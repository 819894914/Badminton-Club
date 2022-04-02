package com.cai.badmintonclub.service;

import com.cai.badmintonclub.pojo.messages;
import org.springframework.ui.Model;

import java.util.List;

public interface messagesService {
    //----简介管理-------------
    messages findbrief();
    void updatebrief(String messagescontent);
    //----简介管理-------------

    //----知识管理-------------
    messages findknowledge();
    void updateknowledge(String messagescontent);
    //----知识管理-------------

    //----------新闻管理-----------------
    List<messages> findallnews();
    void addnews(String newstitle,String newscontent);
    void deletenews(Integer newsid);
    void updatenews(String newstitle,String newscontent,int newsid);
    messages findnewsbyid(int newsid);
    //----------新闻管理-----------------

    //----------公告管理-----------------
    List<messages> findallnotices();
    messages findnoticesbyid(int noticesid);
    void addnotices(String noticestitle,String noticescontent);
    void deletenotices(int noticesid);
    void updatenotices(String noticestitle,String noticescontent,int noticesid);
    //----------新闻管理-----------------
}
