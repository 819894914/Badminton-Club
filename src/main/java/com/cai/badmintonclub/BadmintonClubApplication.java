package com.cai.badmintonclub;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan(value = "com.cai.badmintonclub.mapper")
public class BadmintonClubApplication {
    public static void main(String[] args) {
        SpringApplication.run(BadmintonClubApplication.class,args);
    }
}

