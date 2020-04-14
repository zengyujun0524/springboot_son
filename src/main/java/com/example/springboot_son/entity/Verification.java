package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 曾俞钧
 * @version 1.0
 * @date 2020/3/31 6:09 下午
 */
@Data
public class Verification {
  @ApiModelProperty(value = "用户（ID）",name = "user_id",dataType = "Integer")
  private   Integer  user_id;
  @ApiModelProperty(value = "用户token",name = "user_token",dataType = "String")
  private   String user_token;
  @ApiModelProperty(value = "主键",name = "user_id",dataType = "String")
  private  String user_gesture;
  @ApiModelProperty(value = "绑定状态",name = "binding_state",dataType = "Integer")
  private  Integer binding_state;
}
