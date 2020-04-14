package com.example.springboot_son.web;

import com.example.springboot_son.service.RedisService;
import com.example.springboot_son.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController  //
@RequestMapping("/equipment")
@Api(description = "-redis接口-")
@EnableCaching
public class DemoController {
//    private static Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RedisService redisService ;

 long  z=60;
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ApiOperation(value = "修改用户资料", notes = "用户操作 (除user_id和user_token外都是非必填)")
    public Object demoTest(){
        log.info("获取值");
log.info(redisService.getExpire("19973559563")+"时间");
        redisService.set("19973559563","140076",(long) System.currentTimeMillis());
       return    redisService.get("19973559563");
    }

}
