package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.system.commons.utils.DateUtil;

import java.util.Date;
import java.util.UUID;

public class TestCreditApplyReport {
//    ##商户私钥  ##
//    pfx.name=bfkey_100000178@@100001164.pfx
//    ##商户私钥密码 ##
//    pfx.pwd=100000178_384015
//            ##宝付公钥 -##
//    cer.name=bfkey_100000178@@100001164.cer
//    ##终端号 ##
//    terminal.id=100001164
//            ##商户号 ##
//    member.id=100000178


    public static String keyPath = "E://设备指纹相关/证书/zuoshi_xinyan_pri.pfx";
    public static String keyPwd = "123456";
    public static String cerPath = "/Users/ikfly/work/baofoo/bfkey_100000178@@100001164.cer";

    public static String memberId = "100000178";
    public static String terminalId = "100001164";
    public static void main(String[] args) {



        JSONObject json = new JSONObject();
        json.put("id_name","李文明");
        json.put("id_no","411423198911122018");
        json.put("phone_no","18637002608");
        json.put("trade_date",DateUtil.format(new Date(),DateUtil.fullPattern));
        json.put("versions","1.3.0");
        json.put("industry_type","A5");
        json.put("trans_id",UUID.randomUUID());
        json.put("token","1812291523182250034104");
        json.put("bankcard_no","123456789");

//        String data_content = getCryTextByRsa(json.toJSONString(),"json");
        String test000000000001 = AesUtil.encrypt(json.toJSONString(), "test000000000001");
        System.out.println(test000000000001);
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
