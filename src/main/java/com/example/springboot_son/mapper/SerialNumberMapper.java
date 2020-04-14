package com.example.springboot_son.mapper;

import com.example.springboot_son.dto.HeadsetByAct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @version 1.0
 * @date 2020/4/3 1:48 下午
 */
@Mapper
public interface SerialNumberMapper {
    //激活设备
    Integer actEquipment(HeadsetByAct headsetByAct);
    //查询设备
    HeadsetByAct getEquInfo(@Param("serial_number") String SerialNumber);


}
