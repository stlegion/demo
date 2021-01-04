package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "giphyImageClient", url = "https://media4.giphy.com/media")
public interface GiphyImageClient {

    @GetMapping("/{imageId}/giphy.gif")
    ResponseEntity<byte[]> getImage(@PathVariable String imageId);
}
