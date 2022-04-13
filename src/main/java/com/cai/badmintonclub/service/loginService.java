package com.cai.badmintonclub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cai.badmintonclub.pojo.member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface loginService extends IService<member> {
    void insertAdmissionApplication(Integer memberid);
    void refuseAdmissionApplication(Integer memberid);
    void approveAdmissionApplication(Integer memberid);
    List<member> findToAuditMember();
    List<member> findFaildMember();
    Integer findStatusByMemberId(Integer memberid);
}
