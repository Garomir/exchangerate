package com.ramich.exchangerate.clients;

import com.ramich.exchangerate.entities.Exchange;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "open-exchanges")
public interface OpenExchangeRateClient {

    //https://openexchangerates.org/api/latest.json?app_id=a8b6fa17824246bca592e9a5441852d7&symbols=AUD
    @RequestLine(value = "GET /latest.json?app_id={appId}&symbols={symbol}")
    Exchange getRateWithUsdAndSymbol(@Param("appId") String appId, @Param("symbol") String symbol);
}
