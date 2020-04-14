/**
 * @author 曾俞钧
 * @date 2020/3/23 10:16
 * @version 1.0
 */
package com.example.springboot_son.vo;

import io.swagger.annotations.ApiModelProperty;


import javax.persistence.Column;

public class MainPageVo {

    // hi Stella   感谢大家一年来的照顾，在奥然是一段开心的时光，已身为奥然人自豪。因为2019的经济不景气，
    // 遇到了非常大的挑战和困然，都是您和老板在一直替我们承受着压力，作为奥然的一份子都是知道的，这4个月来我资金压力真的顶不住了,女朋友目前也还需要我照顾 ，朋友那边创业 我过去了，只有工作才能生活，希望您能理解。
    //之后的交接工作 我会尽全力配合，同时也希望奥然越来越好，大家都能万事胜意，万分感谢 。   谢谢 Stella 接下来的
    @ApiModelProperty(value = "设备ID")
    private  Integer equipment_id;   //设备ID
    private  String  equipment_name;  //设备名称
    private  String  equipment_describe; //设备描述
    private  String  equipment_pictures; //设备图片
    private  Integer   state; //设备绑定状态


    public MainPageVo(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
