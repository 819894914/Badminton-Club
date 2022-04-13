package com.cai.badmintonclub.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.pojo.member;
import com.cai.badmintonclub.pojo.messages;
import com.cai.badmintonclub.service.findMessagesService;
import com.cai.badmintonclub.service.loginService;
import com.cai.badmintonclub.service.memberService;
import com.cai.badmintonclub.service.messagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class managmentController {

    @Autowired
    private memberService memberService;

    @Autowired
    private messagesService messagesService;

    @Autowired
    private findMessagesService findMessagesService;

    @Autowired
    private loginService loginService;

    @RequestMapping("briefmanagement")
    public ModelAndView briefmanagement(){
        ModelAndView modelAndView = new ModelAndView();
        messages brief=this.messagesService.findbrief();
        modelAndView.addObject("brief",brief);
        modelAndView.setViewName("Management/briefmanagement");
        return modelAndView;
    }

    @PostMapping("updatebrief")
    public String updatebeirf(String messagescontent){
        this.messagesService.updatebrief(messagescontent);
        return "redirect:/briefmanagement";
    }

    @RequestMapping("/joinmanagement")
    public ModelAndView joinmanagement(){
        ModelAndView modelAndView=new ModelAndView();
        List<member> toAuditMemberList=this.loginService.findToAuditMember();
        List<member> faildMemberList=this.loginService.findFaildMember();
        modelAndView.addObject("toAuditMemberList",toAuditMemberList);
        modelAndView.addObject("faildMemberList",faildMemberList);
        modelAndView.setViewName("Management/joinmanagement");
        return modelAndView;
    }


    @RequestMapping("/doApprove/{memberid}")
    public String doApprove(@PathVariable("memberid") Integer memberId){
        this.loginService.approveAdmissionApplication(memberId);
        return "redirect:/joinmanagement";
    }

    @RequestMapping("/refuse/{memberid}")
    public String refuse(@PathVariable("memberid") Integer memberId){
        this.loginService.refuseAdmissionApplication(memberId);
        return "redirect:/joinmanagement";
    }


    @RequestMapping("knowledgemanagement")
    public ModelAndView knowledgemanagement(){
        ModelAndView modelAndView = new ModelAndView();
        messages knowledge=this.messagesService.findknowledge();
        modelAndView.addObject("knowledge",knowledge);
        modelAndView.setViewName("Management/knowledgemanagement");
        return modelAndView;
    }

    @PostMapping("updateknowledge")
    public String updateknowledge(String messagescontent){
        this.messagesService.updateknowledge(messagescontent);
        return "redirect:/knowledgemanagement";
    }



    @RequestMapping("/membermanagement")
    public String membermanagement(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn){;
        Page<member> page=new Page<>(pn,10);
        QueryWrapper<member> queryWrapper=new QueryWrapper<>();
        queryWrapper.notIn("member_identity","游客");
        Page<member> memberPage=this.loginService.getBaseMapper().selectPage(page,queryWrapper);
        model.addAttribute("page",memberPage);
        return "Management/membermanagement";
    }


    @RequestMapping("/newsmanagement")
    public String newsmanagement(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn){
        Page<messages> page=new Page<>(pn,10);
        QueryWrapper<messages> queryNewsWrapper=new QueryWrapper<>();
        queryNewsWrapper.eq("messages_kind","新闻");
        Page<messages> newsPage=this.findMessagesService.getBaseMapper().selectPage(page,queryNewsWrapper);
        model.addAttribute("flag",1);
        model.addAttribute("page",newsPage);
        return "Management/newsmanagement";
    }

    @ResponseBody
    @RequestMapping("/addnews")
    public String addnews(String newstitle,String newscontent){
        if (newstitle.equals("")) {
            return "标题不能为空";
        }

        if (newscontent.equals("")){
            return "内容不能为空";
        }
        else {
            this.messagesService.addnews(newstitle, newscontent);
            return "添加成功";
        }
    }



    @RequestMapping("deletenews/{newsid}")
    public String deletenews(@PathVariable("newsid") Integer newsid){
        this.messagesService.deletenews(newsid);
        return "redirect:/newsmanagement";
    }


    @ResponseBody
    @RequestMapping("/updatenews")
    public String updatenews(String newstitle, String newscontent, Integer newsid){
        if (newstitle.equals("")){
            return "标题不能为空";
        }
        if (newscontent.equals("")){
            return "内容不能为空";
        }
        else{
            this.messagesService.updatenews(newstitle,newscontent,newsid);
            return "修改成功";
        }
    }

    @RequestMapping("/findnewsbyid/{newsid}")
    public ModelAndView findnewsbyid(@PathVariable("newsid") int newsid){
        ModelAndView modelAndView=new ModelAndView();
        messages prenews=this.messagesService.findnewsbyid(newsid);
        modelAndView.addObject("prenews",prenews);
        modelAndView.setViewName("Management/newsdetails");
        return modelAndView;
    }


    @RequestMapping("noticesmanagement")
    public String noticesmanagement(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn){
        Page<messages> page=new Page<>(pn,10);
        QueryWrapper<messages> queryNoticesWrapper=new QueryWrapper<>();
        queryNoticesWrapper.eq("messages_kind","公告");
        Page<messages> noticesPage=this.findMessagesService.getBaseMapper().selectPage(page,queryNoticesWrapper);
        model.addAttribute("flag",1);
        model.addAttribute("page",noticesPage);
        return "Management/noticesmanagement";
    }



    @ResponseBody
    @RequestMapping("/addnotices")
    public String addnotices(String noticestitle,String noticescontent){
        if (noticestitle.equals("")){
            return "标题不能为空";
        }
        if (noticescontent.equals("")){
            return "内容不能为空";
        }
        else {
            this.messagesService.addnotices(noticestitle,noticescontent);
            return "添加成功";
        }
    }

    @RequestMapping("deletenotices/{noticesid}")
    public String deletenotices(@PathVariable("noticesid") Integer noticesid){
        this.messagesService.deletenotices(noticesid);
        return "redirect:/noticesmanagement";
    }


    @ResponseBody
    @RequestMapping("/updatenotices")
    public String updatenotices(String noticestitle, String noticescontent,Integer noticesid){
        if (noticestitle.equals("")){
            return "标题不能为空";
        }
        if (noticescontent.equals("")){
            return "内容不能为空";
        }
        else {
            this.messagesService.updatenotices(noticestitle, noticescontent, noticesid);
            return "修改成功";
        }
    }

    @RequestMapping("/findnoticesbyid/{noticesid}")
    public ModelAndView findnoticesbyid(@PathVariable("noticesid") int noticesid){
        ModelAndView modelAndView=new ModelAndView();
        messages prenotices=this.messagesService.findnoticesbyid(noticesid);
        modelAndView.addObject("prenotices",prenotices);
        modelAndView.setViewName("Management/noticesdetails");
        return modelAndView;
    }

    @RequestMapping("findNewsByTitle")
    public ModelAndView findMessagesByTitle(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String newsTitle){
        ModelAndView modelAndView=new ModelAndView();
        Page page=new Page(pn,10);
        QueryWrapper<messages> queryWrapper=new QueryWrapper();
        queryWrapper.eq("messages_kind","新闻")
                    .like("messages_title",newsTitle);
        Page<messages> newsPage=this.findMessagesService.getBaseMapper().selectPage(page,queryWrapper);
        modelAndView.addObject("newsTitle",newsTitle);
        modelAndView.addObject("flag","2");
        modelAndView.addObject("page",newsPage);
        modelAndView.setViewName("Management/newsmanagement");
        return modelAndView;
    }

    @RequestMapping("findNoticesByTitle")
    public ModelAndView findNoticesByTitle(@RequestParam(value = "pn", defaultValue = "1") Integer pn,String noticesTitle){
        ModelAndView modelAndView=new ModelAndView();
        Page page=new Page(pn,10);
        QueryWrapper<messages> queryWrapper=new QueryWrapper();
        queryWrapper.eq("messages_kind","公告")
                .like("messages_title",noticesTitle);
        Page<messages> newsPage=this.findMessagesService.getBaseMapper().selectPage(page,queryWrapper);
        modelAndView.addObject("noticesTitle",noticesTitle);
        modelAndView.addObject("flag","2");
        modelAndView.addObject("page",newsPage);
        modelAndView.setViewName("Management/noticesmanagement");
        return modelAndView;
    }
}
