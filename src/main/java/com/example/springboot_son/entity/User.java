package com.example.springboot_son.entity;

import javax.persistence.Entity;

@Entity
public class User {
    private    Integer user_id; //主键
    private    String user_name; //用户姓名
    private  String user_password;//用户密码
    private  String user_toke;//用户的toke
    private  String phone_model;//手机型号
    private  String picture_url;//头像路径
    private  String data_time;//注册时间
    private  Integer user_sex;//性别（0男，1女）

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_toke() {
        return user_toke;
    }

    public void setUser_toke(String user_toke) {
        this.user_toke = user_toke;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getData_time() {
        return data_time;
    }

    public void setData_time(String data_time) {
        this.data_time = data_time;
    }

    public Integer getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(Integer user_sex) {
        this.user_sex = user_sex;
    }


    public User() {
    }




}
