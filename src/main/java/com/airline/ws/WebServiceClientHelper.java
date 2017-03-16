package com.airline.ws;

import com.airline.AirlineLookupServiceRequest;
import com.airline.AirlineLookupServiceResponse;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiFunction;

public class WebServiceClientHelper {

    public final BiFunction<List<AirlineLookupServiceResponse>, List<AirlineLookupServiceResponse>, List<AirlineLookupServiceResponse>> combineAndSort = (u, d) -> {
        List<AirlineLookupServiceResponse> combined = new ArrayList<>(u.size() + d.size());
        combined.addAll(u);
        combined.addAll(d);
        combined.sort(Comparator.comparing(AirlineLookupServiceResponse::getFare));
        return combined;
    };

    public Map<String, String> buildToughAirRequestMap(AirlineLookupServiceRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("from", request.origin);
        requestMap.put("to", request.destination);
        requestMap.put("departureDay", String.valueOf(request.departureDate.getDayOfMonth()));
        requestMap.put("departureMonth", String.valueOf(request.departureDate.getMonthValue()));
        requestMap.put("departureYear", String.valueOf(request.departureDate.getYear()));
        requestMap.put("returnDay", String.valueOf(request.returnDate.getDayOfMonth()));
        requestMap.put("returnMonth", String.valueOf(request.returnDate.getMonthValue()));
        requestMap.put("returnYear", String.valueOf(request.returnDate.getYear()));
        requestMap.put("numberOfAdults", String.valueOf(request.numberOfPassengers));
        return requestMap;
    }

    private final static DateTimeFormatter crazyAirRequestDateFormat = DateTimeFormatter.ofPattern("L-d-u");

    public Map<String, String> buildCrazyAirRequestMap(AirlineLookupServiceRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("origin", request.origin);
        requestMap.put("destination", request.destination);
        requestMap.put("departureDate", crazyAirRequestDateFormat.format(request.departureDate));
        requestMap.put("returnDate", crazyAirRequestDateFormat.format(request.returnDate));
        requestMap.put("numberOfPassengers", String.valueOf(request.numberOfPassengers));
        return requestMap;
    }
}
