package com.example.springboot_son.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (VideoInfo)实体类
 *
 * @author zengyujun
 * @since 2020-04-24 17:06:52
 */
@Data
public class VideoInfo implements Serializable {
    private static final long serialVersionUID = 163038849192696986L;
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
    private Integer uploadTime;
    /**
    * 点赞数量
    */
    private Integer agreeNumber;
    /**
    * 封面url
    */
    private String pageUrl;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 作者头像
    */
    private String authorPictureUrl;
    /**
     * 转发数
     */
    private Integer forwardNumber;
    /**
     * 点赞数
     */
    private Integer commentNumber;


}