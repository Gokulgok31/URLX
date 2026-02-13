package com.gkz.urlshortner.service;


import com.gkz.urlshortner.dto.ShortenUrlRequestDto;
import com.gkz.urlshortner.dto.ShortenUrlResponseDto;
import com.gkz.urlshortner.entity.Url;
import com.gkz.urlshortner.exception.InvalidUrlException;
import com.gkz.urlshortner.exception.UrlNotFoundException;
import com.gkz.urlshortner.repository.UrlRepository;
import com.gkz.urlshortner.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlService {

    private final UrlUtils urlUtils;
    private final UrlRepository urlRepository;

    public ShortenUrlResponseDto shortenUrl(ShortenUrlRequestDto request) {

        String longUrl = request.getUrl();
        log.info("Recevied request to shorten url: {}", longUrl);

        if (!urlUtils.isUrlValid(longUrl)) {
            log.warn("invalid url provided: {}", longUrl);
            throw new InvalidUrlException("Invalid URL: " + longUrl);
        }

        String shortCode = generateUniqueShortCode();

        Url urlEntity = new Url();
        urlEntity.setLongUrl(longUrl);
        urlEntity.setShortCode(shortCode);

        urlRepository.save(urlEntity);

        log.info("short url created successfully: {}", shortCode);
         return ShortenUrlResponseDto.builder()
                .shortCode(shortCode)
                .build();
    }

    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode = RandomStringUtils.randomAlphanumeric(8);
        } while (urlRepository.existsByShortCode(shortCode)); // NOTE: Capital S
        return shortCode;
    }

    public URI getRedirectionUri(String shortCode) {
        String urlToBeParsed = urlRepository.findByShortCode(shortCode)
                .map(Url::getLongUrl)
                .orElseThrow(() ->
                        new UrlNotFoundException("Short code not found: " + shortCode));
        return URI.create(urlToBeParsed);

    }

}
