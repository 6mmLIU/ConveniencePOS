package com.example.conveniencepos.scheduler;

import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.repository.ProductRepository;
import com.example.conveniencepos.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockAlertScheduler {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NotificationService notificationService;

    // 定时任务，每天上午9点检查库存
    @Scheduled(cron = "0 0 9 * * ?")
    public void checkLowStock() {
        List<Product> lowStockProducts = productRepository.findAll().stream()
                .filter(product -> product.getStock() < product.getSafeStock())
                .toList();

        for (Product product : lowStockProducts) {
            notificationService.sendLowStockAlert(
                    "admin@example.com", product.getName(), product.getStock());
        }
    }
}
