package com.example.HotelApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // Affiche le template Thymeleaf signup.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Affiche le template Thymeleaf login.html
    }
}
