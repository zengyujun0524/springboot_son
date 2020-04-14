package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User {
    @ApiModelProperty(value = "主键",name = "user_id",dataType = "Integer")
    private    Integer user_id;
    @ApiModelProperty(value = "用户姓名",name = "user_name",dataType = "String")
    private    String user_name;
    @ApiModelProperty(value = "用户密码",name = "user_password",dataType ="String" )
    private  String user_password;
//    @ApiModelProperty(value = "用户的toke",name ="user_token" ,dataType = "String")
//    private  String user_token;
    @ApiModelProperty(value = "手机型号",name = "phone_model",dataType = "String")
    private  String phone_model;
    @ApiModelProperty(value = "头像路径",name = "picture_url",dataType ="String")
    private  String picture_url;
    @ApiModelProperty(value = "注册时间",name ="data_time",dataType = "String")
    private  String data_time;
    @ApiModelProperty(value = "性别（0男，1女）",name = "user_sex",dataType = "Integer")
    private  Integer user_sex;
    @ApiModelProperty(value = "手机号",name = "user_phone",dataType = "String")
    private  String user_phone;



}
