package com.airline;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface AirlineLookupService {

    List<AirlineLookupServiceResponse> find(Map<String, String> request);
}
