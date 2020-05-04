package com.example.springboot_son.service;

import com.example.springboot_son.dto.HeadsetByAct;
import com.example.springboot_son.mapper.SerialNumberMapper;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 曾俞钧
 * @version 1.0
 * @date 2020/4/7 9:33 上午
 */
@Slf4j
@Service
public class SerialNumberService {
    @Resource
    SerialNumberMapper  serialNumberMapper;


    public ResponseResult actEquipment(HeadsetByAct headsetByAct) throws Exception{

        Map<String, Object> data = new HashMap<String, Object>();
   try {
       //检查激活码是否正确和是否绑定过
       HeadsetByAct hea= serialNumberMapper.getEquInfo(headsetByAct.getSerialNumber());
       log.info("HeadsetByAct>>>>>>"+hea);
          if (hea!=null||1!=hea.getBinState())
              //判断是否激活成功
              if (serialNumberMapper.actEquipment(headsetByAct)>0)

                   return serialNumberMapper.actEquipment(headsetByAct)>0 ? ResponseResult.success():ResponseResult.failure(ResultCode.ACTIVATION_CODE_EXPIRED);

   }catch (Exception e){
       e.printStackTrace();
   }
        return  ResponseResult.failure(ResultCode.ACT_FAILE);

    }
 //
}


