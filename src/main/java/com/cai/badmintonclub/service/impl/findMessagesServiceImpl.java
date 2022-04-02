package com.cai.badmintonclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cai.badmintonclub.mapper.findMessagesMapper;
import com.cai.badmintonclub.pojo.messages;
import com.cai.badmintonclub.service.findMessagesService;
import org.springframework.stereotype.Service;

@Service
public class findMessagesServiceImpl extends ServiceImpl<findMessagesMapper, messages> implements findMessagesService {
}
