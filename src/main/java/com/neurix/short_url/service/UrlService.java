package com.neurix.short_url.service;


import com.neurix.short_url.dto.ShortenRequestBody;
import com.neurix.short_url.model.UrlMapping;
import com.neurix.short_url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {


    @Autowired
    private IdGeneratorService idGeneratorService;


    @Autowired
    private UrlRepository urlRepository;


    // @Autowired
    // private CacheService cacheService;


    public UrlMapping generateShortCode(ShortenRequestBody body){
        int num = idGeneratorService.generateId();
        String shortCode = baseCode62Encode(num);
        UrlMapping urlMapping = saveMapping(body,shortCode);
        return urlMapping;
    }


    public UrlMapping saveMapping(ShortenRequestBody body, String shortCode){
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortCode(shortCode);
        urlMapping.setLongUrl(body.getLongUrl());
        urlMapping.setExpireAt(body.getExpiry());
        urlMapping.setUserId((long) Math.random());
        // Save into cache memory
        // cacheService.setShortCode(shortCode,body.getLongUrl());
        return urlRepository.save(urlMapping);
    }

    public String getLongUrl(String shortCode) {

        String longUrl = cacheService.getShortCode(shortCode);
        if (longUrl != null) {
            return longUrl;
        }

        Optional<UrlMapping> urlMapping = urlRepository.findById(shortCode);
        if (urlMapping.isEmpty()) {
            return "404 : Not Found!";
        }

        String lu = urlMapping.get().getLongUrl();
        // cacheService.setShortCode(shortCode, lu);
        return lu;
    }

    public String baseCode62Encode(int num){
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String result ="";
        while(num>0){
            result = letters.charAt(num%62)+result;
            num= (int) Math.floor(num/62);
        }
        return result;
    }


}
