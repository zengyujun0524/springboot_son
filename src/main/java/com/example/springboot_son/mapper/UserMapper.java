package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.User;
import com.example.springboot_son.entity.Verification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //手机用户注册

    Integer registerPhone(User user) throws Exception ;
    //检查用户是否注册
    User checkRegisterPhone(String user_phone) throws Exception ;

    //用户手机登入
    User phoneLogin(String user_name, String user_password)throws Exception ;

    //更新用户token
     Integer upToken(String user_token, @Param("user_id") Integer user_id) throws Exception ;


    //修改用户资料
    Integer upUser(User user)throws Exception;
    // 查询用户
    User queryUers( int user_id)throws  Exception;
    //修改手机号
    Integer modifyPhone(int user_id,String user_phone)throws  Exception;
    //用户登出——清空token
    Integer userLogout(int user_id)throws  Exception;
    User getToken(String user_token)throws  Exception;
  //插入token
    Integer    inserVer(int user_id,String user_token)throws  Exception;
    //修改验证数据
    Integer modifyVer(Verification  verification)throws  Exception;
    //获取验证数据
    Verification getVer(int user_id)throws  Exception;
    //通过token验证手机号
     Verification getUserByToken(int user_id, String user_token) throws  Exception;
     //修改推送token
       Boolean modifyPush(int user_id,String push_token ) throws  Exception;

}

