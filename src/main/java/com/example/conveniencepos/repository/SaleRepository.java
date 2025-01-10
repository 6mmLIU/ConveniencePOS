package com.example.conveniencepos.repository;

import com.example.conveniencepos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository // 标识为数据访问层组件
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // 查询指定时间范围内的销售记录
    List<Sale> findBySaleTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
}
