package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.AesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by BF100269 on 2016/10/9.
 * 身份证认证(两要素不返照片)
 */
public class DevicePc{
//    String url = "https://dfp.xinyan.com/gateway/device-engine-pc/pc/v1/gather";     //MOCK测试
//    String url = "http://112.65.201.107:8080/applet/v1/searchXyid";     //MOCK测试
//    String url = "https://10.0.21.74:8901/gateway/device-engine-pc/pc/v2/gather";
String url = "http://10.0.70.65:8104/pc/v1/searchXyid";
    public static final String CLIENT_KEY = "DEVICE-AES000002";
    @Test
    public void runTest(){
        for (int i =0; i< 1; i++) {
            JSONObject jsonDto = new JSONObject();
            String trans_id =  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            jsonDto.put("merchantNo","8013932512");
//        jsonDto.put("token",trans_id+ "00000000001");
//        jsonDto.put("token","NOVAL");
//        jsonDto.put("token","2018061909463849600007700001");
//        一级匹配参数
            jsonDto.put("colorDepth","852");
            jsonDto.put("cpuClass","123134s");
            jsonDto.put("platform","Win64");
//            jsonDto.put("webglVendor","123456789");
            jsonDto.put("webglVendor","123456");
            jsonDto.put("everCookie", UUID.randomUUID());
            jsonDto.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0; rv:11.0) like Gecko");
            jsonDto.put("language","zh-CN");
            jsonDto.put("resolution","1360*768");
            jsonDto.put("timeZone","-480");
            jsonDto.put("openDatabase","undefined");
            jsonDto.put("jsFonts","123456");
            jsonDto.put("deviceMemory","12312312312321");
            jsonDto.put("sessionStorage","1");
            jsonDto.put("localStorage","1");
            jsonDto.put("hashIndexedDB","1");
            jsonDto.put("plugins","Shockwave Flash::Shockwave Flash 27.0 r0::application/x-shockwave-flash~swf,application/futuresplash~spl");
            jsonDto.put("adBlock","0");
            jsonDto.put("pretendSystem","0");
            jsonDto.put("pretendResolution","0");
            jsonDto.put("pretendBrowser","0");
            jsonDto.put("canvasId","00e0000e00e10099");
            jsonDto.put("webglRenderer","63aeba7211aacb6654659aa1a777777");
            jsonDto.put("cameraId","");
            jsonDto.put("audioId","");
            jsonDto.put("microphone","");
            jsonDto.put("deviceSys","PC");    //PC IOS ANDROID
            jsonDto.put("pretendResolution", "0");
            jsonDto.put("hasLiedLanguages", "0");
            jsonDto.put("pretendSystem", "0");
            jsonDto.put("pretendBrowser", "0");
            jsonDto.put("extra", "TEST12312");


            jsonDto.put("videoId","");
            jsonDto.put("hardwareConcurrency", "");
            jsonDto.put("touchSupport", "");
            jsonDto.put("pixelRatio", "1");
            jsonDto.put("appName", "oms-测试1");

            jsonDto.put("sysVer","1.3.1");
            jsonDto.put("sdkVer","2.3.1");
            jsonDto.put("browserName","火狐浏览器");
            jsonDto.put("browserVer","7.3.4");
            jsonDto.put("networkType","移动2G");
            String requestData = jsonDto.toString();
            System.out.println("明文请求：" + jsonDto.toString());
        try {
            requestData = AesUtil.encrypt(requestData, "7d5066673143510f");
        }catch (Exception e){
            e.printStackTrace();
        }
            JSONObject req = new JSONObject();
        req.put("message",requestData);
//            try {
//                ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);
//                String responseData = postUtil.sendHttps(req.toJSONString(),"POST","UTF-8");
////            String responseData = HttpRequestUtil.sendHttpPost1(url, jsonDto1.toString()).toString();
//                System.out.println("返回结果密文：" + new String(responseData.getBytes(), "UTF-8"));
//            } catch (Exception e){
//                e.printStackTrace();
//            }
            try {
                RestTemplate restTemplate = new RestTemplate();
                String s = restTemplate.postForObject(url, jsonDto, String.class);
//            String post = HttpRequestUtil.sendPost(requestData, "POST", "UTF-8");
                System.out.println("返回结果密文：" + new String(s.getBytes(), "UTF-8"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}