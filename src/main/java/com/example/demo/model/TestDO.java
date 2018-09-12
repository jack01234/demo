package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 13:55 2018/9/7
 */
@Data
public class TestDO implements Serializable {
    /**
     *
     * 序列化ID
     *
     */
    private static final long serialVersionUID = 7324496333591215857L;

    private Integer id;

    private String cookie;
}
