package com.gkz.urlshortner.exception;

public class LinkExpiredException extends RuntimeException{

    public LinkExpiredException(String message) {
        super(message);
    }
}
