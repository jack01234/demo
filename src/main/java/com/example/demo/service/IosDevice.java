package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.ApiPostUtil;
import com.example.demo.utils.GzipUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by BF100269 on 2016/10/9.
 * 身份证认证(两要素不返照片)
 */
public class IosDevice {
    String url = "https://10.0.21.74:8901/gateway/device-engine-client/ios/v1/gather";     //MOCK测试
//    String url = "http://10.1.60.44:8102/ios/v1/gather";     //MOCK测试


    //    String url = "http://47.100.183.62:9098/test";     //MOCK测试
    public static final String CLIENT_KEY = "DEVICE-AES000003";
    @Test
    public void runTest(){
        String trans_id =  new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        JSONObject jsonDto = new JSONObject();
        jsonDto.put("merchantNo", "8013932732");
        jsonDto.put("idfv", "123123147779996561427");
        jsonDto.put("xyid","123456789");
        jsonDto.put("devicename", "左师的手机");
        jsonDto.put("cpuType", "armtype:12");
        jsonDto.put("unittype", "CPU_TYPE_ARM64");
        jsonDto.put("sysVer", "7.3");
        jsonDto.put("simulator", "0");
        jsonDto.put("timezone", "Asia-ShanghaiGMT+8");
        jsonDto.put("systemlan", "zh-Hans-US");
        jsonDto.put("isJailbreak", "0");
        jsonDto.put("fontnum", "252");
        jsonDto.put("screenres", "414*736");
        jsonDto.put("mac", "1c:77:f6:2d:50:0e");
        jsonDto.put("diskSize", "63989493760");
        jsonDto.put("memorySize", "2084044800");
        jsonDto.put("sdkVersion", "1.0");
        jsonDto.put("deviceSys", "IOS");
        jsonDto.put("appName", "设备指纹演示01");
        jsonDto.put("location", "121.592713,31.214871");
        jsonDto.put("extra", "TEST12312");
        String requestData = jsonDto.toString();
        System.out.println("明文请求：" + jsonDto.toString());
        try {
            requestData = AesUtil.encrypt(requestData, CLIENT_KEY);
        } catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jsonDto1 = new JSONObject();
        jsonDto1.put("message", requestData);
        System.out.println("加密报文：" + jsonDto1.toString());
        try {
            ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);
            String responseData = postUtil.sendHttps(jsonDto1.toJSONString(),"POST","UTF-8");
//            String responseData = HttpRequestUtil.sendHttpPost1(url, jsonDto1.toString()).toString();
            System.out.println("返回结果密文：" + new String(responseData.getBytes(), "UTF-8"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /*压缩软件列表信息*/
    private byte[] compressSoft(List<String> list){
        String softList =   JSONObject.toJSONString(list);
        try {
            byte[] mByte = GzipUtils.compress(softList.getBytes());
            return mByte;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("zip error:" + e.getCause() + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        try {
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);
            requestFactory.setConnectTimeout(1000);
            requestFactory.setReadTimeout(5000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
            HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
            messageConverters.add(1,converter);
            restTemplate.setMessageConverters(messageConverters);

            URI uri = URI.create("https://dfp.xinyan.com/gateway/device-core/core/v1/queryDeviceInfoByToken");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token","1812190941234714282102");
            jsonObject.put("merchantNo","8150715562");
            jsonObject.put("transLogId",UUID.randomUUID());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAcceptCharset(Arrays.asList(StandardCharsets.UTF_8));
            HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toJSONString(),httpHeaders);
            ResponseEntity<String> exchange = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);
            System.out.println(exchange);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}