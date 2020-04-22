package com.example.springboot_son.utils;

/**
 * Created by cengyujun on 2020/4/21 4:49 下午
 */
public class AlipayConfig {

  //合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
  public static String gateway = "4564564";
  //收款国际支付宝帐号
  public static String app_id = "hokoface@yahoo.com";
  //商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
  public static String private_key = "dhsjadhsa";

  //支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
  public static String charset  = "Mdsagdjasdlahl";

  // 签名方式
  public static String sign_type = "RSA";

  // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
  public static String log_path ="C://";

  // 字符编码格式 目前支持 gbk 或 utf-8
  public static String alipay_public_key = "utf-8";

  // 接收通知的接口名
  public static String service = "mobile.securitypay.pay";

  // 正式服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
  public static String notify_url = "http://www.hokoface.com:8090/llala";


  // 正式服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
  public static String return_url = "http://www.hokoface.com:8090/lalal";


  // 请求网关地址
  public static String URL = "https://mapi.alipay.com/gateway.do";

  //支付类型1表示商品购物
  public static String payment_type="1";
  //当前交易有效时间30分钟
  public static String it_b_pay="30m";
  //交易币种,默认为人民币
  public static String currency="HKD";
  //人民币币种
  public static String currencyRMB="CNY";

  public static String forex_biz="FP";

  /**APP下载网址*/
  public static String refer_url="https://sj.qq.com/myapp/detail.htm?apkName=com.maigang.ahg";

}
