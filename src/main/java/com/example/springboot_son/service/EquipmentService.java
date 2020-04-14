/**
 * @author 曾俞钧
 * @date 2020/3/21 14:44
 * @version 1.0
 */
        package com.example.springboot_son.service;
        import com.example.springboot_son.entity.Equipment;
        import com.example.springboot_son.entity.User;
        import com.example.springboot_son.entity.Verification;
        import com.example.springboot_son.mapper.EquipmentMapper;
        import com.example.springboot_son.mapper.UserMapper;
        import com.example.springboot_son.utils.ObjectUtils;
        import com.example.springboot_son.utils.ResponseResult;
        import com.example.springboot_son.utils.ResultCode;
        import com.example.springboot_son.vo.MainPageVo;
        import lombok.extern.slf4j.Slf4j;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.HashMap;
        import java.util.LinkedList;
        import java.        util.List;
        import java.util.Map;

@Slf4j
@Service
public class EquipmentService {


    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    UserMapper userMapper;
    /**
     * 获取设备信息
     * @return bin
     * @throws Exception
     */
    public ResponseResult equipmentInfo(Integer user_id) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        //获取所有设备信息
        try {
    //   equipmentMapper ->Integer.compare(1,2);
//            List<MainPageVo> list = new LinkedList<MainPageVo>();

            List<Equipment> list = new LinkedList<Equipment>();

            log.info(">>>>>>>>>>>>"+user_id);
            list = equipmentMapper.equipmentInfo(user_id);
            data.put("list", list);
            return ResponseResult.success(data);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return ResponseResult.failure("查询失败");
    }

    /**
     * 查询绑定设备
     * @param user_id
     * @return
     * @throws Exception
     */
    public  ResponseResult bindingDevice (Integer user_id,Integer equipment_id,String user_token ) throws  Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        //获取所有设备信息
        try {
        if (!verification(user_token,user_id)){
            log.info("token失效"+user_token);
            return  ResponseResult.failure(ResultCode.LOGIN_DATE);
        }
            List<Equipment> list = new LinkedList<Equipment>();
            log.info(">>>>>>>>>>>>"+user_id);
            list = equipmentMapper.bindingDevice(user_id,equipment_id);
            data.put("list", list);
            return ResponseResult.success(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failure("查询失败");
    }
//
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
            log.info("token失效"+verification1.getUser_token());

            if (!verification1.getUser_token().equals(user_token)||verification1.getUser_token()==null) {
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
            return true;
        }

        return  false;
    }
//    public static boolean isEmpty(String str) {
//        return str == null || str.trim().length() == 0 || "null".equals(str.trim());
//    }


}
