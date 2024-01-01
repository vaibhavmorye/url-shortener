package com.urlshortner.controller;

import com.urlshortner.dto.ProcessURLRequest;
import com.urlshortner.service.UrlShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;
    @PostMapping("/deflate")
    public String deflate(@RequestBody ProcessURLRequest url){
        ProcessURLRequest processed = urlShortenerService.process(url);
        return processed.getShortUrl();
    }

    @GetMapping("/public/{deflated}")
    public ProcessURLRequest inflate(@PathVariable String deflated){
        return urlShortenerService.fetch(deflated);
    }
}
