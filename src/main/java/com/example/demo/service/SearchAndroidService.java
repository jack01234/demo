package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.bo.AndroidDeviceInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 14:57 2018/9/19
 */
@Slf4j
public class SearchAndroidService {
    private static String url = "";
    @Test
    public void searchTest(){
        AndroidDeviceInfoBO req = new AndroidDeviceInfoBO();
        req.setGivingXyid("11809031117023550370101");
        req.setImei("123");
        req.setImsi("456");
        req.setAndroidId("123456");
        req.setTotalSize("123456");
        req.setFcpu("456");
        req.setMac("123");
        req.setPhoneNum("18637002608");
        req.setDeviceName("123");
        req.setBios("123");
        req.setCpu("123");
        req.setScreenRes("123");
        req.setUnitType("123");
        req.setSysVer("123");
        req.setFontNum("123");
        req.setIsRoot("0");
        req.setSimulator("0");
        req.setSystemLan("123");
        req.setTimeZone("123");
        String s = JSONObject.toJSONString(req);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> result = template.postForEntity("", s, String.class);
        String body = result.getBody();
    }
}
