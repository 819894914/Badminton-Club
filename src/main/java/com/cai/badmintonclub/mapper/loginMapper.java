package com.cai.badmintonclub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cai.badmintonclub.pojo.member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface loginMapper extends BaseMapper<member> {
}
