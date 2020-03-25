/**
 * @author 曾俞钧
 * @date 2020/3/21 9:48
 * @version 1.0
 */
package com.example.springboot_son.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    private  Integer equipment_id;   //设备ID

    private  String  equipment_name;  //设备名称

    private  String  equipment_describe; //设备描述
    private  String  equipment_pictures; //设备图片
    private  Integer   state =1; //设备绑定状态


    public Equipment() {
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_describe() {
        return equipment_describe;
    }

    public void setEquipment_describe(String equipment_describe) {
        this.equipment_describe = equipment_describe;
    }

    public String getEquipment_pictures() {
        return equipment_pictures;
    }

    public void setEquipment_pictures(String equipment_pictures) {
        this.equipment_pictures = equipment_pictures;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
