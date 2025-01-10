package com.example.conveniencepos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    // 发送库存预警邮件
    public void sendLowStockAlert(String email, String productName, int stock) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("库存预警");
        message.setText("商品 " + productName + " 的库存仅剩 " + stock + " 件，请及时补货。");
        mailSender.send(message);
    }
}
