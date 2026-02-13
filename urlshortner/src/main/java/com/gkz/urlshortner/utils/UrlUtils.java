package com.gkz.urlshortner.utils;

import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class UrlUtils {

    public boolean isUrlValid(String url) {

        if(url == null || url.isBlank()) {
            return false;
        }
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();
            if(scheme == null ||
                    (!scheme.equalsIgnoreCase("http") &&
                            !scheme.equalsIgnoreCase("https"))){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
