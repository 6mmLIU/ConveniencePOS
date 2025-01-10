package com.example.conveniencepos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 会员姓名

    @Column(nullable = false, unique = true)
    private String phoneNumber; // 会员手机号

    @Column(nullable = false)
    private Integer points = 0; // 会员积分（默认0）
}
