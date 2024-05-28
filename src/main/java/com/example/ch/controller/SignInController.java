package com.example.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class SignInController {
    @GetMapping("/sign-in")
    public String init() {
        return "sign-in"; 
    }
}
