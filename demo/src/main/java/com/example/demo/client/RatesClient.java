package com.example.demo.client;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "rates", url = "https://openexchangerates.org/api/latest.json")
public interface RatesClient {
    @GetMapping
    ObjectNode getRates(@RequestParam(name = "app_id") String ratesApiId, @RequestParam String symbols);
}
