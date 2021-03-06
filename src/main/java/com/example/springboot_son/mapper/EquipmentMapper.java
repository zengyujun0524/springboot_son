/**
 * @author 曾俞钧
 * @date 2020/3/21 14:25
 * @version 1.0
 */
package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.Equipment;

import com.example.springboot_son.vo.EquipmentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentMapper {
   //进入主界面获取设备信息
    List<Equipment> equipmentInfo(@Param("userId")Integer userId) throws Exception;
    //查询用户绑定设备
    List<EquipmentVo>bindingDevice(@Param("userId") Integer userId, Integer equipmentId) throws Exception;
    //修改用户名称
    int modifyName (String equipmentName,Integer updateTime,Integer relationId)throws Exception;



}
