package com.airline;

import com.airline.ws.WebServiceClientHelper;
import jersey.repackaged.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
@Path("/airline")
public class Endpoint {

    private final static Logger logger = LoggerFactory.getLogger(Endpoint.class);

    @Resource(name="crazyAirService") private AirlineLookupService crazyAirService;
    @Resource(name="toughJetService") private AirlineLookupService toughAirService;
    @Inject private ExecutorService executorService;
    @Inject private WebServiceClientHelper webServiceClientHelper;
    @Value("${endpoint.timeout}")
    private Long requestTimeout;

    /**
     * @param origin 3 letter IATA code(eg. LHR, AMS)
     * @param destination 3 letter IATA code(eg. LHR, AMS)
     * @param departureDate Date in ISO8601
     * @param returnDate Date in ISO8601
     * @param numberOfPassengers
     * @return combined and sorted result
     */
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public void search(@QueryParam("origin") @NotNull String origin,
            @QueryParam("destination") @NotNull String destination,
            @QueryParam("departureDate") @NotNull String departureDate,
            @QueryParam("returnDate") @NotNull String returnDate,
            @QueryParam("numberOfPassengers") @NotNull String numberOfPassengers,
            @Suspended final AsyncResponse asyncResponse)
    {
        AirlineLookupServiceRequest request = new AirlineLookupServiceRequest(origin, destination, departureDate, returnDate, numberOfPassengers);
        CompletableFuture<List<AirlineLookupServiceResponse>> toughAirCall = CompletableFuture.supplyAsync(() -> callToughJet(request), executorService);
        CompletableFuture<List<AirlineLookupServiceResponse>> crazyAirCall = CompletableFuture.supplyAsync(() -> callCrazyAir(request), executorService);
        CompletableFuture<List<AirlineLookupServiceResponse>> combined = toughAirCall.thenCombine(crazyAirCall, webServiceClientHelper.combineAndSort);
        CompletableFuture<List<AirlineLookupServiceResponse>> timeout = failAfter(Duration.ofMillis(requestTimeout));
        combined.applyToEither(timeout, asyncResponse::resume);
    }

    private List<AirlineLookupServiceResponse> callToughJet(AirlineLookupServiceRequest request) {
        Map<String, String> toughAirMap = webServiceClientHelper.buildToughAirRequestMap(request);
        return toughAirService.find(toughAirMap);
    }

    private List<AirlineLookupServiceResponse> callCrazyAir(AirlineLookupServiceRequest request) {
        Map<String, String> crazyAirRequestMap = webServiceClientHelper.buildCrazyAirRequestMap(request);
        return crazyAirService.find(crazyAirRequestMap);
    }

    public static CompletableFuture<List<AirlineLookupServiceResponse>> failAfter(Duration duration) {
        final CompletableFuture<List<AirlineLookupServiceResponse>> promise = new CompletableFuture<>();
        scheduler.schedule(() -> {
            logger.info("Timeout occurred while calling remote services");
            return promise.complete(Collections.emptyList());
        },  duration.toMillis(), MILLISECONDS);
        return promise;
    }

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(
                    1,
                    new ThreadFactoryBuilder()
                            .setDaemon(true)
                            .setNameFormat("failAfter-%d")
                            .build());
}