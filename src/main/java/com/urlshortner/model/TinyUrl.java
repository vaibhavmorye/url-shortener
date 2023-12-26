package com.urlshortner.model;

import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
public class TinyUrl {
    @Id
    private String shortUrl;
    private String originalUrl;
    private String userId;
    private Date createdAt;
    private Date lastVisited;
    private Date expiredAt;

}
