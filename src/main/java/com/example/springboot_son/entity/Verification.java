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
    @ApiModelProperty(value = "用户（ID）",name = "userId",dataType = "Integer")
    private   Integer  userId;
    @ApiModelProperty(value = "用户token",name = "userToken",dataType = "String")
    private   String userToken;
    @ApiModelProperty(value = "主键",name = "userGesture",dataType = "String")
    private  String userGesture;
    @ApiModelProperty(value = "绑定状态",name = "bindingState",dataType = "Integer")
    private  Integer bindingState;
    @ApiModelProperty(value = "推送token" ,name = "pushToken",dataType ="String" )
    private  String pushToken;


}