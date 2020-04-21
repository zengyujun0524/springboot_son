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
    @ApiModelProperty(value = "主键", name = "userId", dataType = "Integer")
    private Integer userId;
    @ApiModelProperty(value = "用户姓名", name = "userName", dataType = "String")
    private String userName;
    @ApiModelProperty(value = "用户密码", name = "userPassword", dataType = "String")
    private String userPassword;
    @ApiModelProperty(value = "手机型号", name = "phoneModel", dataType = "String")
    private String phoneModel;
    @ApiModelProperty(value = "头像路径", name = "pictureUrl", dataType = "String")
    private String pictureUrl;
    @ApiModelProperty(value = "注册时间", name = "dataTime", dataType = "String")
    private String dataTime;
    @ApiModelProperty(value = "性别（0男，1女）", name = "userSex", dataType = "Integer")
    private Integer userSex;
    @ApiModelProperty(value = "手机号", name = "userPhone", dataType = "String")
    private String userPhone;


}
