package com.example.springboot_son.test;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import org.junit.Test;

import java.util.UUID;


public class QcloudSmsTest2 {



    @Test
    public  void sendMessage() {
        // 生成订单token 临时切唯一
        String orderToken = UUID.randomUUID().toString();
        //   订单号 可以用时间搓
        Long orderId =System.currentTimeMillis();
   Runnable runnable=() ->System.out.println(orderToken);
       runnable.run();
    }
}
