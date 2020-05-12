package com.example.springboot_son.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.example.springboot_son.service.RedisService;
import com.example.springboot_son.service.UserService;
import com.example.springboot_son.utils.AlipayConfig;
import com.example.springboot_son.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController  //
@RequestMapping("/test")
@Api(description = "-redis接口-")
@EnableCaching
public class DemoController {
//    private static Logger log = LoggerFactory.getLogger(UserService.class);
//
    @Autowired
    private RedisService redisService ;

 long  z=60;
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ApiOperation(value = "redis", notes = "用户操作 (除user_id和user_token外都是非必填)")
    public Object demoTest(){
        log.info("获取值");

        redisService.set("19973559563","140076",(long) System.currentTimeMillis());

       return redisService.get("19973559563");
    }

    @RequestMapping(value = "/appAlipay",method = RequestMethod.POST)
    @ApiOperation(value = "支付宝测试数据", notes ="支付宝测试数据")
    public ResponseResult appAlipay()throws AlipayApiException{
        Map<String, Object> data = new HashMap<String, Object>();
        //构造client
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
//设置网关地址
        certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");
//设置应用Id
        certAlipayRequest.setAppId(AlipayConfig.appid);
//设置应用私钥
        certAlipayRequest.setPrivateKey(AlipayConfig.privateKey);
//设置请求格式，固定值json
        certAlipayRequest.setFormat("json");
//设置字符集
        certAlipayRequest.setCharset("UTF-8");
//设置签名类型
        certAlipayRequest.setSignType(AlipayConfig.sign_type);
//设置应用公钥证书路径
        certAlipayRequest.setCertPath(AlipayConfig.app_cert_path);
//设置支付宝公钥证书路径
        certAlipayRequest.setAlipayPublicCertPath(AlipayConfig.alipay_cert_path);
//设置支付宝根证书路径
        certAlipayRequest.setRootCertPath(AlipayConfig.root_cert_path);
//构造client al
        AlipayClient   alipayClient = new DefaultAlipayClient(certAlipayRequest);

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo(String.valueOf(UUID.randomUUID()));
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            data.put("oderInfo", response.getBody());
            return  ResponseResult.success(data);

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

}
//