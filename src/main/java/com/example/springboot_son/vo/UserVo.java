package com.example.springboot_son.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author 曾俞钧
 * @version 1.0
 * @date 2020/4/1 11:55 上午
 */
@Entity
@Data
public class UserVo {
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
    @ApiModelProperty(value = "用户token",name = "user_token",dataType = "String")
    private   String user_token;
    @ApiModelProperty(value = "主键",name = "user_id",dataType = "String")
    private  String user_gesture;
    @ApiModelProperty(value = "绑定状态",name = "binding_state",dataType = "Integer")
    private  Integer binding_state;
    @ApiModelProperty(value = "登入或者注册",name = "result",dataType = "Integer")
    private  Integer result;

    public UserVo(Integer user_id, String user_name, String user_password, String phone_model, String picture_url, String data_time, Integer user_sex, String user_phone, String user_token, String user_gesture, Integer binding_state, Integer result) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.phone_model = phone_model;
        this.picture_url = picture_url;
        this.data_time = data_time;
        this.user_sex = user_sex;
        this.user_phone = user_phone;
        this.user_token = user_token;
        this.user_gesture = user_gesture;
        this.binding_state = binding_state;
        this.result = result;
    }
}
