package com.example.demo.service;

import com.example.demo.client.GiphyImageClient;
import com.example.demo.client.GiphyApiClient;
import com.example.demo.client.RatesClient;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatesService {
    private static final String RICH = "rich";
    private static final String BROKE = "broke";

    private final GiphyImageClient giphyImageClient;
    private final GiphyApiClient giphyApiClient;
    private final RatesClient ratesClient;

    @Value("${giphyApiId}")
    private String giphyApiId;
    @Value("${ratesApiId}")
    private String ratesApiId;

    public ResponseEntity<byte[]> getRateCondition(Double yesterdayValue, String currency) {
        ObjectNode rates = ratesClient.getRates(ratesApiId, currency);
        Double currentValue = rates.get("rates").get(currency).asDouble();
        String tag = currentValue >= yesterdayValue ? RICH : BROKE;
        ObjectNode imageData = giphyApiClient.getImageData(giphyApiId, tag);
        String imageId = imageData.get("data").get("id").asText();
        return giphyImageClient.getImage(imageId);
    }
}
