package com.example.springboot_son.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (VideoInfo)实体类
 *
 * @author makejava
 * @since 2020-04-21 11:54:07
 */
@Data
public class VideoInfo implements Serializable {
    private static final long serialVersionUID = -41065126602010343L;
    /**
    * 视频ID
    */
    private Long videoId;
    /**
    * 视频地址
    */
    private String videoUrl;
    /**
    * 视频标题
    */
    private String videoTitle;
    /**
    * 视频描述
    */
    private String videoDescribe;
    /**
    * 更新时间
    */
    private String uploadTime;
    /**
    * 点赞数量
    */
    private String agreeNumber;
    /**
    * 封面url
    */
    private String pageUrl;
    /**
    * 用户id
    */
    private Integer userId;


}