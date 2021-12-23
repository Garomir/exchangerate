package com.ramich.exchangerate.clients;

import com.ramich.exchangerate.entities.Exchange;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${exchanges.name}", url="${exchanges.url}")
public interface OpenExchangeRateClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest.json?app_id={appId}&symbols={symbol}")
    Exchange getRateWithUsdAndSymbol(@PathVariable("appId") String appId, @PathVariable("symbol") String symbol);
}
