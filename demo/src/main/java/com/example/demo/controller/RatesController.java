package com.example.demo.controller;

import com.example.demo.service.RatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatesController {
    private final RatesService ratesService;

    @GetMapping("/rates")
    public ResponseEntity<byte[]> getRateCondition(@RequestParam Double yesterdayValue, @RequestParam String currency) {

        return ratesService.getRateCondition(yesterdayValue, currency);
    }
}
