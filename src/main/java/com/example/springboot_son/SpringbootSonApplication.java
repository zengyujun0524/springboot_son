package com.example.springboot_son;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.Executors;

@SpringBootApplication
        @EnableSwagger2
        @EnableCaching
        @EnableScheduling
        @MapperScan(value ="com.example.springboot_son.mapper")
        public  class SpringbootSonApplication  extends SpringBootServletInitializer implements SchedulingConfigurer {
            @Override
            public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
                //启动100个线程
                taskRegistrar.setScheduler(Executors.newScheduledThreadPool(100));
            }

            public static void main(String[] args) {

        SpringApplication.run(SpringbootSonApplication.class, args);

        // 打包部署要继承 extends SpringBootServletInitializer

            }


}
