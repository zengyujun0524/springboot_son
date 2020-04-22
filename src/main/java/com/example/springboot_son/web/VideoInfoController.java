package com.example.springboot_son.web;

import com.example.springboot_son.service.VideoInfoService;
import com.example.springboot_son.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cengyujun on 2020/4/21 2:59 下午
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
            @ApiImplicitParam(name = "offset", value = "初始页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "页数", dataType = "String", paramType = "query")
    })
    public ResponseResult queryAllByLimit(int offset,  int limit) throws Exception{

        return videoInfoService.queryAllByLimit(offset,limit) ;
    }

}
