package com.example.conveniencepos.repository;

import com.example.conveniencepos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 数据访问层组件
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 按分类查找商品
    List<Product> findByCategory(String category);

    // 按名称查找商品
    Product findByName(String name);
}
