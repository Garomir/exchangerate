package com.ramich.exchangerate.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GiphyServiceImplTest {

    @Autowired
    private GiphyService giphyService;

    @Test
    void getUrlFromJsonByTag() {
        String gifUrl = giphyService.getGif("rich");
        assertTrue(gifUrl.contains(".gif"));
    }
}