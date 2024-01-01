package com.urlshortner.service;

import com.urlshortner.dto.ProcessURLRequest;
import com.urlshortner.model.ShortUrl;
import com.urlshortner.repository.ShortUrlRepository;
import com.urlshortner.utils.Base62Util;
import java.io.UnsupportedEncodingException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Log4j2
@AllArgsConstructor
public class UrlShortenerService {

  private ShortUrlRepository shortUrlRepository;

  /*
  * if collision increase / multiple instance of shortURL, and we want to scale the short url logic
  * then we need to add salt/counter to input
  *
  *  long counter = 2907786763L;
      char[] charArray = Base62Util.BASE62_CHARACTERS.toCharArray();
      String hash_string = "";
      while (counter > 0) {
        hash_string += charArray[(int) (counter % 62)];
       counter /= 62;
      }
      url +=hash_string
  *
  }
  * */

  public ProcessURLRequest process(ProcessURLRequest processURLRequest) {
    ShortUrl shortUrl =
        ShortUrl.builder()
            .shortUrl(getShortUrl(processURLRequest.getUrl()))
            .originalUrl(processURLRequest.getUrl())
            .expiredAt(Date.from(new Date().toInstant().plus(1, ChronoUnit.DAYS)))
            .createdAt(new Date())
            .lastVisited(new Date())
            .build();
    shortUrlRepository.save(shortUrl);

    return toDTO(shortUrl);
  }

  public ProcessURLRequest fetch(String id) {
    Optional<ShortUrl> byId = shortUrlRepository.findById(id);
    if (byId.isEmpty()) {
      return ProcessURLRequest.builder().build();
    }
    return toDTO(byId.get());
  }

  private String getShortUrl(String url) {
    try {
      String md5hash = DigestUtils.md5DigestAsHex(url.getBytes("utf-8"));
      return Base62Util.convertToBase62(md5hash).substring(0, 7);
    } catch (UnsupportedEncodingException e) {
      log.error("unsupported encoding error for url : {}", url);
    }
    return null;
  }

  private ProcessURLRequest toDTO(ShortUrl shortUrl) {
    return ProcessURLRequest.builder()
        .shortUrl(shortUrl.getShortUrl())
        .originalUrl(shortUrl.getOriginalUrl())
        .expiredAt(shortUrl.getExpiredAt())
        .createAt(shortUrl.getCreatedAt())
        .build();
  }
}
