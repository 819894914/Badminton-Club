package com.cai.badmintonclub.service;


import com.cai.badmintonclub.pojo.member;

import java.util.List;

public interface memberService {
    public boolean dologin(Integer memberaccount,String memberpassword);
    public List<member> findallmember();
    public member findmemberbyid(int memberid);
    public void addmember(String membername, String membersex, String membergrade, String memberidentity, String memberphone,String memberaccount, String memberpassword);
    public void updatemember(int memberid,String membername, String membersex, String membergrade, String memberidentity, String memberphone);
    public void deletemember(int memberid);
}
