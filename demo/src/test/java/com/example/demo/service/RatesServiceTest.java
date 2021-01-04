package com.example.demo.service;

import com.example.demo.client.GiphyApiClient;
import com.example.demo.client.GiphyImageClient;
import com.example.demo.client.RatesClient;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {"giphyApiId=giphyApiId", "ratesApiId=ratesApiId"})
public class RatesServiceTest {
    private static final double CURRENT_VALUE = 0.1;
    private static final double YESTERDAY_VALUE_EQUAL = 0.1;
    private static final double YESTERDAY_VALUE_LESS = 0.0;
    private static final double YESTERDAY_VALUE_GREATER = 0.2;
    private static final String CURRENCY = "RUB";
    private static final String IMAGE_ID = "imageId";

    @Autowired
    private RatesService ratesService;

    @MockBean
    private  GiphyImageClient giphyImageClient;
    @MockBean
    private  GiphyApiClient giphyApiClient;
    @MockBean
    private  RatesClient ratesClient;

    @Mock(answer = RETURNS_DEEP_STUBS)
    private ObjectNode ratesAnswer;
    @Mock(answer = RETURNS_DEEP_STUBS)
    private ObjectNode giphyApiAnswer;

    @BeforeEach
    public void init() {

        when(ratesClient.getRates(any(), any())).thenReturn(ratesAnswer);
        when(giphyApiClient.getImageData(any(), any())).thenReturn(giphyApiAnswer);
        when(giphyApiAnswer.get("data").get("id").asText()).thenReturn(IMAGE_ID);
    }

    @Test
    public void getRateCondition_currentValueGreaterThanYesterdayValue_reachImageResponse() {
        when(ratesAnswer.get("rates").get(CURRENCY).asDouble()).thenReturn(CURRENT_VALUE);

        ratesService.getRateCondition(YESTERDAY_VALUE_GREATER, CURRENCY);

        verify(giphyApiClient, times(1)).getImageData("giphyApiId", "broke");
        verify(giphyImageClient, times(1)).getImage(IMAGE_ID);
    }

    @Test
    public void getRateCondition_currentValueEqualThanYesterdayValue_richImageResponse() {
        when(ratesAnswer.get("rates").get(CURRENCY).asDouble()).thenReturn(CURRENT_VALUE);

        ratesService.getRateCondition(YESTERDAY_VALUE_EQUAL, CURRENCY);

        verify(giphyApiClient, times(1)).getImageData("giphyApiId", "rich");
        verify(giphyImageClient, times(1)).getImage(IMAGE_ID);
    }

    @Test
    public void getRateCondition_currentValueLessThanYesterdayValue_brokeImageResponse() {
        when(ratesAnswer.get("rates").get(CURRENCY).asDouble()).thenReturn(CURRENT_VALUE);

        ratesService.getRateCondition(YESTERDAY_VALUE_LESS, CURRENCY);

        verify(giphyApiClient, times(1)).getImageData("giphyApiId", "rich");
        verify(giphyImageClient, times(1)).getImage(IMAGE_ID);
    }
}
