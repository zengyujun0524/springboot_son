package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //手机用户注册

    Integer registerPhone(User user) throws Exception ;
    //检查用户是否注册
    User checkRegisterPhone(String user_name) throws Exception ;

    //用户手机登入
    User phoneLogin(String user_name, String user_password);



}
