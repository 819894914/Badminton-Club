package com.cai.badmintonclub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cai.badmintonclub.mapper.loginMapper;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.service.loginService;
import org.springframework.stereotype.Service;

@Service
public class loginServiceimpl extends ServiceImpl<loginMapper, member> implements loginService {

}
