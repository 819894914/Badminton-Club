package com.cai.badmintonclub.mapper;

import com.cai.badmintonclub.pojo.member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface memberMapper {
    List<member> findallmember();
    member findmemberbyid(int memberid);
    void addmember(member member);
    void updatemember(member member);
    void deletemember(int memberid);
}
