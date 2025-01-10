package com.example.conveniencepos.controller;

import com.example.conveniencepos.entity.Sale;
import com.example.conveniencepos.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    /**
     * 渲染销售统计页面
     */
    @GetMapping
    public String getSalesPage(Model model) {
        Map<String, Double> salesByCategory = saleService.getSalesByCategory();
        model.addAttribute("salesByCategory", salesByCategory);
        model.addAttribute("totalSales", null);
        return "auth/sales";
    }

    /**
     * 按时间范围统计销售总额
     */
    @PostMapping("/time")
    public String getTotalSalesByTime(
            @RequestParam String startTime,
            @RequestParam String endTime,
            Model model) {
        try {
            LocalDateTime start = LocalDateTime.parse(startTime);
            LocalDateTime end = LocalDateTime.parse(endTime);

            Double totalSales = saleService.getTotalSalesByTimeRange(start, end);
            Map<String, Double> salesByCategory = saleService.getSalesByCategory();

            model.addAttribute("salesByCategory", salesByCategory);
            model.addAttribute("totalSales", totalSales);
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "时间格式不正确，请使用 '2024-12-01T12:00:00' 格式");
        }

        return "auth/sales";
    }

    /**
     * 创建销售记录并跳转到支付页面
     */
    @PostMapping("/create")
    public String createSale(@RequestParam Long productId,
                             @RequestParam Integer quantity,
                             Model model) {
        Sale sale = saleService.createSale(productId, quantity);

        // 空值检查，防止空指针异常
        if (sale == null) {
            model.addAttribute("error", "无法创建销售记录，请检查输入是否正确");
            return "auth/sales";
        }

        // 跳转到支付页面并传递 saleId 参数
        model.addAttribute("saleId", sale.getId());
        model.addAttribute("amount", sale.getTotalPrice());
        return "redirect:/payments/new?saleId=" + sale.getId();
    }
}
