package com.example.conveniencepos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private Long saleId;
}
