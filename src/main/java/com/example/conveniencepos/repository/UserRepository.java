package com.example.conveniencepos.repository;

import com.example.conveniencepos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // 根据用户名查找用户
    Optional<User> findByEmail(String email);       // 根据邮箱查找用户
    boolean existsByUsername(String username);      // 检查用户名是否存在
}
