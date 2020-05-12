package com.example.springboot_son.web;

import com.example.springboot_son.entity.Comment;
import com.example.springboot_son.entity.VideoAgree;
import com.example.springboot_son.entity.VideoInfo;
import com.example.springboot_son.service.VideoInfoService;
import com.example.springboot_son.utils.ObjectUtils;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by cengyujun on 2020/4/21 2:59 下午
 *
 */
@Slf4j
@RestController
@RequestMapping("/Video")
@Api(description = "-视频操作接口-")
public class VideoInfoController {
    @Autowired
    VideoInfoService videoInfoService;

    @RequestMapping(value = "/queryAllByLimit", method = RequestMethod.POST)
    @ApiOperation(value = "获取视频列表", notes = "分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "初始页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "页数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "Integer", paramType = "query")
    })
    public ResponseResult queryAllByLimit(int offset,  int limit,Integer user_id) throws Exception{
         if (ObjectUtils.isEmpty(offset)||ObjectUtils.isEmpty(limit)){
             return  ResponseResult.failure(ResultCode.NULL_ERR);
         }
        return videoInfoService.queryAllByLimit(offset,limit,user_id) ;
    }

    @RequestMapping(value = "/insertAgree", method = RequestMethod.POST)
    @ApiOperation(value = "点赞", notes = "是否点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "*视频ID", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "agreeUserId", value = "*用户ID", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "isRsult", value = "*点赞（）", dataType = "Integer", paramType = "query")

    })
    public  ResponseResult insertAgree(int videoId,int agreeUserId ,int isRsult)throws  Exception{
        if (ObjectUtils.isEmpty(videoId)||ObjectUtils.isEmpty(agreeUserId)||ObjectUtils.isEmpty(isRsult)){
            return  ResponseResult.failure(ResultCode.NULL_ERR);
        }
        VideoAgree videoAgree = new VideoAgree();
        videoAgree.setAgreeUserId(agreeUserId);
        videoAgree.setIsRsult(isRsult);
        videoAgree.setVideoId(videoId);
        videoAgree.setModifyTime((int) (DateTime.now().getMillis() / 1000));
   return  videoInfoService.insertAgree(videoAgree);
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "上传视频", notes = "资源上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoUrl", value = "*视频路径 ", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "videoTitle", value = "*视频标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "videoDescribe", value = "*视频描述）", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageUrl", value = "*页面路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "authorPictureUrl", value = "*作者头像路径）", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "authorName",value ="作者名称" ,dataType = "String",paramType="query")

    })
    public ResponseResult insert(String videoUrl,String videoTitle,String videoDescribe,String pageUrl,Integer userId,String authorPictureUrl,String authorName)throws Exception{
        if (ObjectUtils.isEmpty(videoUrl)||ObjectUtils.isEmpty(videoTitle)||ObjectUtils.isEmpty(videoDescribe)||ObjectUtils.isEmpty(pageUrl)
                ||ObjectUtils.isEmpty(userId)||ObjectUtils.isEmpty(authorPictureUrl)){
            return  ResponseResult.failure(ResultCode.NULL_ERR);
        } //
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setVideoUrl(videoUrl);
        videoInfo.setVideoTitle(videoTitle);
        videoInfo.setVideoDescribe(videoDescribe);
        videoInfo.setUploadTime((int) (DateTime.now().getMillis() / 1000));
        videoInfo.setPageUrl(pageUrl);
        videoInfo.setUserId(userId);
        videoInfo.setAuthorPictureUrl(authorPictureUrl);
        videoInfo.setAuthorName(authorName);
        return videoInfoService.insert(videoInfo);
    }
    @RequestMapping(value = "/insertComment", method = RequestMethod.POST)
    @ApiOperation(value = "评论上传", notes = "评论模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vid", value = "*视频ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "uname", value = "*用户名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "uheadurl", value = "*头像路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tcontent", value = "*评论内容", dataType = "int", paramType = "query")
    })
    public ResponseResult insertComment(Integer vid,Integer uid,String uname,String uheadurl ,String tcontent)throws Exception{
        Comment comment =new Comment();
        comment.setTid(UUID.randomUUID().toString());
        comment.setVid(vid);
        comment.setUid(uid);
        comment.setUname(uname);
        comment.setUheadurl(uheadurl);
        comment.setTcontent(tcontent);
        comment.setTtime((int) (DateTime.now().getMillis() / 1000));
        return videoInfoService.insertComment(comment);
    }
    @RequestMapping(value = "/queryCommentByLimi", method = RequestMethod.POST)
    @ApiOperation(value = "评论列表", notes = "评论模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vid", value = "*评论ID", dataType = "Integer", paramType = "query")
    })
 public ResponseResult queryCommentByLimi(Integer vid)throws Exception{
      return  videoInfoService.queryCommentByLimit(vid);
 }
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    @ApiOperation(value = "删除视频", notes = "视频模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "videoId", value = "*视频ID", dataType = "Integer", paramType = "query")
    })
 public  ResponseResult deleteById(Integer videoId)throws Exception{
        return  videoInfoService.deleteById(videoId);
 }

    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户发布视频", notes = "视频模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = "query")
    })
 public  ResponseResult queryAll(Integer userId)throws Exception {
        VideoInfo videoInfo = new VideoInfo();
           videoInfo.setUserId(userId);
         return  videoInfoService.queryAll(videoInfo);
 }

}
