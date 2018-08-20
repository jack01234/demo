package com.example.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 16:26 2018/8/17
 */
@Data
public class MessEncryptResDTO implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -2848520946697985929L;

    private String message;
}
