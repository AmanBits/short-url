package com.neurix.short_url.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final RedisTemplate<String, String> redisTemplate;

    public CacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public String getShortCode(String shortCode){
        return redisTemplate.opsForValue().get(shortCode);
    }
    public void setShortCode(String shortCode,String longUrl){
        redisTemplate.opsForValue().set(shortCode,longUrl);
    }
}
