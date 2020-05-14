package com.example.springboot_son.mapper;
import com.example.springboot_son.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author zengyujun
 * @since 2020-05-07 13:58:05
 */
public interface CommentMapper {

    /**
     * 通过ID查询单条数据
     *  1。后台视频用户评论接口，用户评论列表接口
     *  2。新服务器生产环境配置以及数据库结构和数据迁移
     *  3。完善视频点赞接口
     *  4。提供设备修改名称接口
     *  5。面试
     * @param tid 主键
     * @return 实例对象
     */
    CommentMapper queryById(String tid);

    /**
     * 查询指定行数据
     *
     * @param vid
     * @return
     */
    List<Comment> queryCommentByLimit(@Param("vid") int vid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insertComment(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 影响行数
     */
    int deleteById(String tid);


   int countByComment( int tid);

}