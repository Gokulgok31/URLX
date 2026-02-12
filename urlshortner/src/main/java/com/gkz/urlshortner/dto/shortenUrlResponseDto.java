package com.gkz.urlshortner.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class shortenUrlResponseDto {
    private String shortCode;
}
