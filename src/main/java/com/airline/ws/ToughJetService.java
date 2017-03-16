package com.airline.ws;

import com.airline.AirlineLookupService;
import com.airline.AirlineLookupServiceResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
public class ToughJetService implements AirlineLookupService {

    @Inject private WebServiceClientHelper webServiceClientHelper;
    @Inject private ToughJetWebServiceClient webServiceClient;

    public List<AirlineLookupServiceResponse> find(Map<String, String> toughAirRequestMap) {
        return webServiceClient.get(toughAirRequestMap).stream()
                .map(this::mapToughJetResponse)
                .collect(Collectors.toList());
    }

    private AirlineLookupServiceResponse mapToughJetResponse(ToughJetResponse response) {
       return new AirlineLookupServiceResponse(response.carrier, "toughJet",
                computePrice(response.basePrice, response.discount, response.tax),
                response.departureAirportName,
                response.arrivalAirportName,
                parseDate(response.departureDay, response.departureMonth, response.departureYear),
                parseDate(response.returnDay, response.returnMonth, response.returnYear));
    }

    private LocalDate parseDate(String departureDay, String departureMonth, String departureYear) {
        try {
            return LocalDate.of(Integer.valueOf(departureYear), Integer.valueOf(departureMonth), Integer.valueOf(departureDay));
        } catch (DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String computePrice(String basePrice, String discount, String tax) {
        //TODO substract tax, apply discount
        return basePrice;
    }
}
