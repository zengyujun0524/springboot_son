package com.example.springboot_son;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

        @SpringBootApplication
        @EnableSwagger2
        @EnableCaching
        @MapperScan(value ="com.example.springboot_son.mapper")
        public class SpringbootSonApplication  extends SpringBootServletInitializer {

            public static void main(String[] args) {


        SpringApplication.run(SpringbootSonApplication.class, args);
        // 打包部署要继承 extends SpringBootServletInitializer



    }

}
