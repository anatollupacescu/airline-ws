package com.airline.ws;

import java.util.List;
import java.util.Map;

public interface WebServiceClient<C> {

    List<C> get(Map<String, String> req);
}
