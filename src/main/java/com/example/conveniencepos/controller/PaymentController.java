package com.example.conveniencepos.controller;

import org.springframework.ui.Model;
import com.example.conveniencepos.entity.Payment;
import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.service.PaymentService;
import com.example.conveniencepos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    // 自动生成唯一 saleId
    private final AtomicLong saleIdGenerator = new AtomicLong(1000);

    /**
     * 显示支付页面
     */
    @GetMapping("/new")
    public String showPaymentPage(@RequestParam(required = false) Long saleId,
                                  @RequestParam(required = false, defaultValue = "0") BigDecimal totalPrice,
                                  @RequestParam(required = false, defaultValue = "1") Integer quantity,
                                  @RequestParam(required = false) Long productId,
                                  Model model) {
        if (saleId == null) {
            saleId = generateNewSaleId(); // 自动生成 saleId
        }

        if (productId == null) {
            model.addAttribute("error", "商品ID不能为空！");
            return "error";
        }

        // 检查商品是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("error", "商品不存在！");
            return "error";
        }

        // 检查库存是否足够
        if (product.getStock() < quantity) {
            model.addAttribute("error", "库存不足！");
            return "error";
        }

        // 将参数传递到页面
        model.addAttribute("saleId", saleId);
        model.addAttribute("quantity", quantity);
        model.addAttribute("totalPrice", BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(quantity))); // 修正价格计算
        model.addAttribute("productId", productId);

        return "auth/payment"; // 返回支付页面
    }

    /**
     * 处理支付请求
     */
    @PostMapping
    public String makePayment(@RequestParam String method,
                              @RequestParam Long saleId,
                              @RequestParam Integer quantity,
                              @RequestParam Long productId,
                              Model model) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("error", "商品不存在！");
            return "error";
        }

        // 检查库存并更新
        synchronized (this) {
            boolean isPurchased = productService.purchaseProduct(productId, quantity);
            if (!isPurchased) {
                model.addAttribute("error", "库存不足！");
                return "error";
            }
        }

        // 计算总价
        BigDecimal totalPrice = BigDecimal.valueOf(product.getPrice()).multiply(BigDecimal.valueOf(quantity));

        // 创建支付记录
        Payment payment = paymentService.createPayment(totalPrice.doubleValue(), method, saleId);

        // 将支付信息传递到视图
        model.addAttribute("payment", payment);
        model.addAttribute("message", "支付成功！");
        return "auth/payment-success"; // 返回支付成功页面
    }

    /**
     * 自动生成 saleId
     */
    private Long generateNewSaleId() {
        return saleIdGenerator.incrementAndGet();
    }
}
