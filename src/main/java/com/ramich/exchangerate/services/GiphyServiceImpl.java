package com.ramich.exchangerate.services;

import com.ramich.exchangerate.clients.GiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class GiphyServiceImpl implements GiphyService{

    private GiphyClient giphyClient;

    @Value("${giphy.apyKey}")
    private String giphyApyKey;

    @Autowired
    public void setGiphyClient(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public Resource getGif(String tag) {
        return giphyClient.getGifByTag(giphyApyKey, tag);
    }
}
