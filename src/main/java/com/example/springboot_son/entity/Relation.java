/**
 * @author 曾俞钧
 * @date 2020/3/21 9:49
 * @version 1.0
 */
package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
//09ea5eb700258bc6652fef5a6871fa3833f28345
@Data
public class Relation {
    @ApiModelProperty(value = "标识列")
    private Integer relationId;
    @ApiModelProperty(value = "用户ID")
    private Integer userId;
    @ApiModelProperty(value = "设备ID")
    private Integer equipmentId;
    @ApiModelProperty(value = "绑定状态")
    private Integer state;


}
