package com.example.springboot_son.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (VideoAgree)实体类
 *
 * @author zengyujun
 * @since 2020-04-23 17:25:58
 */
@Data
public class VideoAgree implements Serializable {
    private static final long serialVersionUID = 355120469107242779L;
    @ApiModelProperty(value = "视频ID",name = "videoId",dataType = "Integer")
    private Integer videoId;
    @ApiModelProperty(value = "视频ID",name = "videoId",dataType = "Integer")
    private Integer agreeUserId;
    @ApiModelProperty(value = "是否点赞(0:no ，1：ok)",name = "isRsult",dataType = "Integer")
    private Integer isRsult;
    @ApiModelProperty(value = "更新时间",name = "modifyTime",dataType = "Integer")
    private Integer modifyTime;

}
