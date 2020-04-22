/**
 * @author 曾俞钧
 * @date 2020/3/21 14:59
 * @version 1.0
 */
package com.example.springboot_son.web;

import com.example.springboot_son.service.EquipmentService;
import com.example.springboot_son.utils.ObjectUtils;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
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
@Slf4j
@RestController  //
@RequestMapping("/equipment")
@Api(description = "-设备操作接口-")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
      TokenVerification token= new TokenVerification();
    /**
            *  查看设备信息
            * @param userId
            * @param userToken
            * @return
            * @throws Exception
            */
            @RequestMapping(value = "/equipmentInfo", method = RequestMethod.POST)
            @ApiOperation(value = "主界面商品展示", notes = "直接获取设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "Integer", paramType = "query")
    })
    public ResponseResult  equipmentInfo(Integer userId,String userToken) throws Exception {
        //查询所有设备信息

        return equipmentService.equipmentInfo(userId);


    }

    /**
     * 查询用户绑定设备
     * @param userId
     * @param userToken
     * @param equipmentId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bindingDevice", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户绑定设备", notes = "绑定页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*用户token", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "equipmentId", value = "*设备ID", dataType = "Integer", paramType = "query")
    })

 public  ResponseResult bindingDevice(Integer userId,String userToken,Integer equipmentId) throws Exception {
           if (ObjectUtils.isEmpty(userId)||ObjectUtils.isEmpty(equipmentId))
               return ResponseResult.failure(ResultCode.NULL_ERR);

     return equipmentService.bindingDevice(userId,equipmentId,userToken);
 }


}
