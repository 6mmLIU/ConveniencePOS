package com.example.conveniencepos.controller;

import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products") // 商品管理的基础路径
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取商品管理页面，并展示所有商品
    @GetMapping
    public String getProductPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "auth/products"; // 返回 Thymeleaf 页面 products.html
    }

    // 添加新商品
    @PostMapping("/add")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "redirect:/products"; // 添加成功后重定向到商品列表页面
    }

    // 删除商品（根据 ID）
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products"; // 删除成功后重定向到商品列表页面
    }

    // 渲染编辑商品页面
    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "auth/editProduct"; // 返回编辑商品的页面 editProduct.html
    }

    // 更新商品信息
    @PostMapping("/update")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/products"; // 更新成功后重定向到商品列表页面
    }

    // 库存预警页面：展示低库存商品
    @GetMapping("/lowStock")
    public String getLowStockPage(Model model) {
        List<Product> lowStockProducts = productService.getLowStockProducts();
        model.addAttribute("lowStockProducts", lowStockProducts);
        return "auth/lowStock"; // 返回库存预警页面 lowStock.html
    }

    // 调整商品库存
    @PostMapping("/updateStock")
    public String updateStock(@RequestParam Long productId, @RequestParam int change) {
        productService.updateStock(productId, change); // 调用实际存在的 updateStock 方法
        return "redirect:/products";
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportLowStock() throws Exception {
        byte[] excelData = productService.exportLowStockToExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=low_stock_report.xls")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelData);
    }
}
