package com.airline.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Named
public class CrazyAirWebServiceClient implements WebServiceClient<CrazyAirResponse> {

    private final static Logger logger = LoggerFactory.getLogger(ToughJetWebServiceClient.class);

    @Inject
    private RestTemplate template;

    @Value("${crazy.air.url}")
    private String crazyAirUri;

    @Override
    public List<CrazyAirResponse> get(Map<String, String> crazyAirRequest) {
        logger.debug("Request {}", crazyAirRequest);
        CrazyAirResponse[] crazyAirReponse = template.getForObject(crazyAirUri, CrazyAirResponse[].class, crazyAirRequest);
        List<CrazyAirResponse> response = Arrays.asList(crazyAirReponse);
        logger.debug("Response {}", response);
        return response;
    }
}