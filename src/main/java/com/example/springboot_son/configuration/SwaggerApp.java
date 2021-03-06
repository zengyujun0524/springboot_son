package com.example.springboot_son.configuration;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by bianxh on 2019/1/21.
 */
@Configuration
@EnableSwagger2
public class SwaggerApp {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot_son.web"))
                .paths(PathSelectors.any())
                .build();
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("把把科技API接口文档")
                //创建人 tt              // .contact(new Contact("carry", "http://blog.bianxh.top/", ""))
                //版本号
                .version("1.0")
                //描述
                .description("参数 state（1：未绑定，2：已绑定）  sex（1：男 ,2：女）  result(1:新用户 2：老用户) binding_state:(0.未设置手势密码 ，1：已设置)测试验证码：888888")

                .build();
    }
}