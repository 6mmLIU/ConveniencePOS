package com.example.conveniencepos.service;

import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.repository.ProductRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 添加新商品
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 获取所有商品
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 根据 ID 获取商品
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品不存在！"));
    }

    // 删除商品
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // 更新商品信息
    public void updateProduct(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new RuntimeException("商品不存在！"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        productRepository.save(existingProduct);
    }

    // 获取低库存商品列表
    public List<Product> getLowStockProducts() {
        return productRepository.findAll().stream()
                .filter(product -> product.getStock() < product.getSafeStock())
                .toList();
    }

    // 调整库存
    public void updateStock(Long productId, int change) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品不存在！"));
        int newStock = product.getStock() + change;
        if (newStock < 0) {
            throw new RuntimeException("库存不足！");
        }
        product.setStock(newStock);
        productRepository.save(product);
    }

    //购买逻辑
    public boolean purchaseProduct(Long productId, Integer quantity) {
        Product product = getProductById(productId);
        if (product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
            return true;
        } else {
            return false;
        }
    }

    // 导出低库存商品列表为 Excel 文件
    public byte[] exportLowStockToExcel() throws Exception {
        List<Product> lowStockProducts = getLowStockProducts();

        // 创建 Excel 工作簿
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Low Stock Report");

        // 创建表头
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("商品ID");
        header.createCell(1).setCellValue("商品名称");
        header.createCell(2).setCellValue("当前库存");
        header.createCell(3).setCellValue("安全库存");

        // 填充数据
        int rowIndex = 1;
        for (Product product : lowStockProducts) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getStock());
            row.createCell(3).setCellValue(product.getSafeStock());
        }

        // 将数据写入字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}
