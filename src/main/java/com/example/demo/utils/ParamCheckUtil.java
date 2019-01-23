package com.example.demo.utils;


import com.example.demo.common.CommonConstant;
import com.example.demo.model.bo.PcDeviceInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 相关参数校验
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/4
 */
@Slf4j
public class ParamCheckUtil {

    private static String REX = "(?<=\\/)[^\\/]+";

    private static String REX_PATTERN = "(.*)U(.*)Android";
    /**
     * 默认值
     */
    public static final String NO_VAL = "NOVAL";
    /**
     * 参数判断
     * @param req
     * @return result
     */
    public static boolean paramsCheck(PcDeviceInfoBO req){
        if ("PC".equalsIgnoreCase(req.getDeviceSys())) {
            if (NO_VAL.equals(req.getWebglRenderer()) && NO_VAL.equals(req.getAudioId()) &&
                    NO_VAL.equals(req.getVideoId())) {
                return true;
            }
            if (NO_VAL.equals(req.getAudioId()) && NO_VAL.equals(req.getVideoId())
                    && NO_VAL.equals(req.getCanvasId())) {
                return true;
            }

            if (NO_VAL.equals(req.getVideoId()) && NO_VAL.equals(req.getCanvasId()) &&
                    NO_VAL.equals(req.getWebglRenderer())) {
                return true;
            }

            if (NO_VAL.equals(req.getCanvasId()) && NO_VAL.equals(req.getWebglRenderer()) &&
                    NO_VAL.equals(req.getAudioId())) {
                return true;
            }

            if (NO_VAL.equals(req.getWebglRenderer())&&NO_VAL.equals(req.getAudioId()) &&
                    NO_VAL.equals(req.getVideoId()) && NO_VAL.equals(req.getCanvasId())) {
                return true;
            }
        } else {
            if (NO_VAL.equals(req.getWebglRenderer()) && NO_VAL.equals(req.getVideoId())) {
                return true;
            }
            if (NO_VAL.equals(req.getVideoId()) && NO_VAL.equals(req.getCanvasId())) {
                return true;
            }

            if (NO_VAL.equals(req.getCanvasId()) && NO_VAL.equals(req.getWebglRenderer())) {
                return true;
            }

            if (NO_VAL.equals(req.getWebglRenderer())&&
                    NO_VAL.equals(req.getVideoId()) && NO_VAL.equals(req.getCanvasId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串比较
     * @param source 源字符
     * @return 匹配字符串
     */
    public static String strCompare(String source){
        if (StringUtils.isEmpty(source)) {
            return NO_VAL;
        }
        String[] split = source.split(",");
        if (null == split || split.length == 0){
            return source;
        }
        if (split.length>1){
            int val = split[0].compareTo(split[1]);
            return val<0?split[0]:split[1];
        }
        return split[0];
    }
    /**
     * 从UA里面截取unitType
     *
     * @return unitType
     */
    public static String getUnitType(String str){
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            Pattern compile = Pattern.compile(REX);
            Matcher matcher = compile.matcher(str);
            if (!matcher.find()){
                return null;
            }
            String[] results = matcher.group().split(";");
            String result = results[results.length - 1];
            String unityType = result.substring(0, result.length() - 6);
            if (StringUtils.isEmpty(unityType)){
                return null;
            }
            return unityType;
        } catch (Exception e) {
            return null;
        }
    }

    public static String joinRedisValue(String token, String idNo){
        return org.apache.commons.lang3.StringUtils.join(token,CommonConstant.JOIN_SYMBOL,idNo);
    }
    public static void main(String[] args) {

        String ss = "{\"activeTime\":\"2276535\",\"androidId\":\"c3a389\n" +
                "c58a70b8eb\",\"appName\":\"设备指纹演示Demo\",\"availableMemery\":\"3652767744\",\"availableStorage\":\"51300536320\",\"basebandVersion\":\"NX595J_Z0_CN_VNG1N_V222\",\"batteryLevel\":\"27\",\"batteryStatus\":\"充\n" +
                "电\",\"bios\":\"msm8998\",\"blueMac\":\"00:00:00:00:00:00\",\"bootTime\":\"2018-10-20 13:38:16\",\"brand\":\"nubia\",\"contacts\":\"{\\\"4007006600\\\":\\\"nubia 客服\\\"}\",\"cpu\":\"Qualcomm Technologies, Inc MSM8998\",\"\n" +
                "deviceName\":\"NX595JNX595J\",\"dnsAddress\":\"10.0.21.201\",\"fcpu\":\"1900800\",\"firstXyid\":\"11809281051177795308101\",\"fontNum\":\"0\",\"gateway\":\"10.10.199.254\",\"gprsLocation\":\"{\\\"CDMA_LAC\\\":\\\"15\\\",\\\"M\n" +
                "NC\\\":\\\"11\\\",\\\"MCC\\\":\\\"460\\\",\\\"CDMA_CELL_ID\\\":\\\"43\\\"}\",\"imei\":\"A1000059D2AB5C,A1000059D2AB5C\",\"imsi\":\"460110074953557\",\"isDebug\":\"1\",\"isHook\":\"0\",\"isRoot\":\"0\",\"isVirtualApp\":\"0\",\"localIP\":\"1\n" +
                "0.0.193.131\",\"location\":\"\",\"mac\":\"DC:F0:90:A7:4E:90\",\"merchantNo\":\"8000013190\",\"networkType\":\"WIFI\",\"operators\":\"中国电信\",\"packageName\":\"com.xinyan.android.device.xinyandevicedemo\",\"phoneN\n" +
                "um\":\"\",\"ramSize\":6015283200,\"romSize\":54672162816,\"screenRes\":\"1080*1920\",\"sdSize\":0,\"sdkVersion\":\"2.0.1\",\"simId\":\"89860317244712186306\",\"simOperator\":\"46011\",\"simulator\":\"0\",\"softList\":[31\n" +
                ",-117,8,0,0,0,0,0,0,0,-117,-114,5,0,41,-69,76,13,2,0,0,0],\"sysVer\":\"7.1.1\",\"systemLan\":\"zh\",\"timeZone\":\"GMT+08:00,Asia/Shanghai\",\"totalSize\":54672162816,\"uniqueId\":\"201809281053075609384908\n" +
                "99427574082f73e86c8187ce6386a7ea1d35c0f0\",\"unitType\":\"NX595J\",\"virtualInfo\":{\"ro.product.brand\":\"nubia\",\"ro.product.device\":\"NX595J\",\"ro.product.manufacturer\":\"nubia\",\"ro.hardware\":\"qcom\",\"\n" +
                "baseBand\":\"NX595J_Z0_CN_VNG1N_V222\",\"ro.product.model\":\"NX595J\",\"ro.build.product\":\"NX595J\",\"ro.build.fingerprint\":\"nubia/NX595J/NX595J:7.1.1/NMF26X/eng.nubia.20171114.191214:user/release-k\n" +
                "eys\"},\"wifiStatus\":\"3\",\"xyid\":\"11810201413323405907101\"}";

//        System.out.println("801010483560615973A8F09C0548615E9B5248C495".hashCode());
//        String ss = "Mozilla/5.0 (Linux; Android 4.3) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/4.0u1X";
//        if (ss.matches(REX_PATTERN)){
//            System.out.println("包含某种数据");
//        }
        List<String> strings = FileUtil.readTxtFile("D://Desktop/设备指纹数据.txt");
//
        for (int i=0;i<strings.size();i++) {

            System.out.println("DELETE FROM T_PC_DEVICE_INFO WHERE EXTEND1 = "+ strings.get(i).hashCode() +"; -- "+strings.get(i));
            try {
                File file =new File("E://设备指纹相关/数据分析/2018-10-29ignite删除数据.txt");
                Writer out =new FileWriter(file);
                String data="DELETE FROM T_PC_DEVICE_INFO WHERE EXTEND1 = "+ strings.get(i).hashCode() +";-- "+strings.get(i);
                out.write(data);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        System.out.println(joinRedisValue("1234567898","ssssssss"));

    }
}
