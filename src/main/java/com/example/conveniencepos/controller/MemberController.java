package com.example.conveniencepos.controller;

import com.example.conveniencepos.entity.Member;
import com.example.conveniencepos.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members") // 会员管理基础路径
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 获取会员管理页面并展示所有会员信息
    @GetMapping
    public String getMemberPage(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "auth/members"; // 返回 Thymeleaf 页面 members.html
    }

    // 添加新会员
    @PostMapping("/add")
    public String addMember(@ModelAttribute Member member) {
        memberService.addMember(member);
        return "redirect:/members"; // 添加成功后重定向到会员列表页面
    }

    // 根据ID获取会员信息，显示到编辑页面
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "auth/edit_member"; // 返回编辑页面 edit_member.html
    }

    // 更新会员信息
    @PostMapping("/update")
    public String updateMember(@ModelAttribute Member member) {
        memberService.updateMember(member);
        return "redirect:/members"; // 更新成功后重定向到会员列表页面
    }

    // 删除会员
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "redirect:/members"; // 删除成功后重定向到会员列表页面
    }

    // 查询会员功能
    @GetMapping("/search")
    public String searchMembers(@RequestParam("keyword") String keyword, Model model) {
        List<Member> members = memberService.searchMembers(keyword);
        model.addAttribute("members", members);
        model.addAttribute("keyword", keyword); // 返回搜索关键字到前端
        return "auth/members"; // 返回会员列表页面
    }
}
