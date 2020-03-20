package com.example.springboot_son.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.springboot_son.utils.sms.SmsSingleSender;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 验证码工具类
 * @author Administrator
 *
 */
public class VerificationUtils {

	private static Logger log = LoggerFactory.getLogger(VerificationUtils.class);
	
	/**
	 * 	获取4位数验证码、并发送
	 * @param countryCode	国家码
	 * @param phone			手机号码
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> getRandom(String countryCode, String phone)throws Exception {
		Map<String, Object> data = new HashMap<String,Object>();

		// 短信应用SDK AppID
		int appid = 1400329984; // 1400开头

		// 短信应用SDK AppKey
		String appkey = "de9a0f2ddbad0af8ed44a9ceb4f142c7";

		// 需要发送短信的手机号码
		String[] phoneNumbers = {phone};

		// 短信模板ID，需要在短信应用中申请
		// NOTE: 这里的模板ID`7839`只是一个示例，
		// 真实的模板ID需要在短信控制台中申请
		int templateId = 551926;

		// 签名
		// NOTE: 这里的签名"腾讯云"只是一个示例，
		// 真实的签名需要在短信控制台中申请，另外
		// 签名参数使用的是`签名内容`，而不是`签名ID`
		String smsSign = "把把智能科技";
		try {
			Integer ran=(int)(Math.random()*900000)+100000;
			String s = Integer.toString(ran);
			String[] params = {s};
			SmsMultiSender msender = new SmsMultiSender(appid, appkey);
			SmsMultiSenderResult result =  msender.sendWithParam("86", phoneNumbers,
					templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
			System.out.print(result);
			data.put("random",ran);
			//生成当前时间
			data.put("time", System.currentTimeMillis());
		} catch (HTTPException e) {
			// HTTP响应码错误
			e.printStackTrace();
		} catch (JSONException e) {
			// json解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络IO错误
			e.printStackTrace();
		}
	/*	//生成4位数验证码
		Integer ran=(int)(Math.random()*900000)+100000;
		//发送短信
		//MainApp.sendRegister(countryCode, phone,ran);
		SmsSingleSender ss = new SmsSingleSender(1400329984,"de9a0f2ddbad0af8ed44a9ceb4f142c7");
		if("86".equals(countryCode)) {
			//国内
			ss.send(0,countryCode,phone,ran + "为您的注册验证码，请于5分钟内填写。如非本人操作，请忽略本短信。", "", "");
			log.info("------国内短信发送成功！----国家码-》-"+countryCode+"--手机号-》----" + phone);
		}else {
			//国际
			ss.send(0,countryCode,phone,ran + " is the registration verification code. Please enter this code in 5 minutes. If you didn't initiate the request, please disregard this message.", "", "");
			log.info("------国际短信发送成功！----国家码-》-"+countryCode+"--手机号-》----" + phone);
		}
		data.put("random",ran);
		//生成当前时间
		data.put("time", System.currentTimeMillis());
		return data;*/

	return data;
	}
	
	//判断验证码时效性、正确性
	public static Map<String,String> SMSVerification(Long sendTime,Integer OldRandom,Integer newRandom) throws Exception {
		Map<String, String> result = new HashMap<String,String>();
		if(OldRandom.equals(newRandom)) {	//传入的验证码是否和发送验证码一致
			long nowDate = (long) System.currentTimeMillis();
			double diff = (nowDate-sendTime)/60000;	//计算差值是否大于6分钟，否则验证码失效
			log.info("-------发送时间的差值为"+diff+"分钟-------");
			if(diff < 6) {
				log.info("-------验证码正确-------");
				
				result.put("result", "success");
			}else {
				result.put("result", "TimeOut");
			}
		}else {
			result.put("result", "registerCodeFail");
		}
		return result;
	}
	
}
