package com.example.springboot_son.web;

import com.example.springboot_son.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cengyujun on 2020/4/20 3:45 下午
 */
@Slf4j
@RestController
@RequestMapping("/AliPay")
@Api(description = "-支付接口-")
public class AliPayController {
    @RequestMapping(value = "/getAliPay", method = RequestMethod.POST)
    @ApiOperation(value = "主界面商品展示", notes = "直接获取设备信息")
    public ResponseResult getAliPay() throws Exception{
        return  null;
    }
}
