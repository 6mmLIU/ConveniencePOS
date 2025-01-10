package com.example.conveniencepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login"; // 返回 templates/auth/login.html
    }
}
