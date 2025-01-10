package com.example.conveniencepos.controller;

import com.example.conveniencepos.entity.User;
import com.example.conveniencepos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller // 使用 @Controller 进行页面渲染
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 渲染用户注册页面
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "auth/register";
    }

    /**
     * 处理注册表单提交
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            model.addAttribute("successMessage", "注册成功！请登录");
            return "auth/login"; // 注册成功后跳转到登录页面
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "auth/register"; // 注册失败，返回注册页面
        }
    }

    /**
     * 处理 JSON 数据的注册请求 (API 接口)
     */
    @PostMapping("/api/register")
    @ResponseBody
    public ResponseEntity<?> registerUserApi(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "注册成功");
            response.put("user", registeredUser);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * 根据用户名查询用户信息 (API 调试接口)
     */
    @GetMapping("/api/{username}")
    @ResponseBody
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body((User) Map.of("error", "用户不存在！")));
    }
}
