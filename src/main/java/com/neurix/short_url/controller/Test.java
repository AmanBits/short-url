package com.neurix.short_url.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fb")
public class Test {

    @GetMapping
    public String myPage(){
        return "Welcome, FB !";
    }

}
