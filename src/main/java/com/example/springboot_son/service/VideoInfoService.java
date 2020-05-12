package com.example.springboot_son.service;

import com.example.springboot_son.entity.Comment;
import com.example.springboot_son.entity.VideoAgree;

import com.example.springboot_son.entity.VideoInfo;
import com.example.springboot_son.mapper.CommentMapper;
import com.example.springboot_son.mapper.VideoInfoMapper;
import com.example.springboot_son.utils.*;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cengyujun on 2020/4/21 2:29 下午
 * 小时
 */
@Slf4j
@Service
public class VideoInfoService {

    @Resource
    VideoInfoMapper   videoInfoMapper;

    @Resource
    CommentMapper commentMapper;

    //以1分钟的频率执行任务
    @Scheduled(fixedRate = 600)
    public void myTask() {

    }

    /**server {
     *
     listen 80;
     autoindex on;
     server_name www.zengyujun.top;
     access_log /usr/local/nginx/logs/access.log combined;
     index index.html index.htm index.jsp index.php;
     if ( $query_string ~* ".*[\;'\<\>].*" ){
     return 404;
     }

     location / {
     proxy_pass http://123.56.73.147:8012;
     add_header Access-Control-Allow-Origin *;
     }
     }

     server
     {
     listen 80;
     server_name www.zengyujun.top;
     index index.html index.htm index.php;
     root  /www/server/phpmyadmin;

     #error_page   404   /404.html;
     include enable-php.conf;

     location / {
     proxy_pass http://39.102.56.91:8012;
     add_header Access-Control-Allow-Origin *;
     }
     location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$
     {
     expires      30d;
     }

     location ~ .*\.(js|css)?$
     {
     expires      12h;
     }

     location ~ /\.
     {
     deny all;
     }

     access_log  /www/wwwlogs/access.log;
     }

     * 获取视频列表
     * @param offset
     * @param limit
     * @return
     */
    public ResponseResult queryAllByLimit(int offset,  int limit,Integer user_id)throws Exception{
        try {
            log.info("1>>>>>>>>>"+PageHelper.startPage(offset,limit));
            Map<String, List> data = new HashMap<String, List>();
           List<VideoInfo>resul = videoInfoMapper.queryAllByLimit(offset,limit);
//
         //       int sumNumber=videoInfoMapper.getCount();
//            Page<VideoInfo> userPage = new Page (pageInfo);
        //    PageBean<VideoInfo> pageData = new PageBean<>(offset, limit, sumNumber);
      //      log.info("userPage>>>.."+sumNumber);
            if (!ObjectUtils.isEmpty(resul)){
                data.put("videoInfo", resul);
                return ResponseResult.success(data);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

         return  ResponseResult.failure(ResultCode.NULL_ERR);

    }

    /**
     * 点赞
     * @param videoAgree
     * @return
     * @throws Exception
     */
    public  ResponseResult insertAgree(VideoAgree videoAgree)throws Exception{
        try {
            //点赞逻

            return videoInfoMapper.insertAgree(videoAgree)>0?ResponseResult.success():ResponseResult.failure(ResultCode.NULL_ERR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseResult.failure(ResultCode.NULL_ERR);
    }

    /**
     * 上传视频
     * @param videoInfo
     * @return
     * @throws Exception
     */
    public  ResponseResult insert (VideoInfo videoInfo)throws  Exception{
        try {

              return videoInfoMapper.insert(videoInfo)>0?ResponseResult.success():ResponseResult.failure(ResultCode.NULL_ERR);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseResult.failure(ResultCode.NULL_ERR);
    }

    /**
     * 获取插入评论
     * @param comment
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertComment(Comment comment )throws  Exception{
        try {
       if (commentMapper.insertComment(comment)>0){
           int num=commentMapper.countByComment(comment.getVid());
           VideoInfo videoInfo = new VideoInfo();
           videoInfo.setCommentNumber(num);
           log.info(">>点赞数"+num);
           videoInfo.setVideoId(comment.getVid());
           videoInfoMapper.update(videoInfo);
       }
            return ResponseResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseResult.failure(ResultCode.NULL_ERR);
    }

    /**
     * 查询评论列表
     * @param vid
     * @return
     * @throws Exception
     */
    public ResponseResult queryCommentByLimit(Integer vid)throws  Exception{
         try {
             Map<String, List> data = new HashMap<String, List>();
             List<Comment>comment=commentMapper.queryCommentByLimit(vid);
             data.put("Comment",comment);
                  return ResponseResult.success(data);
         } catch (Exception e){
             e.printStackTrace();
         }
        return  ResponseResult.failure(ResultCode.NULL_ERR);
    }

    /**
     * 删除视频接口
     * @param videoId
     * @return
     * @throws Exception
     */
     public  ResponseResult deleteById(Integer videoId) throws  Exception{
        try {
            return  videoInfoMapper.deleteById(videoId)>0?ResponseResult.success():ResponseResult.failure(ResultCode.NULL_ERR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  ResponseResult.failure(ResultCode.NULL_ERR);
     }

    /**
     *  获取我的发布视频
     * @param videoInfo
     * @return
     */
    public  ResponseResult queryAll(VideoInfo videoInfo){
        try {

            Map<String, List> data = new HashMap<String, List>();
            List<VideoInfo> video =videoInfoMapper.queryAll(videoInfo);
              log.info("数据》》》》》"+video+"userid"+videoInfo.getUserId());
             data.put("videoInfo",video);
             return ResponseResult.success(data);
        }catch (Exception e){
            return ResponseResult.failure(ResultCode.NULL_ERR);
        }

}



}
