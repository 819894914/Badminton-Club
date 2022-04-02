package com.cai.badmintonclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private loginService loginService;


    @RequestMapping("/login")
    public String toLogin() {
        return "UserAction/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "UserACtion/register";
    }

    @PostMapping("/doLogin")
    public String doLogin(String account, String password, HttpSession session, Model model) {
        QueryWrapper<member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_account", account).eq("member_password", password);
        member member = loginService.getOne(queryWrapper);
        if (member == null) {
            model.addAttribute("errorMsg", "密码错误或用户不存在");
            return "UserAction/login";
        } else {
            session.setAttribute("member", member);
            return "index";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpSession HttpSession) {
        HttpSession.removeAttribute("member");
        return "redirect:/index";
    }

    @RequestMapping("/doRegister")
    public String doregister(String account, String password, String name, String sex, String grade, String phone, Model Model) {
        if(!account.matches("^[a-zA-Z][a-zA-Z0-9_]{3,9}$")){
            Model.addAttribute("accountErrorMsg", "账号必须字母开头，长度为4到10字节，允许字母数字下划线");
            return "UserAction/register";
        }
            member member = new member();
            member.setMemberaccount(account);
            member.setMemberpassword(password);
            member.setMembername(name);
            member.setMembersex(sex);
            member.setMembergrade(grade);
            member.setMemberphone(phone);
            member.setMemberismember(2);
            member.setMembericon(null);
            member.setMemberidentity("游客");
            QueryWrapper<member> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("member_account", account);
            member existmember = this.loginService.getOne(queryWrapper);
            if (existmember != null) {
                Model.addAttribute("errorMsg", "注册失败，该账号已存在，请重新输入");
                return "UserAction/register";
            } else {
                this.loginService.save(member);
                return "redirect:/login";
            }
    }

    @RequestMapping("/personinform")
    public String personinform(){
        return "UserAction/personinform";
    }

    @RequestMapping("/updatePersonInform")
    public String updatePersonInform(HttpSession HttpSession,String name, String sex, String grade, String phone){
        member member=(member)HttpSession.getAttribute("member");
        member.setMembername(name);
        member.setMembersex(sex);
        member.setMembergrade(grade);
        member.setMemberphone(phone);
        this.loginService.updateById(member);
        HttpSession.setAttribute("member",member);
        return "UserAction/personinform";
    }

    @RequestMapping("/toChangePasswordPage")
    public String toChangePasswordPage(){
        return "UserAction/changePassword";
    }

    @RequestMapping("/changePassword")
    public String changePassword(String password,HttpSession HttpSession){
        member member=(member)HttpSession.getAttribute("member");
        member.setMemberpassword(password);
        this.loginService.updateById(member);
        HttpSession.removeAttribute("member");
        return "redirect:/index";
    }
}
