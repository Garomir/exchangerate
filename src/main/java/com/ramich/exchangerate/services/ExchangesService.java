package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.entities.Exchange;

public interface ExchangesService {
    Exchange getExchangeBySymbol(String symbol) throws JsonProcessingException;
}
