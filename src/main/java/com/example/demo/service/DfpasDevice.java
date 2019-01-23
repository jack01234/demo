package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.ApiPostUtil;
import com.example.demo.utils.RsaCodingUtil;
import com.example.demo.utils.SecurityUtil;
import com.system.commons.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 10:19 2018/12/6
 */
@Slf4j
public class DfpasDevice {

    String url = "https://dfp.xinyan-ai.com/gateway/dfpas/radar/v1/dfpRadar";
    public static String keyPath = "E://设备指纹相关/证书/zuoshi_xinyan_pri.pfx";
    public static String keyPwd = "123456";
    @Test
    public void test(){

        JSONObject json = new JSONObject();
        json.put("id_name","李文明");
        json.put("id_no","411423198911122018");
        json.put("phone_no","18637002608");
        json.put("trade_date",DateUtil.format(new Date(),DateUtil.fullPattern));
        json.put("versions","1.3.0");
        json.put("industry_type","A5");
        json.put("trans_id",UUID.randomUUID());
        json.put("token","1811201852294450191102");
        json.put("bankcard_no","123456789");

        String data_content = getCryTextByRsa(json.toJSONString(),"json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("member_id","8013932531");
        jsonObject.put("terminal_id","1811070000");
        jsonObject.put("encryption_type","RSA");
        jsonObject.put("data_type","json");
        jsonObject.put("data_content",data_content);

        try {
            ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);
            String responseData = postUtil.sendHttps(jsonObject.toJSONString(),"POST","UTF-8");
//            String responseData = HttpRequestUtil.sendHttpPost1(url, jsonDto1.toString()).toString();
            System.out.println("返回结果密文：" + new String(responseData.getBytes(), "UTF-8"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static String getCryTextByRsa(String ss, String data_type){
        try {
            System.out.println("签名前报文: "+ss);
            String result = RsaCodingUtil.encryptByPriPfxFile(SecurityUtil.Base64Encode(ss), keyPath, keyPwd);
            System.out.println("签名后报文:"+result);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
