package com.ramich.exchangerate.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ExchangeTest {

    private static Exchange exchange1 = null;
    private static Exchange exchange2 = null;
    private static Exchange exchange3 = null;

    @BeforeAll
    static void beforeAll() {
        exchange1 = new Exchange();
        exchange1.setBase("USD");
        exchange2 = new Exchange();
        exchange2.setBase("USD");
        exchange3 = new Exchange();
        exchange3.setBase("AUD");
    }

    @Test
    public void createExchange(){
        assertEquals("USD", exchange1.getBase());
    }

    @Test
    public void equalsExchanges(){
        assertEquals(exchange1, exchange2);
    }

    @Test
    public void notEqualsExchange(){
        assertNotEquals(exchange1, exchange3);
    }
}