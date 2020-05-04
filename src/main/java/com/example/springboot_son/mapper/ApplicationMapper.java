package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.Application;
import com.example.springboot_son.entity.StaticResources;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by cengyujun on 2020/4/23 8:53 上午
 */
@Mapper
public interface ApplicationMapper {
    //获取版本信息
 Application getApp()throws  Exception;
 //轮播图
List<StaticResources> listPicture()throws Exception;


}
