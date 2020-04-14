/**
 * @author 曾俞钧
 * @date 2020/3/21 9:48
 * @version 1.0
 */
package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "equipment")
public class Equipment { //
    @Id
    @ApiModelProperty(value = "设备ID(主键)",name = "user_id",dataType = "Integer")
    private  Integer equipment_id;   //设备ID
    @ApiModelProperty(value = "设备名称",name = "equipment_name",dataType = "Integer")
    private  String  equipment_name;  //设备名称
    @ApiModelProperty(value = "设备描述",name = "equipment_describe",dataType = "Integer")
    private  String  equipment_describe; //设备描述
    @ApiModelProperty(value = "设备图片",name = "equipment_pictures",dataType = "String")
    private  String  equipment_pictures; //设备图片
    @ApiModelProperty(value = "设备绑定状态",name = "state",dataType = "Integer")
    private  Integer   state =1; //设备绑定状态




  }
