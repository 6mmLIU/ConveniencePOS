package com.example.conveniencepos.service;

import com.example.conveniencepos.entity.Product;
import com.example.conveniencepos.entity.Sale;
import com.example.conveniencepos.repository.ProductRepository;
import com.example.conveniencepos.repository.SaleRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    // 按时间维度统计销售总额（保持原样）
    public Map<LocalDateTime, Double> getSalesByTime() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getSaleTime,
                        Collectors.summingDouble(Sale::getTotalPrice)
                ));
    }

    // 按分类统计销售总额，优化性能（已改进）
    public Map<String, Double> getSalesByCategory() {
        // 获取所有商品分类
        List<Product> products = productRepository.findAll();
        Map<Long, String> productCategoryMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Product::getCategory, (a, b) -> a));

        // 初始化所有分类为 0.0
        Map<String, Double> salesByCategory = products.stream()
                .map(Product::getCategory)
                .distinct()
                .collect(Collectors.toMap(category -> category, category -> 0.0));

        // 统计销售额
        List<Sale> sales = saleRepository.findAll();
        sales.forEach(sale -> {
            String category = productCategoryMap.get(sale.getProductId());
            salesByCategory.merge(category, sale.getTotalPrice(), Double::sum);
        });

        return salesByCategory;
    }

    // 按时间范围统计销售总额（保持原样）
    public Double getTotalSalesByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<Sale> sales = saleRepository.findBySaleTimeBetween(startTime, endTime);
        return sales.stream()
                .mapToDouble(Sale::getTotalPrice)
                .sum();
    }

    // 导出销售记录为 Excel 文件（保持原样）
    public byte[] exportSalesToExcel(LocalDateTime startTime, LocalDateTime endTime) throws Exception {
        List<Sale> sales = saleRepository.findBySaleTimeBetween(startTime, endTime);

        // 创建 Excel 工作簿
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sales Report");

        // 设置表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // 创建表头
        Row header = sheet.createRow(0);
        String[] headers = {"销售ID", "商品ID", "销售数量", "总金额", "销售时间"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 15 * 256); // 设置列宽
        }

        // 填充数据
        int rowIdx = 1;
        for (Sale sale : sales) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(sale.getId());
            row.createCell(1).setCellValue(sale.getProductId());
            row.createCell(2).setCellValue(sale.getQuantity());
            row.createCell(3).setCellValue(sale.getTotalPrice());
            row.createCell(4).setCellValue(sale.getSaleTime().toString());
        }

        // 将数据写入字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    // 创建销售记录（保持原样）
    public Sale createSale(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品不存在！"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("库存不足！");
        }

        // 减少库存
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // 创建销售记录
        Sale sale = new Sale();
        sale.setProductId(productId);
        sale.setQuantity(quantity);
        sale.setTotalPrice(product.getPrice() * quantity);
        sale.setSaleTime(LocalDateTime.now());
        return saleRepository.save(sale);
    }

    // ★★★ 新增一个方法，用于根据 saleId 获取 Sale 对象 ★★★
    public Sale getSaleById(Long saleId) {
        return saleRepository.findById(saleId).orElse(null);
    }
}
