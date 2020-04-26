package com.example.springboot_son.service;

import com.example.springboot_son.entity.Application;
import com.example.springboot_son.mapper.ApplicationMapper;

import com.example.springboot_son.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cengyujun on 2020/4/23 8:59 上午
 */
@Service
@Slf4j
public class ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;

    public ResponseResult getApp()throws  Exception{
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

}
