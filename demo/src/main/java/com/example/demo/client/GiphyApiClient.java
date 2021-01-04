package com.example.demo.client;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphyApiClient", url = "https://api.giphy.com/v1/gifs/random")
public interface GiphyApiClient {

    @GetMapping
    ObjectNode getImageData(@RequestParam(name = "api_key") String apiKey, @RequestParam String tag);
}
