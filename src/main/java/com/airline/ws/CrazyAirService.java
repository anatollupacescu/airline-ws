package com.airline.ws;

import com.airline.AirlineLookupService;
import com.airline.AirlineLookupServiceResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
public class CrazyAirService implements AirlineLookupService {

    @Inject private CrazyAirWebServiceClient webServiceClient;

    public List<AirlineLookupServiceResponse> find(Map<String, String> crazyAirRequest) {
         return webServiceClient.get(crazyAirRequest).stream()
                 .map(this::mapFromCrazyAir)
                 .collect(Collectors.toList());
    }

    private AirlineLookupServiceResponse mapFromCrazyAir(CrazyAirResponse crazyAirResponse) {
        return new AirlineLookupServiceResponse(crazyAirResponse.airline, "crazyAir",
                crazyAirResponse.price,
                crazyAirResponse.destinationAirportCode,
                crazyAirResponse.destinationAirportCode,
                parseCrazyAirDate(crazyAirResponse.departureDate),
                parseCrazyAirDate(crazyAirResponse.arrivalDate));
    }

    private final static DateTimeFormatter crazyAirResponseDateFormatter = DateTimeFormatter.ofPattern("L-d-u H:m:s");

    private LocalDate parseCrazyAirDate(String date) {
        try {
            return LocalDate.parse(date, crazyAirResponseDateFormatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Could not parse date " + date, e);
        }
    }
}
