package com.example.demo.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 算分响应DTO
 *
 * @author: yingmuhuadao
 * @date: Created in 15:30 2018/8/17
 */
@Data
public class DeviceGradeResDTO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 2986891905500638361L;

    /**
     * 分数
     */
    private BigDecimal score = new BigDecimal(0);

    /**
     * 加分字段
     */
    private String scoreField;

    /**
     * 为加分字段
     */
    private String loseField;

    /**
     * 结论
     */
    private String result;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * UA相似度
     */
    private BigDecimal similarity;
}
