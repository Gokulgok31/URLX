package com.gkz.urlshortner.utils;

import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class UrlUtils {

    public boolean isUrlValid(String url) {
        try{
            URI.create(url).toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
