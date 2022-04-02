package com.cai.badmintonclub.service.impl;


import com.cai.badmintonclub.mapper.messagesMapper;
import com.cai.badmintonclub.pojo.messages;
import com.cai.badmintonclub.service.messagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class messagesserviceimpl implements messagesService {
    @Autowired
    private messagesMapper messagesMapper;
    public messages findbrief(){
        return this.messagesMapper.findbrief();
    }

    public void updatebrief(String messagescontent){
        this.messagesMapper.updatebrief(messagescontent);
    }

    public messages findknowledge(){
        return this.messagesMapper.findknowledge();
    }
    public void updateknowledge(String messagescontent){
        this.messagesMapper.updateknowledge(messagescontent);
    }

    public List<messages> findallnews(){
        this.messagesMapper.findallnews();
        return this.messagesMapper.findallnews();
    }

    public void addnews(String newstitle,String newscontent) {
        messages news=new messages();
        news.setMessagestitle(newstitle);
        news.setMessagescontent(newscontent);
        Date date = new Date();
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        news.setMessagesreleasedate(ft.format(date));
        this.messagesMapper.addnews(news);
    }

    public void deletenews(Integer newsid){
        this.messagesMapper.deletenews(newsid);
    }

    public void updatenews(String newstitle,String newscontent,int newsid) {
        messages news=new messages();
        news.setMessagesid(newsid);
        news.setMessagestitle(newstitle);
        news.setMessagescontent(newscontent);
        Date date = new Date();
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        news.setMessagesreleasedate(ft.format(date));
        this.messagesMapper.updatenews(news);
    }

    public messages findnewsbyid(int newsid){
        return this.messagesMapper.findnewsbyid(newsid);
    }

    public List<messages> findallnotices(){
        return this.messagesMapper.findallnotices();
    }

    public messages findnoticesbyid(int noticesid){
        return this.messagesMapper.findnoticesbyid(noticesid);
    }

    public void addnotices(String noticestitle,String noticescontent){
        messages notices=new messages();
        notices.setMessagestitle(noticestitle);
        notices.setMessagescontent(noticescontent);
        Date date = new Date();
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        notices.setMessagesreleasedate(ft.format(date));
        this.messagesMapper.addnotices(notices);
    }

    public void deletenotices(int noticesid){
        this.messagesMapper.deletenotices(noticesid);
    }

    public void updatenotices(String noticestitle,String noticescontent,int noticesid){
        messages notices=new messages();
        notices.setMessagesid(noticesid);
        notices.setMessagestitle(noticestitle);
        notices.setMessagescontent(noticescontent);
        Date date = new Date();
        SimpleDateFormat ft=new SimpleDateFormat ("yyyy-MM-dd");
        notices.setMessagesreleasedate(ft.format(date));
        this.messagesMapper.updatenotices(notices);
    }
}
