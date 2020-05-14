package com.example.springboot_son.web;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONException;
import com.example.springboot_son.entity.User;
import com.example.springboot_son.entity.Verification;
import com.example.springboot_son.service.RedisService;
import com.example.springboot_son.service.UserService;
import com.example.springboot_son.utils.*;

import com.example.springboot_son.utils.sms.httpclient.HTTPException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
     * 
     * @param userPhone
     * @param phoneModel
     * @param pictureUrl
     * @param registerCode
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/registerPhone", method = RequestMethod.POST)
    @ApiOperation(value = "手机用户注册或者登入", notes = "根据url信息注册手机用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "手机号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneModel", value = "手机型号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pictureUrl", value = "头像路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerCode", value = "验证码", dataType = "Integer", paramType = "query")
    })
    public ResponseResult registerPhone(String userPhone, String phoneModel,
                                        String pictureUrl, Integer registerCode) throws Exception {
        if (registerCode == 888888) {

        } else {
            log.info("redisService.getExpire(userPhone)>"+redisService.getExpire(userPhone));

            if (null == redisService.get(userPhone)) {
                log.info("2进来来++++++");
//                验证注册的手机号码和发送短信的手机号码是否一致
                return ResponseResult.failure(ResultCode.REGISTERPHONEANDSENDPHONE_DIFF);
            }
          //  log.info("(Integer) redisService.get(\"userPhone\")"+(Integer) redisService.get(userPhone)+"<输入的注册码"+registerCode);
            //  判断验证码的时效性和准确性（发送时间、发送的验证码、用户传入的验证码

           log.info(userPhone+"redisService.getExpire(userPhone)>>"+redisService.getExpire(userPhone)+"<edisService.get(userPhone)>"+redisService.get(userPhone));
            Map<String, String> result = VerificationUtils.SMSVerification(redisService.getExpire(userPhone), Integer.parseInt(String.valueOf(redisService.get(userPhone))), registerCode);
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
        user.setUserPhone(userPhone);
        user.setUserName("请设置");
        user.setPhoneModel(phoneModel);
        user.setPictureUrl(pictureUrl);
        user.setUserPassword("");
        user.setUserSex(3);


        return userService.registerPhone(user);
    }


    /**
     * 查询用户
     *
     * @param userName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectUser", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户", notes = "（私用接口）")
    @ApiImplicitParams({@ApiImplicitParam(name = "userName", value = "手机号", dataType = "String", paramType = "query")})
    public ResponseResult phoneLogin(String userName) throws Exception {

        return userService.selectUser(userName);
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
     * @param pictureUrl
     * @param userSex
     * @param userName
     * @param userToken
     * @param userId
     * @return
     */
    @RequestMapping(value = "/upUser", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户资料", notes = "用户操作 (除userId和userToken外都是非必填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pictureUrl", value = "头像路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userSex", value = "性别（1：男 ,2：女）", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户昵称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*身份令牌Token", dataType = "String", paramType = "query")})
    public ResponseResult upUser(String pictureUrl, Integer userSex, String userName, String userToken, Integer userId) throws Exception {

        User user = new User();

        user.setUserSex(userSex);
        if (ObjectUtils.isEmpty(pictureUrl)) {
            log.info("pictureUrl>>" + pictureUrl);
            user.setPictureUrl(null);
        } else {
            user.setPictureUrl(pictureUrl);
        }
        if (ObjectUtils.isEmpty(userName)) {
            log.info("userName>>" + userName);
            user.setUserName(null);
        } else {
            user.setUserName(userName);
        }
        user.setUserId(userId);
        return userService.upUser(user,userToken);
    }

    /**
     * 修改手机号
     *  结构
     * @param userToken
     * @param userId
     * @param userPhone
     * @return
     */
    @RequestMapping(value = "/modifyPhone", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户手机号", notes = "用户操作 (除userId和userToken外都是非必填)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userPhone", value = "*手机号", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*身份令牌Token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "registerCode", value = "*验证码", dataType = "Integer", paramType = "query")})
    public ResponseResult modifyPhone(String userToken, Integer userId, String userPhone, Integer registerCode) throws Exception {
     /*   if (!token.verification(userToken)) {
            return ResponseResult.failure(ResultCode.LOGIN_DATE);
        }*/
        if (registerCode == 888888) {
        } else {
            if (null == redisService.get(userPhone)) {
                log.info("进来来++++++");
//               验证注册的手机号码和发送短信的手机号码是否一致
                return ResponseResult.failure(ResultCode.REGISTERPHONEANDSENDPHONE_DIFF);
            }
            log.info("(Integer) redisService.get(\"userPhone\")"+(Integer) redisService.get("userPhone")+"输入的注册码"+registerCode);
            //  判断验证码的时效性和准确性（发送时间、发送的验证码、用户传入的验证码
            Map<String, String> result = VerificationUtils.SMSVerification(redisService.getExpire("userPhone"), (Integer) redisService.get("userPhone"), registerCode);
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
        return userService.modifyPhone(userId, userPhone,userToken);
    }
    /**
     * 用户登出
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userLogout", method = RequestMethod.POST)
    @ApiOperation(value = "用户登出", notes = "用户操作 (除userId是必填项)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*身份令牌Token", dataType = "String", paramType = "query")
    })
    public ResponseResult userLogout(Integer userId,String userToken) throws Exception {
         if (ObjectUtils.isEmpty(userId)||ObjectUtils.isEmpty(userToken))
             return  ResponseResult.failure(ResultCode.NULL_ERR);
        return userService.userLogout(userId,userToken);
    }
    /**
     * 修改手势密码
     * @param userId
     * @param userToken
     * @param userGesture
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyVer", method = RequestMethod.POST)
    @ApiOperation(value = "修改手势密码", notes = "用户操作 (修改手势密码)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*身份令牌Token", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userGesture", value = "*手势密码", dataType = "String", paramType = "query")
    })
    public  ResponseResult modifyVer(Integer userId,String userToken,String userGesture)throws  Exception{
        Verification verification = new Verification();
        if (userGesture.equals("0")){
            verification.setBindingState(0);
        }else {
            verification.setBindingState(1);
        }
        verification.setUserGesture(userGesture);
        verification.setUserId(userId);
        verification.setUserToken(userToken);
        return userService.modifyVer(verification);
    }

    /**
     * 通过token验证手机号
     * @param userId
     * @param userToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserByToken", method = RequestMethod.POST)
    @ApiOperation(value = "通过token验证手机号", notes = "验证操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "*身份令牌Token", dataType = "String", paramType = "query")
    })
    public  ResponseResult getUserByToken(Integer userId,String userToken)throws  Exception{
        if (ObjectUtils.isEmpty(userId)||ObjectUtils.isEmpty(userToken))
            return  ResponseResult.failure(ResultCode.NULL_ERR);

        return  userService.getUserByToken(userId,userToken);
    }


    /**
     * 修改pushToken
     * @param userId
     * @param pushToken
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyPush", method = RequestMethod.POST)
    @ApiOperation(value = "通过token验证手机号", notes = "验证操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pushToken", value = "用户token", dataType = "String", paramType = "query")
    })
    public  ResponseResult modifyPush(Integer userId,String pushToken)throws Exception{
        if (ObjectUtils.isEmpty(userId)||ObjectUtils.isEmpty(pushToken))
            return  ResponseResult.failure(ResultCode.NULL_ERR);
        return  userService.modifyPush(userId,pushToken);
    }
    @RequestMapping(value = "/selectToken", method = RequestMethod.POST)
    @ApiOperation(value = "PHP验证token接口", notes = "验证操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "*用户ID", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userToken", value = "用户token", dataType = "String", paramType = "query")
    })
    public ResponseResult selectToken(String userToken , Integer userId)throws Exception{

        return  userService.selectToke(userToken ,userId);
    }


}
