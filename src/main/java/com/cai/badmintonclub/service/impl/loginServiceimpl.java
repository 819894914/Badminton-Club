package com.cai.badmintonclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cai.badmintonclub.mapper.loginMapper;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class loginServiceimpl extends ServiceImpl<loginMapper, member> implements loginService {

    @Autowired
    private loginMapper loginMapper;

    public void insertAdmissionApplication(Integer memberid){
        this.loginMapper.insertAdmissionApplication(memberid);
    }
    public void refuseAdmissionApplication(Integer memberid){
        this.loginMapper.refuseAdmissionApplication(memberid);
    }
    public void approveAdmissionApplication(Integer memberid){
        this.loginMapper.approveAdmissionApplication(memberid);
        this.loginMapper.updateIsMember(memberid);
    }

    public List<member> findToAuditMember(){
        return this.loginMapper.findToAuditMember();
    }

    public List<member> findFaildMember(){
        return this.loginMapper.findFaildMember();
    }
    public Integer findStatusByMemberId(Integer memberid){
        return this.loginMapper.findStatusByMemberId(memberid);
    }

}
