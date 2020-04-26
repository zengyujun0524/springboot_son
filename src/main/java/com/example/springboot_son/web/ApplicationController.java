package com.example.springboot_son.web;

import com.example.springboot_son.service.ApplicationService;
import com.example.springboot_son.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cengyujun on 2020/4/23 9:29 上午
 */
@Slf4j
@RestController
@RequestMapping("/app")
@Api(description = "-app版本接口-")
public class ApplicationController {
     @Autowired
    public   ApplicationService applicationService;

    @RequestMapping(value = "/getApp", method = RequestMethod.POST)
    @ApiOperation(value = "获取app版本", notes = "app")
    public ResponseResult getApp() throws Exception{
        return  applicationService.getApp();
    }
}
