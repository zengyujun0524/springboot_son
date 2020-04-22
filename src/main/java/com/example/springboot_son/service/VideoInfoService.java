package com.example.springboot_son.service;

import com.example.springboot_son.entity.VideoInfo;
import com.example.springboot_son.mapper.VideoInfoMapper;
import com.example.springboot_son.utils.ObjectUtils;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cengyujun on 2020/4/21 2:29 下午
 * xs
 */
@Slf4j
@Service
public class VideoInfoService {

    @Autowired
    VideoInfoMapper   videoInfoMapper;

    /**
     * 获取视频列表
     * @param offset
     * @param limit
     * @return
     */
    public ResponseResult queryAllByLimit(int offset,  int limit)throws Exception{
        try {
            Map<String, List> data = new HashMap<String, List>();
            List<VideoInfo>  resul= videoInfoMapper.queryAllByLimit(offset,limit);
            log.info("resul>>>.."+resul);
            if (!ObjectUtils.isEmpty(resul)){
                data.put("VideoInfo",resul);
                return ResponseResult.success(data);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

         return  ResponseResult.failure(ResultCode.NULL_ERR);

    }


}
