package com.example.springboot_son.mapper;


import com.example.springboot_son.entity.VideoAgree;
import com.example.springboot_son.entity.VideoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (VideoInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-21 13:47:31
 */
public interface VideoInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param videoId 主键
     * @return 实例对象
     */
    VideoInfo queryById(Long videoId);

    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<VideoInfo> queryAllByLimit(@Param("offset") int offset,@Param("limit") int limit);

    /**
     * 查询视频评论总数
     * @returngetCount
     */
    int getCount(@Param("videoId") int vid );


    /**
     * 通过实体作为筛选条件查询
     *
     * @param videoInfo 实例对象
     * @return 对象列表
     */
    List<VideoInfo> queryAll(VideoInfo videoInfo);

    /**
     * 新增数据
     *
     * @param videoInfo 实例对象
     * @return 影响行数
     */
    int insert(VideoInfo videoInfo)throws  Exception;

    /**
     * 修改数据
     *
     * @param videoInfo 实例对象
     * @return 影响行数
     */
    int update(VideoInfo videoInfo);

    /**
     * 通过主键删除数据
     *
     * @param videoId 主键
     * @return 影响行数
     */
    int deleteById(Integer videoId)throws  Exception;

    int insertAgree(VideoAgree videoAgree)throws  Exception;

    int updateNameById(VideoInfo videoInfo);

}