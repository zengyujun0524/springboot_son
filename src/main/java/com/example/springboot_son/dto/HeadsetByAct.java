package com.example.springboot_son.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 曾俞钧
 * @version 1.0

 * @date 2020/4/3 9:59 上午
 */
@Data
@Table(name = "equ_binding")
public class HeadsetByAct {
    @Id
    @Column(name = "equ_id")
    private  Integer id;
    @Column(name = "serial_number")
    private String  serialNumber;
    @Column(name = "get_into_time")
    private String  intoTime;
    @Column(name = "activation_time")
    private String actTime;
    @Column(name = "binding_state")
    public Integer binState;

}
