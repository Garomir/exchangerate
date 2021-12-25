package com.ramich.exchangerate.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

public interface GiphyService {
    Resource getGif(String tag);
}
