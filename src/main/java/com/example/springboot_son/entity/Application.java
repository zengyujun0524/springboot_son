package com.example.springboot_son.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Application)实体类
 *
 * @author zengyujun
 * @since 2020-04-23 10:11:40
 */
@Data


public class Application implements Serializable {
    private static final long serialVersionUID = -15997221938740983L;
    
    private Integer appId;
    /**
    * 版本号
    */
    private Integer versionNum;
    /**
    * 版本描述
    */
    private String describe;
    /**
    * 更新时间
    */
    private String createTime;
    /**
    * 是否强制更新(0：是、1：否）
    */
    private Integer mandatoryUpstring;
    /**
    * 版本名称
    */
    private String versionName;

    /**
     * 包名
     */
    private String apkAddress;


}