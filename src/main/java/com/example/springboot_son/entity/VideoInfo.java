package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "视频ID",name = "videoId",dataType = "Long")
    private Long videoId;
    @ApiModelProperty(value = "视频地址",name = "videoUrl",dataType = "String")
    private String videoUrl;
    @ApiModelProperty(value = "视频地址",name = "videoUrl",dataType = "String")
    private String videoTitle;
    /**
    * 视频描述
    */
    private String videoDescribe;
    @ApiModelProperty(value = "更新时间",name = "uploadTime",dataType = "Integer")
    private Integer uploadTime;
    @ApiModelProperty(value = "点赞数量",name = "agreeNumber",dataType = "Integer")
    private Integer agreeNumber;
    @ApiModelProperty(value = "封面路径",name = "pageUrl",dataType = "String")
    private String pageUrl;
    @ApiModelProperty(value = "用户ID",name = "videoUrl",dataType = "String")
    private Integer userId;
    @ApiModelProperty(value = "作者头像",name = "authorPictureUrl",dataType = "String")
    private String authorPictureUrl;
    @ApiModelProperty(value = "转发数",name = "forwardNumber",dataType = "Integer")
    private Integer forwardNumber;
    @ApiModelProperty(value = "点赞数",name = "commentNumber",dataType = "Integer")
    private Integer commentNumber;


}