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
        checkUser=  userMapper.checkRegisterPhone(user.getUser_phone());
        String tokendate=encodedText+"-"+System.currentTimeMillis();
          if (checkUser==null){
              log.info("-------用户注册-------");
              log.info("insert INTO `user` (user_name,user_password,user_toke,phone_model,picture_url,data_time,user_sex)" +
                      " VALUES("+user.getUser_name()+","+user.getUser_password()+","+user.getUser_token()+","+user.getPhone_model()+
                      ","+user.getPicture_url()+","+user.getData_time()+"-"+user.getUser_sex()+")");
              try {

                  user.setData_time(createdate);
                  user.setUser_token(tokendate);
              //开始注册
                  index=  userMapper.registerPhone(user);
                  if (index>0){
                      log.info("-------测试1-------");

                      checkUser=  userMapper.checkRegisterPhone(user.getUser_phone());
                      data.put("user",checkUser);
                      data.put("result",1);
                      return ResponseResult.success(data);
                  }
              }catch(Exception e){
                  //异常处理
                  e.printStackTrace();
                  log.info("-------测试4-------"+index);
                   return ResponseResult.failure(ResultCode.USERID_NULL);
              }
          }
        log.info(tokendate+"-------测试4-------"+user.getUser_phone());
           int index1 =userMapper.upToken(tokendate,user.getUser_phone());
          if (index1>0){
              user.setUser_token(tokendate);
              data.put("user",checkUser);
              data.put("result",2);
              return ResponseResult.success(data);
          }
        log.info("-------测试3-------");
       return ResponseResult.failure("toekn更新失败");
    }

    /**
     * 查询用户
     * @param user_phone
     * @return
     * @throws Exception
     */
   public  ResponseResult selectUser(String user_phone) throws Exception{
        log.info("-------查询用户 -------");

       try {
           User user =new User();
            user=userMapper.checkRegisterPhone(user_phone);
           log.info("-------user>>>>>>"+user);
            if (user!=null){
                log.info("查询成功");
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",user);
                return ResponseResult.success(data);
            }
       }catch (Exception e){
           e.printStackTrace();

       }
       return  ResponseResult.failure("用户不存在");
    }

    /**
     * 修改用户资料
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult upUser( User  user) throws Exception{
        log.info("-------修改用户资料 -------");
       try {
           int index=userMapper.upUser(user);
            if (index>0){
                log.info("-------修改成功 -------");
                User user1 =userMapper.queryUers(user.getUser_id());
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",user1);
                return  ResponseResult.success(data);
            }

       }catch (Exception e){
           e.printStackTrace();
       }
          return  ResponseResult.failure(ResultCode.MODIFICATION_FAILED);

    }

}