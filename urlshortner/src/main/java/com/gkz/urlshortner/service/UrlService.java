package com.gkz.urlshortner.service;


import com.gkz.urlshortner.dto.shortenUrlRequestDTO;
import com.gkz.urlshortner.dto.shortenUrlResponseDTO;
import com.gkz.urlshortner.entity.Url;
import com.gkz.urlshortner.repository.UrlRepository;
import com.gkz.urlshortner.utils.UrlUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlUtils urlUtils;
    private final UrlRepository urlRepository;

    public shortenUrlResponseDTO shortenUrl(shortenUrlRequestDTO requestUrl) {
        String url = requestUrl.getUrl();
        boolean isValid = urlUtils.isUrlValid(url);
        try {
            String shortCode = "Random String";
            if (urlRepository.existsByshortCode(shortCode)) ;
            Url urlEntity = new Url();
            urlEntity.setLongUrl(url);
            urlEntity.setShortCode(shortCode);
            urlRepository.save(urlEntity);
            return shortenUrlResponseDTO.builder().
                    shortCode(shortCode)
                    .build();
        } catch (DataIntegrityViolationException e) {
            String shortCode = RandomStringUtils.randomAlphanumeric(8);
            if (urlRepository.existsByshortCode(shortCode)) ;
            Url urlEntity = new Url();
            urlEntity.setLongUrl(url);
            urlEntity.setShortCode(shortCode);
            urlRepository.save(urlEntity);
            return shortenUrlResponseDTO.builder().
                    shortCode(shortCode)
                    .build();
        }
    }
}
