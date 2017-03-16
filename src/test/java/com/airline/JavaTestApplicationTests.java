package com.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.airline.ws.CrazyAirResponse;
import com.airline.ws.ToughJetResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JavaTestApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;
    private MockRestServiceServer mockServer;

    @Value("${tough.jet.url}")
    private String toughJet;

    @Value("${crazy.air.url}")
    private String crazyAir;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mockServer = MockRestServiceServer.bindTo(template).build();
    }

    @Test
    public void test() throws IOException {
        ToughJetResponse tjr1 = new ToughJetResponse("ToughJet",
                "123.0089",
                "0.00", "0.00",
                "LAX",
                "IAD",
                "1", "2", "2016", "2", "1", "2016");
        ToughJetResponse[] tjr = new ToughJetResponse[]{tjr1};
        String response = mapper.writeValueAsString(tjr);
        mockServer.expect(times(1), requestTo(toughJet)).andRespond(withSuccess(response, MediaType.APPLICATION_JSON));
        CrazyAirResponse crazyAirMockResponse = new CrazyAirResponse("crazyAir",
                "122.00",
                "E",
                "LAX",
                "IAD", "1-2-2016 14:00:00", "2-2-2016 16:00:00");
        CrazyAirResponse[] crazyAirMockResponses = new CrazyAirResponse[]{crazyAirMockResponse};
        String crazyAirStringResponse = mapper.writeValueAsString(crazyAirMockResponses);
        mockServer.expect(times(1), requestTo(crazyAir)).andRespond(withSuccess(crazyAirStringResponse, MediaType.APPLICATION_JSON));
        String body = this.restTemplate.getForObject("/airline/search?origin=LAX&destination=IAD&departureDate=1-2-2016 12:00:00&returnDate=2-2-2016 12:00:00&numberOfPassengers=1", String.class);
        AirlineLookupServiceResponse[] airlineLookupServiceResponseArr = mapper.readValue(body, AirlineLookupServiceResponse[].class);
        assertThat(airlineLookupServiceResponseArr.length).isEqualTo(2);
        assertThat(airlineLookupServiceResponseArr[0].getFare()).isEqualTo("122.00");
        assertThat(airlineLookupServiceResponseArr[1].getFare()).isEqualTo("123.00");
    }
}