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
    @ApiModelProperty(value = "主键",name = "userId",dataType = "Integer")
    private    Integer userId;
    @ApiModelProperty(value = "用户姓名",name = "userName",dataType = "String")
    private    String userName;
    @ApiModelProperty(value = "用户密码",name = "userPassword",dataType ="String" )
    private  String userPassword;
    //    @ApiModelProperty(value = "用户的toke",name ="userToken" ,dataType = "String")
//    private  String userToken;
    @ApiModelProperty(value = "手机型号",name = "phoneModel",dataType = "String")
    private  String phoneModel;
    @ApiModelProperty(value = "头像路径",name = "pictureUrl",dataType ="String")
    private  String pictureUrl;
    @ApiModelProperty(value = "注册时间",name ="dataTime",dataType = "String")
    private  String dataTime;
    @ApiModelProperty(value = "性别（0男，1女）",name = "userSex",dataType = "Integer")
    private  Integer userSex;
    @ApiModelProperty(value = "手机号",name = "userPhone",dataType = "String")
    private  String userPhone;
    @ApiModelProperty(value = "用户token",name = "userToken",dataType = "String")
    private   String userToken;
    @ApiModelProperty(value = "主键",name = "userId",dataType = "String")
    private  String userGesture;
    @ApiModelProperty(value = "绑定状态",name = "bindingState",dataType = "Integer")
    private  Integer bindingState;
    @ApiModelProperty(value = "登入或者注册",name = "result",dataType = "Integer")
    private  Integer result;

    public UserVo(Integer userId, String userName, String userPassword, String phoneModel, String pictureUrl, String dataTime, Integer userSex, String userPhone, String userToken, String userGesture, Integer bindingState, Integer result) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneModel = phoneModel;
        this.pictureUrl = pictureUrl;
        this.dataTime = dataTime;
        this.userSex = userSex;
        this.userPhone = userPhone;
        this.userToken = userToken;
        this.userGesture = userGesture;
        this.bindingState = bindingState;
        this.result = result;
    }
}
