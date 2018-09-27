package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.ApiPostUtil;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BF100269 on 2016/10/9.
 * 身份证认证(两要素不返照片)
 */
public class DevicePc{
//    String url = "https://dfp.xinyan.com/gateway/device-engine-pc/pc/v1/gather";     //MOCK测试
    String url = "https://10.0.21.74:8901/gateway/device-engine-pc/pc/v1/gather";     //MOCK测试
    public static final String CLIENT_KEY = "DEVICE-AES000002";
    @Test
    public void runTest(){
        JSONObject jsonDto = new JSONObject();
        String trans_id =  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        jsonDto.put("merchantNo","8000013190");
//        jsonDto.put("token",trans_id+ "00000000001");
//        jsonDto.put("token","NOVAL");
//        jsonDto.put("token","2018061909463849600007700001");
//        一级匹配参数
        jsonDto.put("colorDepth","734");
        jsonDto.put("devicePixelRatio","021.8");
        jsonDto.put("cpuClass","123123");
        jsonDto.put("platform","Win62");
        jsonDto.put("webglVendor","12341377755");

        jsonDto.put("everCookie", "201806121608424482312312312312777115265");
        jsonDto.put("userAgent", "Mozilla/4.0 (compatible; MSIE 11.2; Windows NT 77.1)");
        jsonDto.put("language","zh-CW3");
        jsonDto.put("userLanguage","");
        jsonDto.put("systemLanguage","");
        jsonDto.put("resolution","1360*768");
        jsonDto.put("timeZone","12312");
        jsonDto.put("openDatabase","1");
        jsonDto.put("jsFonts","321");
        jsonDto.put("deviceMemory","12312312312321");
        jsonDto.put("sessionStorage","1");
        jsonDto.put("localStorage","1");
        jsonDto.put("hashIndexedDB","1");
        jsonDto.put("addBehavior","false");
        jsonDto.put("doNotTrack","null");
        jsonDto.put("excludeOpenDatabase","true");
        jsonDto.put("plugins","{\"0\":{\"0\":{}},\"2\":{\"0\":{}},\"2\":{\"0\":{},\"1\":{}},\"3\":{\"0\":{}}}");
        jsonDto.put("adBlock","0");
        jsonDto.put("pretendSystem","false");
        jsonDto.put("pretendResolution","false");
        jsonDto.put("pretendBrowser","false");
        jsonDto.put("canvasId","00e0000e00e10099");
        jsonDto.put("webglRenderer","63aeba7211aacb6654659aa1a777777");
        jsonDto.put("cameraId","12312312111112");
        jsonDto.put("audioId","123123123123123");
        jsonDto.put("microphone","123123123123123");
        jsonDto.put("deviceSys","IOS");    //PC IOS ANDROID
        jsonDto.put("pretendResolution", "0");
        jsonDto.put("hasLiedLanguages", "0");
        jsonDto.put("pretendSystem", "0");
        jsonDto.put("pretendBrowser", "0");
        jsonDto.put("extra", "TEST12312");


        jsonDto.put("videoId","123123123211123123");
        jsonDto.put("hardwareConcurrency", "123111");
        jsonDto.put("touchSupport", "123112");
        jsonDto.put("pixelRatio", "123112");
        jsonDto.put("appName", "atms");

        jsonDto.put("sysVer","1.3.1");
        jsonDto.put("sdkVer","2.3.1");
        jsonDto.put("browserName","火狐浏览器");
        jsonDto.put("browserVer","7.3.4");
        jsonDto.put("networkType","移动2G");
        String requestData = jsonDto.toString();
        System.out.println("明文请求：" + jsonDto.toString());
        try {
            requestData = AesUtil.encrypt(requestData, CLIENT_KEY);
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jsonDto1 = new JSONObject();
        jsonDto1.put("message", requestData);
        System.out.println("加密报文：" + jsonDto1.toString());
        ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);

        try {
            String post = postUtil.sendHttps(jsonDto1.toString(), "POST", "UTF-8");
            System.out.println("返回结果密文：" + new String(post.getBytes(), "UTF-8"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}