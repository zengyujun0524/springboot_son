package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.User;
import com.example.springboot_son.entity.Verification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //手机用户注册

    Integer registerPhone(User user) throws Exception ;
    //检查用户是否注册
    User checkRegisterPhone(String userPhone) throws Exception ;

    //用户手机登入
    User phoneLogin(String user_name, String userPassword)throws Exception ;

    //更新用户token
     Integer upToken(String userToken, @Param("userId") Integer userId) throws Exception ;


    //修改用户资料
    Integer upUser(User user)throws Exception;
    // 查询用户
    User queryUers( int userId)throws  Exception;
    //修改手机号
    Integer modifyPhone(int userId,String userPhone)throws  Exception;
    //用户登出——清空token
    Integer userLogout(int userId)throws  Exception;
    User getToken(String userToken)throws  Exception;
    //插入token
    Integer    inserVer(int userId,String userToken)throws  Exception;
    //修改验证数据
    Integer modifyVer(Verification  verification)throws  Exception;
    //获取验证数据
    Verification getVer(int userId)throws  Exception;
    //通过token验证手机号
     Verification getUserByToken(int userId, String userToken) throws  Exception;
     //修改推送token
       Boolean modifyPush(int userId,String pushToken ) throws  Exception;
     //验证token
      Verification selectToken(String userToken, Integer userId);

}

