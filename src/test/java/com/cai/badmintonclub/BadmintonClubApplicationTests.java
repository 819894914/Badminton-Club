package com.cai.badmintonclub;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.mapper.findMessagesMapper;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.pojo.messages;
import com.cai.badmintonclub.pojo.messagesBoard;
import com.cai.badmintonclub.service.findMessagesService;
import com.cai.badmintonclub.service.loginService;
import com.cai.badmintonclub.service.messagesBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.util.*;

@SpringBootTest
class BadmintonClubApplicationTests {

    @Autowired
    private com.cai.badmintonclub.service.findMessagesService findMessagesService;

    @Autowired
    private com.cai.badmintonclub.mapper.findMessagesMapper findMessagesMapper;

    @Autowired
    private com.cai.badmintonclub.service.messagesBoardService messagesBoardService;

    @Autowired
    private com.cai.badmintonclub.service.loginService loginService;
    @Test
    void contextLoads() {
    }


    @Test
    void test(){
        List<messages> messagesList = this.findMessagesService.list(null);
        messagesList.forEach(System.out::println);
    }

    @Test
    void testPage(){
        QueryWrapper<messages> querNewsyWrapper=new QueryWrapper<>();
        querNewsyWrapper.eq("messages_kind","新闻");
        Page<messages> page=new Page<>();
        Page<messages> messagesPage=this.findMessagesService.getBaseMapper().selectPage(page,querNewsyWrapper);
        System.out.println(messagesPage.getPages());
        System.out.println(messagesPage.getTotal());
        System.out.println(messagesPage.getSize());
        System.out.println(messagesPage.getRecords());
    }
    @Test
    void testNotTourists(){
        QueryWrapper<member> queryWrapper=new QueryWrapper<>();
        queryWrapper.notIn("member_identity","游客");
        this.loginService.getBaseMapper().selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    void testFindMemberByName(){
        Page<member> page=new Page<>(1,10);
        QueryWrapper<member> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("member_name","三");
        Page<member> memberPage=this.loginService.getBaseMapper().selectPage(page,queryWrapper);
        List<member> memberList=memberPage.getRecords();
        memberList.forEach(System.out::println);
    }
}
