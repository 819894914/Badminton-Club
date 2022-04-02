package com.cai.badmintonclub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cai.badmintonclub.pojo.messages;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface findMessagesMapper extends BaseMapper<messages> {
}
