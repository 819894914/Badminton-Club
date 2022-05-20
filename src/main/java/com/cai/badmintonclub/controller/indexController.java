package com.cai.badmintonclub.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.pojo.messages;
import com.cai.badmintonclub.pojo.messagesBoard;
import com.cai.badmintonclub.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "社团前台页面控制类")
@Controller
public class indexController {
    @Autowired
    private messagesService messagesService;

    @Autowired
    private findMessagesService findMessagesService;

    @Autowired
    private loginService loginService;

    @Autowired
    private messagesBoardService messagesBoardService;

    @ApiOperation("跳转主页")
    @RequestMapping(value={"/","/index"})
    public ModelAndView index(HttpSession HttpSession){
        ModelAndView modelAndView=new ModelAndView();
        QueryWrapper<messages> queryNewsWrapper=new QueryWrapper<>();
        queryNewsWrapper.eq("messages_kind","新闻")
                        .orderByDesc("messages_id");
        List<messages> newsList = this.findMessagesService.list(queryNewsWrapper);
        QueryWrapper<messages> queryNoticesyWrapper=new QueryWrapper<>();
        queryNoticesyWrapper.eq("messages_kind","公告")
                            .orderByDesc("messages_id");
        List<messages> noticesList = this.findMessagesService.list(queryNoticesyWrapper);
        modelAndView.addObject("noticesList",noticesList);
        modelAndView.addObject("newsList",newsList);
        if (HttpSession.getAttribute("member")==null) {
            member member = new member();
            member.setMembername(null);
            member.setMemberidentity("游客");
            HttpSession.setAttribute("member",member);
        }
        modelAndView.setViewName("index");
        return  modelAndView;
    }



    @ApiOperation("跳转新闻页面")
    @RequestMapping("/news")
    public String news(Model model,@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        Page<messages> page=new Page<>(pn,10);
        QueryWrapper<messages> queryNewsWrapper=new QueryWrapper<>();
        queryNewsWrapper.eq("messages_kind","新闻")
                .orderByDesc("messages_id");
        Page<messages> NewsPage = this.findMessagesService.getBaseMapper().selectPage(page,queryNewsWrapper);
        model.addAttribute("page",NewsPage);
        return  "ClubHomepage/news";
    }

    @ApiOperation("跳转公告页面")
    @RequestMapping("notices")
    public String notices(Model model,@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        Page<messages> page=new Page<>(pn,10);
        QueryWrapper<messages> queryNoticesWrapper=new QueryWrapper<>();
        queryNoticesWrapper.eq("messages_kind","公告")
                .orderByDesc("messages_id");
        Page<messages> noticesPage = this.findMessagesService.getBaseMapper().selectPage(page,queryNoticesWrapper);
        model.addAttribute("page",noticesPage);
        return "ClubHomepage/notices";
    }
    @ApiOperation("跳转社团留言板")
    @RequestMapping("/messageboard")
    public String messageboard(Model model,@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        Page<messagesBoard> page=new Page<>(pn,10);
        Page<messagesBoard> messagesBoardPage=this.messagesBoardService.findAllMessages(page);
        model.addAttribute("page",messagesBoardPage);
        return "ClubHomepage/messageboard";
    }

    @ApiOperation("跳转羽毛球知识页面")
    @RequestMapping("knowledge")
    public ModelAndView knowledge(){
        ModelAndView modelAndView = new ModelAndView();
        messages knowledge=this.messagesService.findknowledge();
        modelAndView.addObject("knowledge",knowledge);
        modelAndView.setViewName("ClubHomepage/knowledge");
        return modelAndView;
    }

    @ApiOperation("跳转社团成员页面")
    @RequestMapping("/member")
    public ModelAndView member(){
        ModelAndView modelAndView=new ModelAndView();
        QueryWrapper<member> queryPersidentWrapper=new QueryWrapper<>();
        queryPersidentWrapper.eq("member_identity","社长");
        member president=this.loginService.getOne(queryPersidentWrapper);

        QueryWrapper<member> queryMinisterWrapper=new QueryWrapper<>();
        queryMinisterWrapper.eq("member_identity","部长");
        List<member> ministerList=this.loginService.getBaseMapper().selectList(queryMinisterWrapper);

        QueryWrapper<member> queryMemberWrapper=new QueryWrapper<>();
        queryMemberWrapper.eq("member_identity","会员");
        List<member> memberList=this.loginService.getBaseMapper().selectList(queryMemberWrapper);

        modelAndView.addObject("president",president);
        modelAndView.addObject("ministerList",ministerList);
        modelAndView.addObject("memberList",memberList);
        modelAndView.setViewName("ClubHomepage/member");
        return  modelAndView;
    }

    @ApiOperation("跳转社团简介页面")
    @RequestMapping("communityprofile")
    public ModelAndView communityprofile(){
        ModelAndView modelAndView = new ModelAndView();
        messages brief=this.messagesService.findbrief();
        modelAndView.addObject("brief",brief);
        modelAndView.setViewName("ClubHomepage/communityprofile");
        return modelAndView;
    }

    @ApiOperation("跳转加入我们页面")
    @RequestMapping("joinus")
    public String joinus(){
        return "ClubHomepage/joinus";
    }

    @ApiOperation("查询对应新闻或公告")
    @RequestMapping("/findMessageByIdAndKind")
    public ModelAndView findMessageByIdAndKind(String messagesId, String messagesKind){
       ModelAndView modelAndView=new ModelAndView();
        QueryWrapper<messages> queryWrapper=new QueryWrapper();
        queryWrapper.eq("messages_id",messagesId).eq("messages_kind",messagesKind);
        messages messages=this.findMessagesService.getOne(queryWrapper);
        modelAndView.addObject("messages",messages);
        modelAndView.setViewName("ClubHomepage/messagescontent");
        return modelAndView;
    }
}

