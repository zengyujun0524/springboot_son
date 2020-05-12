/**
 * @author
 * @date 2020/3/21 14:44
 * @version 1.0  http://123.56.73.147/
 */
        package com.example.springboot_son.service;
        import com.example.springboot_son.entity.Equipment;
        import com.example.springboot_son.entity.StaticResources;
        import com.example.springboot_son.entity.Verification;
        import com.example.springboot_son.mapper.ApplicationMapper;
        import com.example.springboot_son.mapper.EquipmentMapper;
        import com.example.springboot_son.mapper.UserMapper;
        import com.example.springboot_son.utils.ObjectUtils;
        import com.example.springboot_son.utils.ResponseResult;
        import com.example.springboot_son.utils.ResultCode;
        import com.example.springboot_son.vo.EquipmentVo;
        import lombok.extern.slf4j.Slf4j;
        import org.joda.time.DateTime;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import javax.annotation.Resource;
        import java.util.HashMap;
        import java.util.LinkedList;
        import java.        util.List;
        import java.util.Map;

@Slf4j
@Service
public class EquipmentService {


    @Resource
    EquipmentMapper equipmentMapper;

      @Resource
    ApplicationMapper applicationMapper;

    @Resource
    UserMapper userMapper;
    /**
     * 获取设备信息
     * @return bin
     * @throws Exception
     */
    public ResponseResult equipmentInfo(Integer userId) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        //获取所有设备信息
        try {
    //   equipmentMapper ->Integer.compare(1,2);
//            List<MainPageVo> list = new LinkedList<MainPageVo>();

            List<Equipment> list = new LinkedList<Equipment>();

            log.info(">>>>>>>>>>>>"+userId);
            list = equipmentMapper.equipmentInfo(userId);
            data.put("list", list);
            List <StaticResources>  listPicture =applicationMapper.listPicture();
            data.put("staticResources",listPicture);
            return ResponseResult.success(data);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return ResponseResult.failure("查询失败");
    }

    /**
     * 修改设备名称
     * @param equipmentName
     * @param relationId
     * @return
     * @throws Exception
     */
   public ResponseResult modifyName(String equipmentName,Integer relationId)throws Exception{
        try {
            //修改设备名称
            return equipmentMapper.modifyName(equipmentName,(int) (DateTime.now().getMillis() / 1000),relationId)>0?
                    ResponseResult.success():ResponseResult.failure(ResultCode.NULL_ERR);

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseResult.failure(ResultCode.NULL_ERR);
    }

    /**
     * 查询绑定设备
     * @param userId
     * @return
     * @throws Exception
     */
    public  ResponseResult bindingDevice (Integer userId,Integer equipment_id,String userToken ) throws  Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        //获取所有设备信息
        try {
        if (!verification(userToken,userId)){
            log.info("token失效"+userToken);
            return  ResponseResult.failure(ResultCode.LOGIN_DATE);
        }
            List<EquipmentVo> list = equipmentMapper.bindingDevice(userId,equipment_id);
            log.info(">>>>>>>>>>>>"+userId);
            data.put("list", list);
            return ResponseResult.success(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.failure("查询失败");
    }
//
    public  boolean  verification(String userToken,Integer userId) throws Exception {

        if (ObjectUtils.isEmpty(userToken)){
            return false ;
        }
        Verification verification1 = new Verification();
        try {
            verification1  =userMapper.getVer(userId);
            if (verification1==null) {
                return false;
            }
            log.info("token失效"+verification1.getUserToken());

            if (!verification1.getUserToken().equals(userToken)||verification1.getUserToken()==null) {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // 一个月时间 2592000
        String sub=userToken.substring(userToken.indexOf("-")+1);
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
