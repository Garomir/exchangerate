package com.ramich.exchangerate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.entities.Exchange;
import com.ramich.exchangerate.services.ExchangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {
    private ExchangesService exchangesService;

    @Autowired
    public void setExchangesService(ExchangesService exchangesService) {
        this.exchangesService = exchangesService;
    }

    @GetMapping("/rate/{symbol}")
    public Exchange getRateWithUsdAndSymbol(@PathVariable("symbol") String symbol){
        Exchange ex = null;
        try {
            ex = exchangesService.getRateWithUsdAndSymbol(symbol);
            System.out.println(ex.getRates().path(symbol).asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ex;
    }

    @GetMapping("/rate/yesterday/{symbol}")
    public Exchange getYesterdayRateWithUsdAndSymbol(@PathVariable("symbol") String symbol){
        Exchange ex = null;
        try {
            ex = exchangesService.getYesterdayRateWithUsdAndSymbol(symbol);
            System.out.println(ex.getRates().path(symbol).asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ex;
    }
}
