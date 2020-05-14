package com.example.springboot_son.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * (StaticResources)实体类
 *
 * @author zengyujun
 * @since 2020-04-28 10:39:16
 */
@Data
public class StaticResources  {
    /**
    * 图片ip
    */
    private Integer pictureId;
    /**
    * 图片
    */
    private String pictureUrl;


}