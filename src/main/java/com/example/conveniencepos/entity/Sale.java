package com.example.conveniencepos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 销售记录 ID

    @Column(nullable = false)
    private Long productId; // 商品 ID（外键）

    @Column(nullable = false)
    private Integer quantity; // 销售数量

    @Column(nullable = false)
    private Double totalPrice; // 总金额

    @Column(nullable = false)
    private LocalDateTime saleTime; // 销售时间
}
