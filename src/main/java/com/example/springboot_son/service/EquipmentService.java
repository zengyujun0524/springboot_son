/**
 * @author 曾俞钧
 * @date 2020/3/21 14:44
 * @version 1.0
 */
        package com.example.springboot_son.service;
        import com.example.springboot_son.entity.Equipment;
        import com.example.springboot_son.mapper.EquipmentMapper;
        import com.example.springboot_son.utils.ResponseResult;
        import com.example.springboot_son.vo.MainPageVo;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.HashMap;
        import java.util.LinkedList;
        import java.util.List;
        import java.util.Map;


@Service
public class EquipmentService {
    private static Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    EquipmentMapper equipmentMapper;

    /**
     * 获取设备信息
     * @return
     * @throws Exception
     */
    public ResponseResult equipmentInfo(Integer user_id) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        //获取所有设备信息
        try {

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


}

