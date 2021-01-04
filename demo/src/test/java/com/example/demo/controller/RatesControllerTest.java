package com.example.demo.controller;

import com.example.demo.service.RatesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatesController.class)
public class RatesControllerTest {
    @Autowired
    protected MockMvc mvc;
    @MockBean
    RatesService ratesService;

    @Test
    public void getRateCondition_success_http200() throws Exception {
        mvc.perform(get("/rates")
                .param("yesterdayValue", "0.0")
                .param("currency", "RUB"))
                .andExpect(status().isOk());
    }
}
