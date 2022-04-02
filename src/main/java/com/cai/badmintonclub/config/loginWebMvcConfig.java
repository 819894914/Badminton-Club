package com.cai.badmintonclub.config;

import com.cai.badmintonclub.interceptor.adminInterceptor;
import com.cai.badmintonclub.interceptor.loginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class loginWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/css/**","/static/images/**","/static/videos/**","/static/js/**")
                .excludePathPatterns("/findMessageByIdAndKind")
                .excludePathPatterns("/","/index","/login","/joinus","/news","/notices","/knowledge","/communityprofile","/doLogin","/register","/doRegister","/loginOut","/personinform","/updatePersonInform","/changePassword","/index","/upload");
        registry.addInterceptor(new adminInterceptor())
                .addPathPatterns("/briefmanagement","/newsmanagement","/noticesmanagement","/joinmanagement","/newsdetails","/noticesdetails","/knowledgemanagement")
                .addPathPatterns("/updatebrief","updateknowledge","/addnews","/deletenews/{newsid}","/updatenews/{newsid}","/findnewsbyid/{newsid}","/addnotices","/deletenotices/{noticesid}")
                .addPathPatterns("/updatenotices/{noticesid}","findnoticesbyid/{noticesid}")
                .excludePathPatterns("/css/**","/images/**","/videos/**","/js/**")
                .excludePathPatterns("/","/index","/login","/joinus","/news","/notices","/knowledge","/communityprofile","/doLogin","/upload");
    }
}
