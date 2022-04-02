package com.cai.badmintonclub.controller;

import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.pojo.messagesBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class messagesBoardController {
    @Autowired
    private com.cai.badmintonclub.service.messagesBoardService messagesBoardService;

    @RequestMapping("/releaseMessages")
    public String releaseMessages(HttpSession HttpSession,String messagesContent){
        member member=(member)HttpSession.getAttribute("member");
        String name=member.getMembername();
        Date date = new Date();
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        messagesBoard messagesBoard=new messagesBoard();
        messagesBoard.setMemberId(member.getMemberid());
        messagesBoard.setMessagesContent(messagesContent);
        messagesBoard.setMessagesReleaseDate(ft.format(date));
        this.messagesBoardService.releaseMessages(messagesBoard);
        return "redirect:/messageboard";
    }
}
