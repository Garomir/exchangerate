package com.ramich.exchangerate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ramich.exchangerate.entities.Exchange;
import com.ramich.exchangerate.services.ExchangesService;
import com.ramich.exchangerate.services.GiphyService;
import com.ramich.exchangerate.utils.MediaTypeUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;

@RestController
public class MyController {

    private ExchangesService exchangesService;
    private GiphyService giphyService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public void setExchangesService(ExchangesService exchangesService) {
        this.exchangesService = exchangesService;
    }

    @Autowired
    public void setGiphyService(GiphyService giphyService) {
        this.giphyService = giphyService;
    }

    @GetMapping("/rate/today/{symbol}")
    public Exchange getRateWithUsdAndSymbol(@PathVariable("symbol") String symbol){
        Exchange ex = null;
        try {
            ex = exchangesService.getRateWithUsdAndSymbol(symbol);
            System.out.println(ex.getRates().path(symbol).asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ex;
    }

    @GetMapping("/rate/yesterday/{symbol}")
    public Exchange getYesterdayRateWithUsdAndSymbol(@PathVariable("symbol") String symbol){
        Exchange ex = null;
        try {
            ex = exchangesService.getYesterdayRateWithUsdAndSymbol(symbol);
            System.out.println(ex.getRates().path(symbol).asText());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ex;
    }

    @GetMapping("/rate/{symbol}")
    public ResponseEntity<InputStreamResource> getGif(@PathVariable("symbol") String symbol) throws IOException {
        InputStreamResource inputStreamResource;
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

        File file = null;
        MediaType mediaType = null;
        if (todayRate > yesterdayRate){
            gifUrl = giphyService.getGif("rich");
            file = new File("C:\\Proj\\fillle");
            //file = File.createTempFile("data", null);
            FileUtils.copyURLToFile(new URL(gifUrl), file);
            mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, file.getName());

            //InputStream in = new URL(gifUrl).openStream();
            inputStreamResource = new InputStreamResource(new FileInputStream(file));
            /*File file = new File(gifUrl);
            inputStreamResource = new InputStreamResource(new FileInputStream(file));*/
        } else {
            gifUrl = giphyService.getGif("broke");
            file = new File("C:\\Proj\\fillle.gif");
            //file = File.createTempFile("data", null);
            FileUtils.copyURLToFile(new URL(gifUrl), file);
            mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, file.getName());

            //InputStream in = new URL(gifUrl).openStream();
            inputStreamResource = new InputStreamResource(new FileInputStream(file));
            /*File file = new File(gifUrl);
            inputStreamResource = new InputStreamResource(new FileInputStream(file));*/
        }

        //return gifUrl;

        return ResponseEntity.ok()
                // Content-Disposition
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                // Content-Type
                .contentType(MediaType.IMAGE_GIF)
                // Contet-Length
                .contentLength(file.length()) //
                .body(inputStreamResource);
    }
}
