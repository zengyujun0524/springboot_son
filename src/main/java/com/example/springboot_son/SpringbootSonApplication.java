package com.example.springboot_son;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value ="com.example.springboot_son.mapper")
public class SpringbootSonApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSonApplication.class, args);
        // 打包部署要继承 extends SpringBootServletInitializer


    }

}
