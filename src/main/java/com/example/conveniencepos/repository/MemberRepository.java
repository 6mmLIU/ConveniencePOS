package com.example.conveniencepos.repository;

import com.example.conveniencepos.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 根据姓名或手机号模糊查询
    List<Member> findByNameContainingOrPhoneNumberContaining(String name, String phoneNumber);
}
