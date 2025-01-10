package com.example.conveniencepos.controller;

import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class CustomerProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "auth/shop"; // 返回客户商品页面
    }

    @PostMapping("/purchase")
    public String purchaseProduct(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            Model model) {
        boolean success = productService.purchaseProduct(productId, quantity);
        if (success) {
            model.addAttribute("message", "购买成功！");
        } else {
            model.addAttribute("message", "购买失败，库存不足！");
        }
        return "redirect:/shop";
    }
}
