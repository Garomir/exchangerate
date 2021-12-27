package com.ramich.exchangerate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ramich.exchangerate.entities.Exchange;
import com.ramich.exchangerate.services.ExchangesService;
import com.ramich.exchangerate.services.GiphyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/static")
public class StaticController {
    private ExchangesService exchangesService;
    private GiphyService giphyService;

    @Autowired
    public void setExchangesService(ExchangesService exchangesService) {
        this.exchangesService = exchangesService;
    }

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    @GetMapping("/{symbol}")
    public String getGifPage(@PathVariable("symbol") String symbol,
                             Model model){
        String gifUrl;
        Exchange yesterday = null;
        Exchange today = null;
        double yesterdayRate, todayRate;

        try {
            yesterday = exchangesService.getYesterdayRateWithUsdAndSymbol(symbol);
            today = exchangesService.getRateWithUsdAndSymbol(symbol);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assert yesterday != null;
        yesterdayRate = yesterday.getRates().path(symbol).asDouble();
        assert today != null;
        todayRate = today.getRates().path(symbol).asDouble();

        if (todayRate > yesterdayRate){
            gifUrl = giphyService.getGif("rich");
        } else {
            gifUrl = giphyService.getGif("broke");
        }

        model.addAttribute("gifUrl", gifUrl);
        return "index";
    }
}
