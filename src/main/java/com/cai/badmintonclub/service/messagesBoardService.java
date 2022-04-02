package com.cai.badmintonclub.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cai.badmintonclub.pojo.messagesBoard;

import java.util.List;

public interface messagesBoardService extends IService<messagesBoard> {
    Page<messagesBoard> findAllMessages(Page<messagesBoard> page);
    void releaseMessages(messagesBoard messagesBoard);
}
