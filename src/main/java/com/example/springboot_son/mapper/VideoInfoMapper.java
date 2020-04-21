package com.example.springboot_son.mapper;


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
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<VideoInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


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
    int insert(VideoInfo videoInfo);

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
    int deleteById(Long videoId);

}