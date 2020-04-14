/**
 * @author 曾俞钧
 * @date 2020/3/21 14:25
 * @version 1.0
 */
package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.Equipment;
import com.example.springboot_son.vo.MainPageVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentMapper {
   //进入主界面获取设备信息  // 我是要当老板
    List<Equipment> equipmentInfo(@Param("user_id")Integer user_id) throws Exception;
    //查询用户绑定设备
    List<Equipment>bindingDevice(@Param("user_id")Integer user_id,@Param("equipment_id") Integer equipment_id) throws Exception;



}
