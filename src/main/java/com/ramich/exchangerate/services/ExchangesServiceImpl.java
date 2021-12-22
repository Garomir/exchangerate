package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramich.exchangerate.clients.OpenExchangeRateClient;
import com.ramich.exchangerate.entities.Exchange;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Service;

@Service
public class ExchangesServiceImpl implements ExchangesService{
    /*String testJson = "{\"disclaimer\": \"Usage subject to terms: " +
            "https://openexchangerates.org/terms\",\"license\":" +
            " \"https://openexchangerates.org/license\",\"timestamp\": 1640120400" +
            ",\"base\": \"USD\",\"rates\": {\"AUD\": 1.397517}}";*/

    @Value("$(exchanges.url)")
    private String exchangesUrl;

    @Value("$(exchanges.appId)")
    private String exchangesAppId;

    OpenExchangeRateClient exchangeClient = Feign.builder()
            .decoder((Decoder) new ObjectMapper())
            .target(OpenExchangeRateClient.class, exchangesUrl);

    @Override
    public Exchange getExchangeBySymbol(String symbol) throws JsonProcessingException {
        Exchange exchange = exchangeClient.getRateWithUsdAndSymbol(exchangesAppId, symbol);
        return exchange;

        /*ObjectMapper objectMapper = new ObjectMapper();

        Exchange exchange = objectMapper.readValue(testJson, Exchange.class);

        return exchange;*/
    }
}
