package com.gkz.urlshortner.service;


import com.gkz.urlshortner.dto.shortenUrlRequestDto;
import com.gkz.urlshortner.dto.shortenUrlResponseDto;
import com.gkz.urlshortner.entity.Url;
import com.gkz.urlshortner.repository.UrlRepository;
import com.gkz.urlshortner.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlUtils urlUtils;
    private final UrlRepository urlRepository;

    public shortenUrlResponseDto shortenUrl(shortenUrlRequestDto request) {

        String longUrl = request.getUrl();
        if (!urlUtils.isUrlValid(longUrl)) {
            throw new IllegalArgumentException("Invalid URL: " + longUrl);
        }
        String shortCode = generateUniqueShortCode();
        Url urlEntity = new Url();
        urlEntity.setLongUrl(longUrl);
        urlEntity.setShortCode(shortCode);
        urlRepository.save(urlEntity);

        return shortenUrlResponseDto.builder()
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
                .orElse("/");
        return URI.create(urlToBeParsed);

    }
}
