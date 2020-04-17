package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (Verification)实体类
 *
 * @author makejava
 * @since 2020-04-16 09:30:29
 */
@Data
public class Verification {
 //
    @ApiModelProperty(value = "用户（ID）",name = "user_id",dataType = "Integer")
    private   Integer  user_id;
    @ApiModelProperty(value = "用户token",name = "user_token",dataType = "String")
    private   String user_token;
    @ApiModelProperty(value = "主键",name = "user_id",dataType = "String")
    private  String user_gesture;
    @ApiModelProperty(value = "绑定状态",name = "binding_state",dataType = "Integer")
    private  Integer binding_state;
    @ApiModelProperty(value = "推送token" ,name = "push_token",dataType ="String" )
    private  String push_token;


}