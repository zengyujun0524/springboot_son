/**
 * @author 曾俞钧
 * @date 2020/3/23 10:16
 * @version 1.0
 */
package com.example.springboot_son.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.persistence.Column;
@Data
public class EquipmentVo {

    //
    @ApiModelProperty(value = "设备ID")
    private  Integer equipmentId;   //设备ID
    private  String  equipmentName;  //设备名称
    private  String  equipmentDescribe; //设备描述
    private  String  equipmentPictures; //设备图片
    private  Integer   state; //设备绑定状态
    private  Integer relationId;//列表id

}
