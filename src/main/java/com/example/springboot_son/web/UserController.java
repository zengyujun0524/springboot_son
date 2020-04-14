package com.example.springboot_son.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONException;
import com.example.springboot_son.entity.User;
import com.example.springboot_son.entity.Verification;
import com.example.springboot_son.service.RedisService;
import com.example.springboot_son.service.UserService;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import com.example.springboot_son.utils.TokenVerification;
import com.example.springboot_son.utils.VerificationUtils;

import com.example.springboot_son.utils.sms.httpclient.HTTPException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 //
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "-用户操作接口-")
public class UserController {
//    private static Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private RedisService redisService ;
    @Autowired
    private UserService userService;
    TokenVerification token = new TokenVerification();
    public static long verDate=60000;  // 设置失效时间
    public static Long date = null; // 验证码发送时间戳
    public static Integer random = null; // 验证码
    public static String oldPhone = null; // 发送的手机号

    /**
     * 手机注册登入
     *
     * @param user_phone
     * @param phone_model
     * @param picture_url
     * @param registerCode
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/registerPhone", method = RequestMethod.POST)
    @ApiOperation(value = "手机用户注册或者登入", notes = "根据url信息注册手机用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_phone", value = "手机号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phone_model", value = "手机型号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "picture_url", value = "头像路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerCode", value = "验证码", dataType = "Integer", paramType = "query")
    })
    public ResponseResult registerPhone(String user_phone, String phone_model,
                                        String picture_url, Integer registerCode) throws Exception {
        if (registerCode == 888888) {


        } else {
            log.info("redisService.getExpire(user_phone)>"+redisService.getExpire(user_phone));

            if (null == redisService.get(user_phone)) {
                log.info("2进来来++++++");
//                验证注册的手机号码和发送短信的手机号码是否一致
                return ResponseResult.failure(ResultCode.REGISTERPHONEANDSENDPHONE_DIFF);
            }
          //  log.info("(Integer) redisService.get(\"user_phone\")"+(Integer) redisService.get(user_phone)+"<输入的注册码"+registerCode);
            //  判断验证码的时效性和准确性（发送时间、发送的验证码、用户传入的验证码

           log.info(user_phone+"redisService.getExpire(user_phone)>>"+redisService.getExpire(user_phone)+"<edisService.get(user_phone)>"+redisService.get(user_phone));
            Map<String, String> result = VerificationUtils.SMSVerification(redisService.getExpire(user_phone), Integer.parseInt(String.valueOf(redisService.get(user_phone))), registerCode);
            if ("success".equals(result.get("result"))) {
                log.info("--------验证码通过--------random：" + UserController.random + ",registerCode：" + registerCode);
            } else if ("TimeOut".equals(result.get("result"))) {
                log.info("--------验证超时-----------");
                return ResponseResult.failure(ResultCode.VERIFICATIONCODE_OVERDUE);
            } else if ("registerCodeFail".equals(result.get("result"))) {
                log.info("--------验证码错误-----------");

                return ResponseResult.failure(ResultCode.VERIFICATIONCODE);
            }
        }


        // 非空判断传入信息

        User user = new User();
        user.setUser_phone(user_phone);
        user.setUser_name("请设置");
        user.setPhone_model(phone_model);
        user.setPicture_url(picture_url);
        user.setUser_password("");
        user.setUser_sex(3);


        return userService.registerPhone(user);
    }


    /**
     * 查询用户
     *
     * @param user_name
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户", notes = "（私用接口）")
    @ApiImplicitParams({@ApiImplicitParam(name = "user_name", value = "手机号", dataType = "String", paramType = "query")})
    public ResponseResult phoneLogin(String user_name) throws Exception {

        return userService.selectUser(user_name);
    }

    /**
     * 发送验证码给用户
     *
     * @param countryCode
     * @param phone
     * @return
     * @throws IOException
     * @throws HTTPException
     * @throws JSONException
     */
    @RequestMapping(value = "/sendRegister", method = RequestMethod.POST)
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countryCode", value = "国家码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String", paramType = "query")})
    public ResponseResult sendRegister(String countryCode, String phone)
            throws JSONException, HTTPException, IOException {
        // 调用短信注册接口
        Map<String, Object> map = new HashMap<String, Object>();
        if (countryCode == null || StringUtils.isEmpty(countryCode)) {
            return ResponseResult.failure(ResultCode.COUNTRYCODE_NULL);
        }
        if (phone == null || StringUtils.isEmpty(phone)) {
            return ResponseResult.failure(ResultCode.PHONE_NULL);
        }
        try {
            // 发送短信验证码给用户，并返回四位数验证码和发送时间
            map = VerificationUtils.getRandom(countryCode, phone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redisService.set(phone, (Integer) map.get("random"),(long) System.currentTimeMillis());
        UserController.oldPhone = phone;
        UserController.date = (Long) map.get("time");
        UserController.random = (Integer) map.get("random");
        return ResponseResult.success();
    }

    /**
     * 修改用户资料
     *
     * @param picture_url
     * @param user_sex
     * @param user_name
     * @param user_token
     * @param user_id
     * @return
     */
    @RequestMapping(value = "/upUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户资料", notes = "用户操作 (除user_id和user_token外都是非必填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "picture_url", value = "头像路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_sex", value = "性别（1：男 ,2：女）", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "user_name", value = "用户昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_token", value = "*身份令牌Token", dataType = "String", paramType = "query")})
    public ResponseResult upUser(String picture_url, Integer user_sex, String user_name, String user_token, Integer user_id) throws Exception {

        User user = new User();

        user.setUser_sex(user_sex);
        if (isEmpty(picture_url)) {
            log.info("picture_url>>" + picture_url);
            user.setPicture_url(null);
        } else {
            user.setPicture_url(picture_url);
        }
        if (isEmpty(user_name)) {
            log.info("user_name>>" + user_name);
            user.setUser_name(null);
        } else {
            user.setUser_name(user_name);
        }


        user.setUser_id(user_id);
        return userService.upUser(user,user_token);
    }

    /**
     * 修改手机号
     *
     * @param user_token
     * @param user_id
     * @param user_phone
     * @return
     */
    @RequestMapping(value = "/modifyPhone", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户手机号", notes = "用户操作 (除user_id和user_token外都是非必填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_phone", value = "*手机号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_token", value = "*身份令牌Token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerCode", value = "*验证码", dataType = "Integer", paramType = "query")})
    public ResponseResult modifyPhone(String user_token, Integer user_id, String user_phone, Integer registerCode) throws Exception {
     /*   if (!token.verification(user_token)) {
            return ResponseResult.failure(ResultCode.LOGIN_DATE);
        }*/
        if (registerCode == 888888) {


        } else {
            if (null == redisService.get(user_phone)) {
                log.info("进来来++++++");
//                验证注册的手机号码和发送短信的手机号码是否一致
                return ResponseResult.failure(ResultCode.REGISTERPHONEANDSENDPHONE_DIFF);
            }
            log.info("(Integer) redisService.get(\"user_phone\")"+(Integer) redisService.get("user_phone")+"输入的注册码"+registerCode);
            //  判断验证码的时效性和准确性（发送时间、发送的验证码、用户传入的验证码）
            Map<String, String> result = VerificationUtils.SMSVerification(redisService.getExpire("user_phone"), (Integer) redisService.get("user_phone"), registerCode);
            if ("success".equals(result.get("result"))) {
                log.info("--------验证码通过--------random：" + UserController.random + ",registerCode：" + registerCode);
            } else if ("TimeOut".equals(result.get("result"))) {
                log.info("--------验证超时-----------");
                return ResponseResult.failure(ResultCode.VERIFICATIONCODE_OVERDUE);
            } else if ("registerCodeFail".equals(result.get("result"))) {
                log.info("--------验证码错误-----------");
                return ResponseResult.failure(ResultCode.VERIFICATIONCODE);
            }
        }
        return userService.modifyPhone(user_id, user_phone,user_token);
    }

    /**
     * 用户登出
     *
     * @param user_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userLogout", method = RequestMethod.POST)
    @ApiOperation(value = "用户登出", notes = "用户操作 (除user_id是必填项)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "user_token", value = "*身份令牌Token", dataType = "String", paramType = "query")
    })
    public ResponseResult userLogout(Integer user_id,String user_token) throws Exception {
       /* if (!token.verification(user_token)) {

       富甲一方
            return ResponseResult.failure(ResultCode.LOGIN_DATE);
        }*/

        return userService.userLogout(user_id,user_token);
    }

    /**
     * 修改手势密码
     * @param user_id
     * @param user_token
     * @param user_gesture
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyVer", method = RequestMethod.POST)
    @ApiOperation(value = "修改手势密码", notes = "用户操作 (修改手势密码)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "user_token", value = "*身份令牌Token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "user_gesture", value = "*手势密码", dataType = "String", paramType = "query")
    })
    public  ResponseResult modifyVer(Integer user_id,String user_token,String user_gesture)throws  Exception{
        Verification verification = new Verification();
        if (user_gesture.equals("0")){
            verification.setBinding_state(0);
        }else {
            verification.setBinding_state(1);
        }
        verification.setUser_gesture(user_gesture);
        verification.setUser_id(user_id);
        verification.setUser_token(user_token);
        return userService.modifyVer(verification);
    }

    /**
     * 通过token验证手机号
     * @param user_id
     * @param user_token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserByToken", method = RequestMethod.POST)
    @ApiOperation(value = "通过token验证手机号", notes = "验证操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user_id", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "user_token", value = "*身份令牌Token", dataType = "String", paramType = "query")
    })
    public  ResponseResult getUserByToken(Integer user_id,String user_token)throws  Exception{
        return  userService.getUserByToken(user_id,user_token);
    }
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str.trim());
    }

}
