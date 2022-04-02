package com.cai.badmintonclub.interceptor;

import com.cai.badmintonclub.pojo.member;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        member member=(member)session.getAttribute("member");
        if((member.getMemberidentity().equals("管理员"))){
            return true;
        }
        else {
            return false;
        }
    }
}
