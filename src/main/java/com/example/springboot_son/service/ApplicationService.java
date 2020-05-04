package com.example.springboot_son.service;

import com.example.springboot_son.entity.Application;
import com.example.springboot_son.entity.StaticResources;
import com.example.springboot_son.mapper.ApplicationMapper;

import com.example.springboot_son.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** java-1.8.0-openjdk-1.8.0.252.b09-2.el8_1.x86_64
 * Created by cengyujun on 2020/4/23 8:59
 */
@Service
@Slf4j
public class ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;

    public ResponseResult getApp()throws  Exception {
        Map<String, Object> data = new HashMap<String, Object>();
      try {
          Application application =applicationMapper.getApp();
          data.put("application",application);
      return ResponseResult.success(data);
      }catch (Exception e){
          e.printStackTrace();
      }
              return ResponseResult.failure("数据库异常");
    }
    public ResponseResult listPicture()throws Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        try {
          List <StaticResources>  list =applicationMapper.listPicture();
            data.put("staticResources",list);
            return ResponseResult.success(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseResult.failure("数据库异常");
    }

}
