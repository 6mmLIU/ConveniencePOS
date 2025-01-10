package com.example.conveniencepos.service;

import com.example.conveniencepos.entity.Member;
import com.example.conveniencepos.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // 获取所有会员
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 添加会员
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    // 根据ID获取会员信息
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("会员不存在！"));
    }

    // 更新会员
    public void updateMember(Member member) {
        if (!memberRepository.existsById(member.getId())) {
            throw new RuntimeException("会员不存在！");
        }
        memberRepository.save(member);
    }

    // 删除会员
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    // 查询会员
    public List<Member> searchMembers(String keyword) {
        return memberRepository.findByNameContainingOrPhoneNumberContaining(keyword, keyword);
    }
}
