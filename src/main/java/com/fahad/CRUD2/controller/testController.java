package com.fahad.CRUD2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @Value("${profile}")
    private String message;

    @GetMapping("/")
    public String home(){
        return "Message: " + message;
    }
}
