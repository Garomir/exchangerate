package com.ramich.exchangerate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.entities.Exchange;
import com.ramich.exchangerate.services.ExchangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {
    @Autowired
    private ExchangesService exchangesService;

    /*@Autowired
    public void setExchangesService(ExchangesService exchangesService) {
        this.exchangesService = exchangesService;
    }*/

    @GetMapping("/test")
    public void mapJson(){
        try {
            Exchange ex = exchangesService.getExchangeBySymbol("AUD");
            System.out.println(ex.getRates().path("AUD").asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
