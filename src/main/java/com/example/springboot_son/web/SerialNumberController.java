package com.example.springboot_son.web;

import com.example.springboot_son.dto.HeadsetByAct;
import com.example.springboot_son.service.SerialNumberService;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.TokenVerification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 曾俞钧
 * @version 1.0
 * @date 2020/4/7 10:26 上午
 */
@Slf4j
@RestController
@RequestMapping("/serial")
@Api(description = "-激活绑定接口-")
public class SerialNumberController {

    @Autowired
    SerialNumberService serialNumberService; //
    TokenVerification token = new TokenVerification();


    /**
     * 耳机激活接口
     * @param serialNumber
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/actEquipment", method = RequestMethod.POST)
    @ApiOperation(value = "耳机激活接口", notes = "设备激活")
    @ApiImplicitParams({@ApiImplicitParam(name = "serialNumber", value = "序列号", dataType = "String", paramType = "query"),
                             @ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", paramType = "query")})
    public ResponseResult actEquipment(String serialNumber , Integer id) throws Exception {
        HeadsetByAct headsetByAct  = new HeadsetByAct();
        Date date = new Date();
        // 设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取String类型的时间ß
        String createdate = sdf.format(date);

        headsetByAct.setBinState(1);
        headsetByAct.setActTime(createdate);
        headsetByAct.setId(id);
        headsetByAct.setSerialNumber(serialNumber);

        return serialNumberService.actEquipment(headsetByAct);
    }
}
