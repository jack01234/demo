package com.example.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备目标特征和源特征算分请求DTO
 *
 * @author: yingmuhuadao
 * @date: Created in 15:15 2018/8/17
 */
@Data
public class DeviceGradeDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -4564786516632485374L;
    /**
     * 源特征信息
     */
    private String source;

    /**
     * 目标特征信息
     */
    private String target;
}
