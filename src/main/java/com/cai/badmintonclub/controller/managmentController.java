package com.cai.badmintonclub.controller;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cai.badmintonclub.pojo.*;
import com.cai.badmintonclub.service.findMessagesService;
import com.cai.badmintonclub.service.loginService;
import com.cai.badmintonclub.service.memberService;
import com.cai.badmintonclub.service.messagesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Api(tags = "社团管理后台控制类")
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

    @ApiOperation("跳转社团简介管理页面")
    @RequestMapping("briefmanagement")
    public ModelAndView briefmanagement(){
        ModelAndView modelAndView = new ModelAndView();
        messages brief=this.messagesService.findbrief();
        modelAndView.addObject("brief",brief);
        modelAndView.setViewName("Management/briefmanagement");
        return modelAndView;
    }
    @ApiOperation("更新社团简介操作")
    @PostMapping("updatebrief")
    public String updatebeirf(String messagescontent){
        this.messagesService.updatebrief(messagescontent);
        return "redirect:/briefmanagement";
    }
    @ApiOperation("跳转审批入社申请页面")
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

    @ApiOperation("通过用户入社申请操作")
    @RequestMapping("/doApprove/{memberid}")
    public String doApprove(@PathVariable("memberid") Integer memberId){
        this.loginService.approveAdmissionApplication(memberId);
        return "redirect:/joinmanagement";
    }
    @ApiOperation("拒绝用户入社申请")
    @RequestMapping("/refuse/{memberid}")
    public String refuse(@PathVariable("memberid") Integer memberId){
        this.loginService.refuseAdmissionApplication(memberId);
        return "redirect:/joinmanagement";
    }

    @ApiOperation("跳转羽毛球知识管理页面")
    @RequestMapping("knowledgemanagement")
    public ModelAndView knowledgemanagement(){
        ModelAndView modelAndView = new ModelAndView();
        messages knowledge=this.messagesService.findknowledge();
        modelAndView.addObject("knowledge",knowledge);
        modelAndView.setViewName("Management/knowledgemanagement");
        return modelAndView;
    }
    @ApiOperation("更新羽毛球知识操作")
    @PostMapping("updateknowledge")
    public String updateknowledge(String messagescontent){
        this.messagesService.updateknowledge(messagescontent);
        return "redirect:/knowledgemanagement";
    }

    @ApiOperation("跳转成员管理页面")
    @RequestMapping("/membermanagement")
    public String membermanagement(Model model, @RequestParam(value = "pn", defaultValue = "1") Integer pn){;
        Page<member> page=new Page<>(pn,10);
        QueryWrapper<member> queryWrapper=new QueryWrapper<>();
        queryWrapper.notIn("member_identity","游客");
        Page<member> memberPage=this.loginService.getBaseMapper().selectPage(page,queryWrapper);
        model.addAttribute("page",memberPage);
        return "Management/membermanagement";
    }

    @ApiOperation("跳转新闻管理页面")
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

    @ApiOperation("添加新闻操作")
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

    @ApiOperation("删除新闻操作")
    @RequestMapping("deletenews/{newsid}")
    public String deletenews(@PathVariable("newsid") Integer newsid){
        this.messagesService.deletenews(newsid);
        return "redirect:/newsmanagement";
    }

    @ApiOperation("更新新闻操作")
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

    @ApiOperation("获取对应ID的新闻内容")
    @RequestMapping("/findnewsbyid/{newsid}")
    public ModelAndView findnewsbyid(@PathVariable("newsid") int newsid){
        ModelAndView modelAndView=new ModelAndView();
        messages prenews=this.messagesService.findnewsbyid(newsid);
        modelAndView.addObject("prenews",prenews);
        modelAndView.setViewName("Management/newsdetails");
        return modelAndView;
    }

    @ApiOperation("跳转公告页面")
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

    @ApiOperation("添加公告操作")
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

    @ApiOperation("删除公告操作")
    @RequestMapping("deletenotices/{noticesid}")
    public String deletenotices(@PathVariable("noticesid") Integer noticesid){
        this.messagesService.deletenotices(noticesid);
        return "redirect:/noticesmanagement";
    }


    @ApiOperation("更新公告操作")
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

    @ApiOperation("获取对应ID的公告内容")
    @RequestMapping("/findnoticesbyid/{noticesid}")
    public ModelAndView findnoticesbyid(@PathVariable("noticesid") int noticesid){
        ModelAndView modelAndView=new ModelAndView();
        messages prenotices=this.messagesService.findnoticesbyid(noticesid);
        modelAndView.addObject("prenotices",prenotices);
        modelAndView.setViewName("Management/noticesdetails");
        return modelAndView;
    }

    @ApiOperation("查找新闻操作")
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

    @ApiOperation("查询公告操作")
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

    @ApiOperation("富文本插入图片接口")
    @ResponseBody
    @RequestMapping(value = "/upload-img",method = RequestMethod.POST)
    public String updateImages(@RequestParam("fileName") MultipartFile file){
        ReturnImages returnImages = new ReturnImages();
        returnImages.setErrno("0");
        List<Map> imageList = new ArrayList();
        String imagesJson = null;
        try{
                InputStream inputStream = file.getInputStream();
                String uploadPath = "./src/main/resources/static/upload";
                String filename = Calendar.getInstance().getTimeInMillis()+".jpg";
                File newfile = new File(uploadPath, filename);
                FileUtils.copyInputStreamToFile(inputStream, newfile);
                String url = "http://localhost:8082/images/" + filename;
                Map<String,String> map = new HashMap();
                map.put("url",url);
                imageList.add(map);
                returnImages.setData(imageList);
                imagesJson = JSON.toJSONString(returnImages);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagesJson;
    }

}
