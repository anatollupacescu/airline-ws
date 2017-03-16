package com.airline;

import java.util.List;
import java.util.Map;

public interface AirlineLookupService {

    List<AirlineLookupServiceResponse> find(Map<String, String> request);
}
