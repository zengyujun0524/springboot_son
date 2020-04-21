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
public class Equipment {
    @Id
    @ApiModelProperty(value = "设备ID(主键)", name = "equipmentId", dataType = "Integer")
    private Integer equipmentId;   //设备ID
    @ApiModelProperty(value = "设备名称", name = "equipmentName", dataType = "Integer")
    private String equipmentName;  //设备名称
    @ApiModelProperty(value = "设备描述", name = "equipmentDescribe", dataType = "Integer")
    private String equipmentDescribe; //设备描述
    @ApiModelProperty(value = "设备图片", name = "equipmentPictures", dataType = "String")
    private String equipmentPictures; //设备图片
    @ApiModelProperty(value = "设备绑定状态", name = "state", dataType = "Integer")
    private Integer state = 1; //设备绑定状态


}
