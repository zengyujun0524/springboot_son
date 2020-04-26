package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.Application;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cengyujun on 2020/4/23 8:53 上午
 */
@Mapper
public interface ApplicationMapper {
    //获取版本信息
 Application getApp()throws  Exception;


}
