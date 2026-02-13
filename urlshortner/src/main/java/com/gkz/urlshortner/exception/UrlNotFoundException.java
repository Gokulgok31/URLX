package com.gkz.urlshortner.exception;

public class UrlNotFoundException extends RuntimeException{

    public UrlNotFoundException(String message) {
        super(message);
    }
}
