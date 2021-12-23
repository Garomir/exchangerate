package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.clients.OpenExchangeRateClient;
import com.ramich.exchangerate.entities.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangesServiceImpl implements ExchangesService{

    @Value("$(exchanges.url)")
    private String exchangesUrl;

    @Value("$(exchanges.appId)")
    private String exchangesAppId;

    @Autowired
    private OpenExchangeRateClient exchangeRateClient;

    @Override
    public Exchange getRateWithUsdAndSymbol(String symbol) throws JsonProcessingException {
        Exchange exchange = exchangeRateClient.getRateWithUsdAndSymbol("a8b6fa17824246bca592e9a5441852d7", symbol);
        return exchange;
    }
}
