package com.cai.badmintonclub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cai.badmintonclub.pojo.member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface loginMapper extends BaseMapper<member> {
    void insertAdmissionApplication(Integer memberid);
    void refuseAdmissionApplication(Integer memberid);
    void approveAdmissionApplication(Integer memberid);
    void updateIsMember(Integer memberid);
    List<member> findToAuditMember();
    List<member> findFaildMember();
    Integer findStatusByMemberId(Integer memberid);
}
