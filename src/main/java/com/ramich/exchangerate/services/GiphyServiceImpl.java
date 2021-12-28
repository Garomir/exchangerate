package com.ramich.exchangerate.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramich.exchangerate.clients.GiphyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GiphyServiceImpl implements GiphyService{

    private GiphyClient giphyClient;

    @Value("${giphy.apiKey}")
    private String giphyApiKey;

    @Autowired
    public void setGiphyClient(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public String getGif(String tag) {
        String json = giphyClient.getGifUrlByTag(giphyApiKey, tag);
        return getGifUrlFromJson(json);
    }

    private String getGifUrlFromJson(String json){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assert jsonNode != null;
        JsonNode url = jsonNode.at("/data/images/original/url");
        return url.asText();
    }
}