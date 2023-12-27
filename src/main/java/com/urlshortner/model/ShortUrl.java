package com.urlshortner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "SHORT_URL")
@AllArgsConstructor
@NoArgsConstructor
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
