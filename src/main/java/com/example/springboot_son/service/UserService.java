package com.example.springboot_son.service;

import com.example.springboot_son.entity.User;
import com.example.springboot_son.mapper.UserMapper;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import com.google.gson.internal.$Gson$Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper; // 用户mapper
    /**
     * 手机注册
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult registerPhone(User user) throws Exception{
        Integer index = 0;
        Map<String, Object> data = new HashMap<String, Object>();
        User checkUser = new User();
        // 设置token
        Date date = new Date();
        // 设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取String类型的时间
        String createdate = sdf.format(date);
        String token = user.getUser_password() + "#" + createdate;
        // 加密密码
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = token.getBytes("UTF-8");
        // 编码
        final String encodedText = encoder.encodeToString(textByte);
        checkUser=  userMapper.checkRegisterPhone(user.getUser_name());
          if (checkUser==null){
              log.info("-------用户注册-------");
              log.info("insert INTO `user` (user_name,user_password,user_toke,phone_model,picture_url,data_time,user_sex)" +
                      " VALUES("+user.getUser_name()+","+user.getUser_password()+","+user.getUser_toke()+","+user.getPhone_model()+
                      ","+user.getPicture_url()+","+user.getData_time()+"-"+user.getUser_sex()+")");
              try {

                  user.setData_time(createdate);
                  user.setUser_toke(encodedText);
              //开始注册
                  index=  userMapper.registerPhone(user);
                  if (index>0){
                      log.info("-------测试1-------");

                      data.put("user",user);
                      return ResponseResult.success(user);
                  }

              }catch(Exception e){
                  //异常处理
                  e.printStackTrace();
                  log.info("-------测试4-------"+index);
                   return ResponseResult.failure(ResultCode.USERID_NULL);

              }

          }

        user.setUser_toke(encodedText);
        log.info("-------测试3-------");
       return ResponseResult.success(checkUser);

    }

    /**
     * 查询用户
     * @param user_name
     * @return
     * @throws Exception
     */
   public  ResponseResult selectUser(String user_name) throws Exception{
        log.info("-------查询用户 -------");

       try {
           User user =new User();
            user=userMapper.checkRegisterPhone(user_name);
           log.info("-------user>>>>>>"+user);
            if (user!=null){
                log.info("查询成功");
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",user);
                return ResponseResult.success(data);
            }
       }catch (Exception e){
           return  ResponseResult.failure("用户不存在");
       }
       return  ResponseResult.failure("用户不存在");
    }

}