package com.example.conveniencepos.service;

import com.example.conveniencepos.entity.User;
import com.example.conveniencepos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户注册逻辑
     * @param user 用户对象，包含用户名、邮箱和密码
     * @return 保存后的用户对象
     */
    public User registerUser(User user) {
        // 检查用户名和邮箱是否已存在
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在，请更换其他用户名。");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("邮箱已被注册，请使用其他邮箱。");
        }

        // 加密密码
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 设置默认角色为 "USER"
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("USER")); // 默认角色
        }

        // 保存用户
        return userRepository.save(user);
    }

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 包含用户信息的 Optional 对象
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
