package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.ApiPostUtil;
import org.junit.jupiter.api.Test;

/**
 * 陀螺仪上送
 *
 * @author: yingmuhuadao
 * @date: Created in 10:45 2018/12/4
 */
public class GyroDevice {

    String url = "https://dfp.xinyan-ai.com/gateway/device-core/core/v1/sendGyro";


    @Test
    public void test(){
        System.out.println("1".hashCode());
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("message",AesUtil.encrypt("aaa","DEVICE-AES000001"));

        try {
            ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);
            String responseData = postUtil.sendHttps(jsonObject.toJSONString(),"POST","UTF-8");
//            String responseData = HttpRequestUtil.sendHttpPost1(url, jsonDto1.toString()).toString();
            System.out.println("返回结果密文：" + new String(responseData.getBytes(), "UTF-8"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
