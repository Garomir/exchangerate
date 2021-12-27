package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.clients.OpenExchangeRateClient;
import com.ramich.exchangerate.entities.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExchangesServiceImplTest {

    @Mock
    private Exchange testExchange = new Exchange();

    @Mock
    private OpenExchangeRateClient client;

    @InjectMocks
    private ExchangesService exchangesService = new ExchangesServiceImpl();

    @BeforeEach
    void setUp() {
        testExchange.setBase("USD");
        testExchange.setTimestamp("2021-12-28");
        Mockito.when(client.getRateWithUsdAndSymbol("a8b6fa17824246bca592e9a5441852d7", "AUD")).thenReturn(testExchange);
    }

    @Test
    void getRateWithUsdAndSymbol() {
        try {
            Exchange exchange = exchangesService.getRateWithUsdAndSymbol("AUD");
            assertEquals(exchange.getBase(), testExchange.getBase());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}