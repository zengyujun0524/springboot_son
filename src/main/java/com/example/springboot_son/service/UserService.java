package com.example.springboot_son.service;

import com.example.springboot_son.entity.User;
import com.example.springboot_son.entity.Verification;
import com.example.springboot_son.entity.VideoInfo;
import com.example.springboot_son.mapper.UserMapper;
import com.example.springboot_son.mapper.VideoInfoMapper;
import com.example.springboot_son.utils.ObjectUtils;
import com.example.springboot_son.utils.ResponseResult;
import com.example.springboot_son.utils.ResultCode;
import com.example.springboot_son.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class UserService {


    @Resource
    private UserMapper userMapper; // 用户mapper

    @Resource
    private VideoInfoMapper videoInfoMapper;//修改视频
    /**
     * 手机注册
     * @param user
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult registerPhone(User user) throws Exception{
        Integer index = 0;
        Map<String, Object> data = new HashMap<String, Object>();
        User checkUser = new User();
        // 设置token
        Date date = new Date();
        // 设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取String类型的时间
        String createdate = sdf.format(date);
        String token = user.getUserPassword() + "zyj" + createdate;
        // 加密密码
        final Base64.Encoder encoder = Base64.getEncoder();
        final byte[] textByte = token.getBytes("UTF-8");
        // 编码
        log.info("-------用户注册-------"+user.getUserPhone());
        final String encodedText = encoder.encodeToString(textByte);
        checkUser=  userMapper.checkRegisterPhone(user.getUserPhone());
        String tokendate=encodedText+"-"+System.currentTimeMillis();
          if (checkUser==null){
              log.info("-------用户注册-------");
              log.info("insert INTO `user` (user_name,user_password,user_toke,phone_model,picture_url,data_time,user_sex)" +
                      " VALUES("+user.getUserName()+","+user.getUserPassword()+","+user.getPhoneModel()+
                      ","+user.getPictureUrl()+","+user.getDataTime()+"-"+user.getUserSex()+")");
              try {
                  user.setDataTime(createdate);
//                  user.setUser_token(tokendate);
              //开始注册
                  index=  userMapper.registerPhone(user);
                  if (index>0){
                      log.info("-------测试1-------");

                      checkUser=  userMapper.checkRegisterPhone(user.getUserPhone());
                    if (userMapper.inserVer(checkUser.getUserId(),tokendate)>0) {
                        log.info("-------测试5-------");
                        Verification verification = userMapper.getVer(checkUser.getUserId());
                        UserVo userVo =new UserVo(checkUser.getUserId(),checkUser.getUserName(),checkUser.getUserPassword(),checkUser.getPhoneModel()
                                ,checkUser.getPictureUrl(),checkUser.getDataTime(),checkUser.getUserSex(),checkUser.getUserPhone(),verification.getUserToken(),
                                verification.getUserGesture(),verification.getBindingState(),1);
                        data.put("userVo",userVo);
                        return ResponseResult.success(data);
                    }


                  }
              }catch(Exception e){
                  //异常处理
                  throw new Exception("抛异常了");

              }
          }

    log.info(tokendate+"-------登入-------"+user.getUserId()+tokendate);
    int index2 =userMapper.upToken(tokendate,checkUser.getUserId());
          if (index2>0){
            Verification   verification = userMapper.getVer(checkUser.getUserId());
              UserVo userVo =new UserVo(checkUser.getUserId(),checkUser.getUserName(),checkUser.getUserPassword(),checkUser.getPhoneModel()
                      ,checkUser.getPictureUrl(),checkUser.getDataTime(),checkUser.getUserSex(),checkUser.getUserPhone(),verification.getUserToken(),
                      verification.getUserGesture(),verification.getBindingState(),2);
                   data.put("userVo",userVo);
              return ResponseResult.success(data);
          }
        log.info("-------测试3-------");
       return ResponseResult.failure("toekn更新失败");
    }

    /**
     * 查询用户
     * @param user_phone
     * @return
     * @throws Exception
     */
   public  ResponseResult selectUser(String user_phone) throws Exception{
        log.info("-------查询用户 ------- ");
       try {
           User user =new User();
            user=userMapper.checkRegisterPhone(user_phone);
           log.info("-------user>>>>>>"+user);
            if (user!=null){
                log.info("查询成功");
                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",user);
                return ResponseResult.success(data);
            }
       }catch (Exception e){
           e.printStackTrace();
  //
       }
       return  ResponseResult.failure("用户不存在");
    }


    /**
     * 修改用户资料
     * @param user
     * @return
     * @throws Exception
     */
    public ResponseResult upUser( User  user,String user_token) throws Exception{
        log.info("-------修改用户资料 -------");
       try {
           if (!verification(user_token,user.getUserId())){

               log.info("-------修改用户资料 -------进来了");
               return ResponseResult.failure(ResultCode.LOGIN_DATE);
           }
           int index=userMapper.upUser(user);
            if (index>0){
                log.info("-------修改成功 -------");
                User user1 =userMapper.queryUers(user.getUserId());
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.setUserId(user1.getUserId());
                videoInfo.setAuthorName(user1.getUserName());
                videoInfo.setAuthorPictureUrl(user1.getPictureUrl());
                //修改视频作者名称
                  videoInfoMapper.updateNameById(videoInfo);

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",user1);
                return  ResponseResult.success(data);
            }

       }catch (Exception e){
           e.printStackTrace();
       }
          return  ResponseResult.failure(ResultCode.MODIFICATION_FAILED);
    }

    /**
     * 修改手机号
     * @param user_id
     * @param user_phone
     * @return
     * @throws Exception
     */
    public  ResponseResult modifyPhone(Integer user_id,String user_phone,String user_token)throws Exception {
        try {
            //验证token
            if (verification(user_token,user_id)){
                if (ObjectUtils.isNotNull(userMapper.checkRegisterPhone(user_phone))){
                    return  ResponseResult.failure(ResultCode.PHONE_NULL);
                }
                int index =userMapper.modifyPhone(user_id,user_phone);
                if (index>0){
                    User user= new User();
                    user=userMapper.queryUers(user_id);
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("user",user);
                    return ResponseResult.success(data);
                }
            }


           }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseResult.failure(ResultCode.LOGIN_DATE);
    }

    /**
     * 用户登出
     * @param user_id
     * @return
     * @throws Exception
     */
    public  ResponseResult userLogout(Integer user_id ,String user_token)throws Exception{
         try {
             //验证token
                if (verification(user_token,user_id)){
                    if (userMapper.userLogout(user_id)>0){

                        return ResponseResult.success();
                    }

                }

         }catch (Exception e){
             e.printStackTrace();
         }
             return ResponseResult.failure(ResultCode.LOGIN_DATE);
    }

    /**
     * 修改验证数据
     * @param verification
     * @return
     * @throws Exception
     */
    public  ResponseResult modifyVer(Verification  verification)throws  Exception{
        try {
            //验证token
            if (verification(verification.getUserToken(),verification.getUserId())){
                log.info("验证成功");
                if (userMapper.modifyVer(verification)>0){
                    return ResponseResult.success();
                }
                }
        }catch (Exception e){
           e.printStackTrace();
        }
        return ResponseResult.failure(ResultCode.LOGIN_DATE);
    }

    /**
     *修改pushtoken
     * @param user_id
     * @param push_token
     * @return
     * @throws Exception
     */
    public  ResponseResult modifyPush(int user_id,String push_token )throws  Exception {
        try {
            return userMapper.modifyPush(user_id, push_token) == true ? ResponseResult.success() : ResponseResult.failure("token传入失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failure(ResultCode.TOKEN_ERR);
    }

    /**
     * 通过token验证手机号
     * @param user_id
     * @param user_token
     * @return
     * @throws Exception
     */
    public ResponseResult getUserByToken (int user_id,String user_token)throws  Exception{
       try {
            Verification verification =userMapper.getUserByToken(user_id,user_token);
            if (ObjectUtils.isNotNull(verification)){

                Map<String, Object> data = new HashMap<String, Object>();
                data.put("user",userMapper.queryUers(user_id));
                return ResponseResult.success(data);
            }

       }catch (Exception e){
           e.printStackTrace();
       }

        return  ResponseResult.failure(ResultCode.VER_ERR);
    }

    /**
     * PHP验证用户token
     *
     * @param userToken
     * @return
     */
    public  ResponseResult selectToke(String userToken, Integer userId){
         try{
             if (userMapper.selectToken(userToken,userId)!=null){
               Map<String, Object> data = new HashMap<String, Object>();
                 User user = userMapper.queryUers(userId);
                 data.put("user",user);
                 return ResponseResult.success(data);
         }
         }catch (Exception e){
             e.printStackTrace();
         }
        return  ResponseResult.failure(ResultCode.VER_ERR);
    }

    public  boolean  verification(String user_token,Integer user_id) throws Exception {

        if (ObjectUtils.isEmpty(user_token)){
            return false ;
        }
        Verification verification1 = new Verification();
        try {
            verification1  =userMapper.getVer(user_id);
            if (verification1==null) {
                return false;
            }
            if (!verification1.getUserToken().equals(user_token)||verification1.getUserToken()==null) {
                log.info("user_token>>>>>"+user_token);
                log.info("token失效111"+verification1.getUserToken());
                return false;
            }
        }catch (Exception e){

            e.printStackTrace();
        }

        // 一个月时间 2592000
        String sub=user_token.substring(user_token.indexOf("-")+1);
        long l = Long.parseLong( sub )/1000;
        long i= System.currentTimeMillis()/1000;
        if (i-l<2592000){
            log.info("成功"+verification1.getUserToken());
            return true;
        }

        return  false;
    }



//    public static boolean isEmpty(String str) {
////        return str == null || str.trim().length() == 0 || "null".equals(str.trim());
////    }
}

