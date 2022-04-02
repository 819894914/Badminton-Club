package com.cai.badmintonclub.mapper;

import com.cai.badmintonclub.pojo.messages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface messagesMapper {
    //-----社团简介管理功能-----
    messages findbrief();
    void updatebrief(String messagescontent);
    //-------------------------

    //羽毛球知识管理功能
    messages findknowledge();
    void updateknowledge(String messagescontent);
    //-------------------------

    //新闻管理功能
    List<messages> findallnews();
    messages findnewsbyid(int id);
    void addnews(messages news);
    void deletenews(int newsid);
    void updatenews(messages news);
    //-------------------------

    //公告管理功能
    List<messages> findallnotices();
    messages findnoticesbyid(int noticesid);
    void addnotices(messages notices);
    void deletenotices(int noticesid);
    void updatenotices(messages notices);
    //-------------------------
}
