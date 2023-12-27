package com.urlshortner.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UrlDTO {
    private String url;
    private String originalUrl;
    private String shortUrl;
    private Date createAt;
    private Date expiredAt;
}
