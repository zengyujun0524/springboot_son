package com.example.springboot_son;

import com.example.springboot_son.entity.User;
import com.example.springboot_son.utils.ObjectUtils;
import com.example.springboot_son.utils.UrlSend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.*;

@SpringBootTest
class SpringbootSonApplicationTests {

    @Test
    void contextLoads() throws IOException, ParseException {
//       String url = "http://www.babaznkj.com/wp-json/user/v1/checkPhone";
//       String phoneNumber = "1234567";
//       UrlSend.httpURLConnectionPOST(url, phoneNumber);

        System.out.println("》》》》 ObjectUtils.isEmpty(\"\");"+ ObjectUtils.isEmpty(""));

//       URL url1 = new URL("http://www.babaznkj.com/wp-json/user/v1/checkPhone?");
//       HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
//       conn.setConnectTimeout(10000);
//       conn.setRequestMethod("GET");
//       conn.setDoInput(true);
//       conn.setDoOutput(true);
//
//
//       // 输出返回结果
//       InputStream input = conn.getInputStream();
//       int resLen = 0;
//       byte[] res = new byte[1024];
//       StringBuilder sb = new StringBuilder();
//       while ((resLen = input.read(res)) != -1) {
//          sb.append(new String(res, 0, resLen));
//       }
//
//       String jsonStr = sb.toString();
//
//　    System.out.println(jsonStr);


    /*User user = new User();

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

*/
//       try {
//          /**以下参数分别填写您的 Redis 实例内网 IP、端口号、实例 ID 和密码*/
//          String host = "123.56.73.147";
//          int port = 6379;
//          String instanceid = "84ffd722-b506-4934-9025-645bb2a0997b";
//          String password = "0524A";
//          //连接Redis
//          Jedis jedis = new Jedis(host, port);
//          //鉴权
//          jedis.auth(instanceid + ":" + password);
//
//          /**接下来可以开始操作 Redis 实例，可以参考 https://github.com/xetorthio/jedis */
//          //设置 Key
//          jedis.set("redis", "tencent");
//          System.out.println("set key redis suc, value is: tencent");
//          //获取 Key
//          String value = jedis.get("redis");
//          System.out.println("get key redis is: " + value);
//
//          //关闭退出
//          jedis.quit();
////        /*  jedis.close();*/
//       } catch (Exception e) {
//          e.printStackTrace();
//       }
//    }
//创建对象
/*       HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
       //存储一条数据
       opsForHash.put("orderInfo","orderId","11");
       //获取一条数据
       String value = opsForHash.get("orderInfo", "orderId");
       System.out.println(value);

       //存储多条数据 佳
       Map<String,String> map = new HashMap<>();
       map.put("createTime","2018-06-21");
       map.put("orderSn","888888");
       opsForHash.putAll("orderInfo",map);
       //获取多条数据
       List<String> listKey = new ArrayList<>();
       listKey.add("createTime");
       listKey.add("orderSn");
       List<String> info = opsForHash.multiGet("orderInfo", listKey);
       for (String s : info) {
          System.out.println(s);

       }*/


    }
}
