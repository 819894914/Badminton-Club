package com.cai.badmintonclub.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cai.badmintonclub.mapper.messagesBoardMapper;
import com.cai.badmintonclub.pojo.messagesBoard;
import com.cai.badmintonclub.service.messagesBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messagesBoardServiceImpl extends ServiceImpl<messagesBoardMapper,messagesBoard> implements messagesBoardService {

    @Autowired
    private messagesBoardMapper messagesBoardMapper;

    public Page<messagesBoard> findAllMessages(Page<messagesBoard> page){
        return this.messagesBoardMapper.findAllMessages(page);
    }
    public void releaseMessages(messagesBoard messagesBoard){
        this.messagesBoardMapper.releaseMessages(messagesBoard);
    }
}
