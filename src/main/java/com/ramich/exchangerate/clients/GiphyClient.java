package com.ramich.exchangerate.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${giphy.name}", url="${giphy.url}")
public interface GiphyClient {
    @RequestMapping(method = RequestMethod.GET, value = "?apiKey={apiKey}&tag={tag}")
    Resource getGifByTag(@PathVariable("apiKey") String apiKey, @PathVariable("tag") String tag);
}
