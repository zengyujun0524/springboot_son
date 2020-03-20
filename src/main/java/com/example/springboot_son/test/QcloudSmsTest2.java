package com.example.springboot_son.test;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import org.junit.Test;



public class QcloudSmsTest2 {

    @Test
    public  void sendMessage() {
        // 短信应用SDK AppID
        int appid = 1400329984; // 1400开头
        String name="yys";
        String PhoneNumber="19973559563";
        // 短信应用SDK AppKey
        String appkey = "de9a0f2ddbad0af8ed44a9ceb4f142c7";

        // 需要发送短信的手机号码
        String[] phoneNumbers = {PhoneNumber};//多个号码用,隔开

        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，
        // 真实的模板ID需要在短信控制台中申请
        int templateId = 551926;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，
        // 真实的签名需要在短信控制台中申请，另外
        // 签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "把把智能科技";

        // 指定模板ID单发短信
        try {
            String number = (long)(Math.random() * 1000000)+"";//生成六位验证码
            String[] params = {name,number,"5"};
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.print(result);
        } catch (Exception e) {
        }
    }
}
