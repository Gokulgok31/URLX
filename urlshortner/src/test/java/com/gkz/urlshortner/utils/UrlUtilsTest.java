package com.gkz.urlshortner.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrlUtilsTest {

    private final UrlUtils urlUtils = new UrlUtils();

    @Test
     void test_isUrlValid(){
        assertFalse(urlUtils.isUrlValid("sring boot test"));
        assertFalse(urlUtils.isUrlValid("wrong url test"));
        assertTrue(urlUtils.isUrlValid("https://google.com"));
        assertTrue(urlUtils.isUrlValid("https://facebook.com"));
        assertFalse(urlUtils.isUrlValid("hts://twitter.com"));
        assertFalse(urlUtils.isUrlValid("htt://google.com"));
        assertFalse(urlUtils.isUrlValid("htt://"));
        assertFalse(urlUtils.isUrlValid(null));
    }
}
