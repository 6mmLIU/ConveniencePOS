package com.example.conveniencepos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 用户名

    @Column(nullable = false, unique = true)
    private String email; // 邮箱

    @Column(nullable = false)
    private String password; // 密码

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles; // 用户角色，如 "ADMIN", "CASHIER", "USER"
}
