/**
 * @author 曾俞钧
 * @date 2020/3/21 14:59
 * @version 1.0
 */
package com.example.springboot_son.web;

import com.example.springboot_son.service.EquipmentService;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import com.example.springboot_son.utils.TokenVerification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController  //
@RequestMapping("/equipment")
@Api(description = "-设备操作接口-")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;
      TokenVerification token= new TokenVerification();
    /**
     *  查看设备信息及绑定状态
     * @param user_id
     * @param user_token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/equipmentInfo", method = RequestMethod.POST)
    @ApiOperation(value = "查询设备信息", notes = "直接获取设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "用户ID", dataType = "Integer", paramType = "query")
    })
 public ResponseResult  equipmentInfo(Integer user_id,String user_token) throws Exception {
   //查询所有设备信息
        if ( user_token==null||token.verification(user_token)){
            return equipmentService.equipmentInfo(user_id);

        }
        return  ResponseResult.failure(ResultCode.LOGIN_DATE);
 }


}
