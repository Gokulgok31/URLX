package com.gkz.urlshortner.controller;

import com.gkz.urlshortner.dto.shortenUrlRequestDto;
import com.gkz.urlshortner.dto.shortenUrlResponseDto;
import com.gkz.urlshortner.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/urls")
public class UrlController {

    private final UrlService urlService;


    @PostMapping
    public shortenUrlResponseDto shortenUrl(@RequestBody shortenUrlRequestDto requestUrl) {
        return urlService.shortenUrl(requestUrl);
    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> getRedirectionUrl(@PathVariable String shortCode) {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY.value())
                .location(urlService.getRedirectionUri(shortCode))
                .build();
    }

}
