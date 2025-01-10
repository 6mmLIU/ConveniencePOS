package com.example.conveniencepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    // 渲染注册页面
    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register"; // 返回 Thymeleaf 页面 auth/register.html
    }
}
