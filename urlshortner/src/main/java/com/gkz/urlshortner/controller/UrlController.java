package com.gkz.urlshortner.controller;

import com.gkz.urlshortner.dto.shortenUrlRequestDTO;
import com.gkz.urlshortner.dto.shortenUrlResponseDTO;
import com.gkz.urlshortner.entity.Url;
import com.gkz.urlshortner.service.UrlService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;


    @PostMapping("/shortenUrl")
    public shortenUrlResponseDTO shortenUrl(@RequestBody shortenUrlRequestDTO requestUrl) {
        return urlService.shortenUrl(requestUrl);
    }
}
