package com.example.conveniencepos.service;

import com.example.conveniencepos.entity.Payment;
import com.example.conveniencepos.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Double amount, String method, Long saleId) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setSaleId(saleId);
        return paymentRepository.save(payment);
    }
}
