/**
 * @author 曾俞钧
 * @date 2020/3/23 10:48
 * @version 1.0
 */
package com.example.springboot_son.mapper;

import com.example.springboot_son.entity.Relation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RelationMapper {
    //查看设备状态
       List<Relation> checkState(int user_id)  throws Exception;


}
