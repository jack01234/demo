package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * DO
 *
 * @author: yingmuhuadao
 * @date: Created in 17:25 2018/8/1
 */
@Data
public class BizInfoDO implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -2870405642885818458L;

    private String subMerchantNo;
    private String name;
    private String idNo;
    private String phoneNum;
    private String bankCardNo;

    public static void main(String[] args) {
        String ss = "1360*779";
        System.out.println(ss.hashCode());
    }
}
