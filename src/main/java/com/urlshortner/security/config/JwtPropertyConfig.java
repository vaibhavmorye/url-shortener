package com.urlshortner.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtPropertyConfig {

    private String secret;
    private String expirationTimeoutMs;
    private String cookie;
}
