package com.cai.badmintonclub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.pojo.messagesBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface messagesBoardMapper extends BaseMapper<messagesBoard> {
    Page<messagesBoard> findAllMessages(IPage<messagesBoard> page);
    void releaseMessages(messagesBoard messagesBoard);
}
