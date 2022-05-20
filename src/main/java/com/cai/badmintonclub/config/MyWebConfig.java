package com.cai.badmintonclub.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    // 配置虚拟路径映射访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射图片保存地址
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:D:\\IDEA\\Projects\\Badminton Club\\src\\main\\resources\\static\\upload\\");
    }
}