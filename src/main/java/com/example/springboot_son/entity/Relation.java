/**
 * @author 曾俞钧
 * @date 2020/3/21 9:49
 * @version 1.0
 */
package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;

@Entity
public class Relation {
    @ApiModelProperty(value = "标识列")
     private Integer relation_id;
    @ApiModelProperty(value = "用户ID")
    private Integer user_id;
    @ApiModelProperty(value = "设备ID")
    private Integer equipment_id;
    @ApiModelProperty(value = "绑定状态")
    private Integer state;

    public Relation() {

    }

    public Integer getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(Integer relation_id) {
        this.relation_id = relation_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
