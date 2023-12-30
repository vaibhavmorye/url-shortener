package com.urlshortner.security.jwt;

import com.urlshortner.security.service.UserDetailsImp;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.UnsupportedKeyException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient.ResponseSpec;
import org.springframework.web.util.WebUtils;

@Service
@Log4j2
public class JwtUtils {

  private String jwtSecret;

  private String jwtExpirationTimeout;

  private String jwtCookie;

  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, jwtCookie);
    if (cookie != null) return cookie.getValue();
    else return null;
  }

  public ResponseCookie generateJwtCookie(UserDetailsImp userPrinciple) {
    String jwt = generateJwtFromUsername(userPrinciple.getUsername());
    return ResponseCookie.from(jwtCookie, jwt)
        .path("/api")
        .maxAge(24 * 60 * 60)
        .httpOnly(true)
        .build();
  }

  public ResponseCookie clearCookies() {
    return ResponseCookie.from(jwtCookie, null).path("/api").build();
  }

  private String generateJwtFromUsername(String username) {
    return Jwts.builder()
        .subject(username)
        .issuedAt(new Date())
        .expiration(new Date(new Date().getTime() + jwtExpirationTimeout))
        .signWith(key())
        .compact();
  }

  public boolean validateJwt(String jwt) {
    try {
      Jwts.parser().verifyWith(key()).build().parse(jwt);
      return true;
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedKeyException e) {
      log.error("JWT token is Unsupported", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT token is empty : {}", e.getMessage());
    }
    return false;
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getUserName(String jwt) {
    return Jwts.parser().verifyWith(key()).build().parseSignedClaims(jwt).getPayload().getSubject();
  }
}
