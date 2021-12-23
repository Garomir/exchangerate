package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.clients.OpenExchangeRateClient;
import com.ramich.exchangerate.entities.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ExchangesServiceImpl implements ExchangesService {

    @Value("$(exchanges.url)")
    private String exchangesUrl;

    @Value("$(exchanges.appId)")
    private String exchangesAppId;

    @Autowired
    private OpenExchangeRateClient exchangeRateClient;

    @Override
    public Exchange getRateWithUsdAndSymbol(String symbol) throws JsonProcessingException {
        return exchangeRateClient.getRateWithUsdAndSymbol("a8b6fa17824246bca592e9a5441852d7", symbol);
    }

    @Override
    public Exchange getYesterdayRateWithUsdAndSymbol(String symbol) throws JsonProcessingException {
        return exchangeRateClient
                .getYesterdayRateWithUsdAndSymbol("a8b6fa17824246bca592e9a5441852d7", symbol, getYesterday());
    }

    private String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}