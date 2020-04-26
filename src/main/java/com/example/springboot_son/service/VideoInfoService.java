package com.example.springboot_son.service;

import com.example.springboot_son.entity.VideoAgree;

import com.example.springboot_son.entity.VideoInfo;
import com.example.springboot_son.mapper.VideoInfoMapper;
import com.example.springboot_son.utils.*;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by cengyujun on 2020/4/21 2:29 下午
 * 小时
 */
@Slf4j
@Service
public class VideoInfoService {

    @Autowired
    VideoInfoMapper   videoInfoMapper;

    //以1分钟的频率执行任务
    @Scheduled(fixedRate = 600)
    public void myTask() {

    }

    /**
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
                int sumNumber=videoInfoMapper.getCount();
//            Page<VideoInfo> userPage = new Page (pageInfo);
        //    PageBean<VideoInfo> pageData = new PageBean<>(offset, limit, sumNumber);
            log.info("userPage>>>.."+sumNumber);
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
     * @return不
     * @throws Exception
     */
    public  ResponseResult insertAgree(VideoAgree videoAgree)throws Exception{
        try {
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


}
