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
public class ToughJetWebServiceClient implements WebServiceClient<ToughJetResponse> {

    private final static Logger logger = LoggerFactory.getLogger(ToughJetWebServiceClient.class);

    @Inject
    private RestTemplate template;

    @Value("${tough.jet.url}")
    private String remoteUri;

    @Override
    public List<ToughJetResponse> get(Map<String, String> toughAirRequestMap) {
        logger.debug("Request {}", toughAirRequestMap);
        ToughJetResponse[] toughJetResponse = template.getForObject(remoteUri, ToughJetResponse[].class, toughAirRequestMap);
        List<ToughJetResponse> response = Arrays.asList(toughJetResponse);
        logger.debug("Response {}", response);
        return response;
    }
}