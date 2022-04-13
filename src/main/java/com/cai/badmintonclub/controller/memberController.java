package com.cai.badmintonclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.service.loginService;
import com.cai.badmintonclub.service.memberService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class memberController {
    @Autowired
    private memberService memberservice;

    @Autowired
    private loginService loginService;



    @ResponseBody
    @RequestMapping("/addmember")
    public String addmember(String addmembername, String addmembersex, String addmembergrade, String addmemberidentity, String addmemberphone, String addmemberaccount, String addmemberpassword) {
        QueryWrapper<member> queryAccountWrapper = new QueryWrapper<>();
        queryAccountWrapper.eq("member_account", addmemberaccount);
        boolean existsAccount = this.loginService.getBaseMapper().exists(queryAccountWrapper);

        QueryWrapper<member> queryPresidentWrapper = new QueryWrapper<>();
        queryPresidentWrapper.eq("member_identity", "社长");
        boolean flag=this.loginService.getBaseMapper().exists(queryPresidentWrapper);
        System.out.println("获取的账号为:"+addmemberaccount);
        if (addmemberaccount.equals(""))
            return "账号不能为空";
        else if(!addmemberaccount.matches("^[a-zA-Z][a-zA-Z0-9_]{3,9}$")){
            return "账号必须字母开头，长度为4到10字节，允许字母数字下划线";
        }
        if (existsAccount) {
            return "账号已存在";
        }

        if (addmemberpassword.equals(""))
            return "密码不能为空";
        else if (!addmemberpassword.matches("^[a-zA-Z0-9]{3,7}$")){
            return "密码长度必须为4到8字节，只允许字母数字";
        }

        if (addmembername.equals(""))
            return "名字不能为空";
        else if (!addmembername.matches("^[\u4e00-\u9fa5]{0,4}")){
            return "请输入正确的姓名，只允许汉字，长度为1到4位";
        }
        if (addmemberphone.equals(""))
            return "号码不能为空";
        else if (!addmemberphone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")){
            return "请输入正确的手机号码";
        }

        if (flag&addmemberidentity.equals("社长")) {
            return "社长仅能有一个";
        }
        else {
            this.memberservice.addmember(addmembername, addmembersex, addmembergrade, addmemberidentity, addmemberphone, addmemberaccount, addmemberpassword);
            return "添加成功";
        }
    }

    @ResponseBody
    @RequestMapping("/findmemberbyid")
    public member findmemberbyid(int memberid){
        return this.memberservice.findmemberbyid(memberid);
    }



    @ResponseBody
    @RequestMapping("/updatemember")
    public String updatemember(int memberid,String membername, String membersex, String membergrade, String memberidentity, String memberphone){
        QueryWrapper<member> queryPresidentWrapper = new QueryWrapper<>();
        queryPresidentWrapper.eq("member_identity", "社长");
        boolean flag=this.loginService.getBaseMapper().exists(queryPresidentWrapper);
        if (membername.equals(""))
            return "名字不能为空";
        else if (!membername.matches("^[\u4e00-\u9fa5]{0,4}")){
            return "请输入正确的姓名，只允许汉字，长度为1到4位";
        }
        if (memberphone.equals(""))
            return "号码不能为空";
        else if (!memberphone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$")){
            return "请输入正确的手机号码";
        }
        if (flag&memberidentity.equals("社长"))
            return "社长仅能有一个";
        else {
            this.memberservice.updatemember(memberid, membername, membersex, membergrade, memberidentity, memberphone);
            return "修改成功";
        }
    }

    @RequestMapping("/deletemember/{memberid}")
    public String deletemember(@PathVariable("memberid") int memberid){
        this.memberservice.deletemember(memberid);
        return "redirect:/membermanagement";
    }

    @RequestMapping("/findMemeberByName")
    public ModelAndView findMemeberByName(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String memberName){
        ModelAndView modelAndView=new ModelAndView();
        Page<member> page=new Page<>(pn,10);
        QueryWrapper<member> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("member_name",memberName)
                .notIn("member_identity","游客");
        Page<member> memberPage=this.loginService.getBaseMapper().selectPage(page,queryWrapper);
        modelAndView.addObject("page",memberPage);
        modelAndView.setViewName("Management/membermanagement");
        return modelAndView;
    }
}
