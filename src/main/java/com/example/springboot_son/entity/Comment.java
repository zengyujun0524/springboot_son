package com.example.springboot_son.entity;
import lombok.Data;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author zengyujun
 * @since 2020-05-07 11:33:40
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 521919142918880750L;

    /**
    * 主键ID
    */
    private String tid;
    /**
    * 视频ID
    */
    private int vid;
    /**
    * 用户ID
    */
    private Integer uid;
    /**
    * 用户名
    */
    private String uname;
    /**
    * 用户头像
    */
    private String uheadurl;
    /**
    * 评论内容
    */
    private String tcontent;
    /**
    * 评论时间
    */
    private Integer ttime;


}