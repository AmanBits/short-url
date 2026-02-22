package com.neurix.short_url.service;


import org.springframework.stereotype.Service;

@Service
public class IdGeneratorService {

    public int generateId(){
        return (int) (Math.random() * 1_000_000);
    }
}
