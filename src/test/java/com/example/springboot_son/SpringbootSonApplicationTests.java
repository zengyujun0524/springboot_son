package com.example.springboot_son;

import com.example.springboot_son.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SpringbootSonApplicationTests {

    @Test
    void contextLoads() throws UnsupportedEncodingException, ParseException {
     User user = new User();

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
        System.out.println("token>>>>>"+System.currentTimeMillis());
        System.out.println("token>>>>>"+encodedText);
        String tokendate=encodedText+"-"+System.currentTimeMillis();
        System.out.println("token>>>>>"+tokendate);
        String sub=tokendate.substring(tokendate.indexOf("-")+1);

        boolean convertSuccess=true;
        //时间格式定义
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前时间日期--nowDate
        String nowDate = format.format(new Date());
        //获取30天前的时间日期--minDate
        Calendar calc = Calendar.getInstance();
        calc.add(Calendar.DAY_OF_MONTH, 30);
        String minDate = format.format(calc.getTime());
        long ts = date.getTime();

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String zyj=  String.valueOf(sdf3.parse(minDate).getTime());
        long l = Long.parseLong( zyj );

        System.out.println("格式化结果：" + zyj);
        System.out.println("时间搓"+minDate);
        System.out.println("一个月后时间搓"+l);







    }



}
