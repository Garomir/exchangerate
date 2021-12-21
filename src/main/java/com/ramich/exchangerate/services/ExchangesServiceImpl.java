package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramich.exchangerate.entities.Exchange;
import org.springframework.stereotype.Service;

@Service
public class ExchangesServiceImpl implements ExchangesService{

    String testJson = "{\"disclaimer\": \"Usage subject to terms: " +
            "https://openexchangerates.org/terms\",\"license\":" +
            " \"https://openexchangerates.org/license\",\"timestamp\": 1640120400" +
            ",\"base\": \"USD\",\"rates\": {\"AUD\": 1.397517}}";
    @Override
    public Exchange getExchangeBySymbol(String symbol) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Exchange exchange = objectMapper.readValue(testJson, Exchange.class);

        return exchange;
    }
}
