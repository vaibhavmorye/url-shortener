package com.urlshortner.model;

import jakarta.persistence.Id;
import java.util.Date;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class ShortUrl {
    @Id
    private String shortUrl;
    private String originalUrl;
    private String userId;
    private Date createdAt;
    private Date lastVisited;
    private Date expiredAt;

}
