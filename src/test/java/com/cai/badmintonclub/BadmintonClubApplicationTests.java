package com.cai.badmintonclub;
import com.cai.badmintonclub.mapper.loginMapper;
import com.cai.badmintonclub.pojo.member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class BadmintonClubApplicationTests {

    @Autowired
    private com.cai.badmintonclub.mapper.loginMapper loginMapper;

    @Test
    void test01(){
        List<member> list01=this.loginMapper.findToAuditMember();
        list01.forEach(System.out::println);
    }

    @Test
    void test02(){
        System.out.println(this.loginMapper.findStatusByMemberId(10));
    }
}
