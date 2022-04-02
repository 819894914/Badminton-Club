package com.cai.badmintonclub.service.impl;

import com.cai.badmintonclub.mapper.memberMapper;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.service.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class memberserviceimpl implements memberService {

    @Autowired
    private memberMapper memberMapper;


    @Override
    public boolean dologin(Integer memberaccount,String memberpassword){
        return true;
    }

    public List<member> findallmember(){
        List<member> memberList=this.memberMapper.findallmember();
        return  memberList;
    }
    public member findmemberbyid(int memberid){
        return this.memberMapper.findmemberbyid(memberid);
    }

    public void addmember(String membername, String membersex, String membergrade, String memberidentity, String memberphone, String memberaccount, String memberpassword){
        member member=new member();
        member.setMembername(membername);
        member.setMembersex(membersex);
        member.setMemberidentity(memberidentity);
        member.setMemberphone(memberphone);
        member.setMembergrade(membergrade);
        member.setMemberaccount(memberaccount);
        member.setMemberpassword(memberpassword);
        this.memberMapper.addmember(member);
    }

    @Override
    public void updatemember(int memebrid,String membername, String membersex, String membergrade, String memberidentity, String memberphone) {
        member member=new member();
        member.setMemberid(memebrid);
        member.setMembername(membername);
        member.setMembersex(membersex);
        member.setMembergrade(membergrade);
        member.setMemberphone(memberphone);
        member.setMemberidentity(memberidentity);
        this.memberMapper.updatemember(member);
    }

    @Override
    public void deletemember(int memberid) {
        this.memberMapper.deletemember(memberid);
    }
}
