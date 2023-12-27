package com.urlshortner.controller;

import com.urlshortner.dto.UrlDTO;
import com.urlshortner.service.UrlShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;
    @PostMapping("/deflate")
    public String deflate(@RequestBody UrlDTO url){
        UrlDTO processed = urlShortenerService.process(url);
        return "";
    }

    @GetMapping("/{deflated}")
    public String inflate(){
        return "";
    }
}
