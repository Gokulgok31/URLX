package com.gkz.urlshortner.controller;

import com.gkz.urlshortner.dto.ShortenUrlRequestDto;
import com.gkz.urlshortner.dto.ShortenUrlResponseDto;
import com.gkz.urlshortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/urls")
@Slf4j
public class UrlController {

    private final UrlService urlService;


    @PostMapping
    public ResponseEntity<ShortenUrlResponseDto> shortenUrl(
            @RequestBody ShortenUrlRequestDto requestUrl) {

        log.info("Received request url: {}", requestUrl.getUrl());

        ShortenUrlResponseDto response = urlService.shortenUrl(requestUrl);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> getRedirectionUrl(@PathVariable String shortCode) {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY.value())
                .location(urlService.getRedirectionUri(shortCode))
                .build();
    }

}
