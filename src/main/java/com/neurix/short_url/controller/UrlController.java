package com.neurix.short_url.controller;


import com.neurix.short_url.dto.ShortenRequestBody;
import com.neurix.short_url.dto.ShortenResponseBody;
import com.neurix.short_url.model.UrlMapping;
import com.neurix.short_url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shorten")
public class UrlController {


    @Autowired
    private UrlService urlService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShortenResponseBody createShortUrl(@RequestBody ShortenRequestBody body){
        UrlMapping urlMapping = urlService.generateShortCode(body);
        String shortUrl = "https://short-url-hprf.onrender.com/api/shorten/"+urlMapping.getShortCode();
        ShortenResponseBody shortenResponseBody = new ShortenResponseBody();
        shortenResponseBody.setShortUrl(shortUrl);
        return shortenResponseBody;
    }



    @GetMapping("/{shortCode}")
    public ResponseEntity<Void>  redirect(@PathVariable String shortCode){
        String longUrl = urlService.getLongUrl(shortCode);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY) // 301
                .header(HttpHeaders.LOCATION, longUrl)
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
                .header(HttpHeaders.VARY, "")
                .build();
    }



}
