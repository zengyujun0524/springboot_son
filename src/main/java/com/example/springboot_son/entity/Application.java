package com.example.springboot_son.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Application)实体类
 *
 * @author makejava
 * @since 2020-04-16 09:05:46
 */
@Data
public class Application implements Serializable {
    private static final long serialVersionUID = -68873280365977231L;
    
    private Object appId;
    
    private String versionNum;
    
    private String describe;
    
    private Date createTime;
    /**
    * 是否强制更新(0：是、1：否）
    */
    private String mandatoryUpstring;
    
    private String versionName;

}