package com.example.demo.utils;

import com.example.demo.model.bo.AndroidDeviceInfoBO;
import com.example.demo.model.bo.PcDeviceInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 信息解析工具类
 *
 * @author: yingmuhuadao
 * @date: Created in 15:37 2018/8/17
 */
@Slf4j
public class MessageParseUtil {

    /**
     * 将请求message存放到map
     *
     * @param message 请求消息
     *
     * @return 转换结果
     */
    public static Map<String,String> parseMap(String message){

        Map<String, String> map = new HashMap<>(1);

        if (StringUtils.isBlank(message)) {
            return map;
        }

        String[] messageSet = message.split(",");

        for (int i = 0; i < messageSet.length; i++) {

            String[] split = messageSet[i].trim().split("=");

            map.put(split[0].trim(),split[1].trim());
        }

        return map;
    }


    /**
     * map 转对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass){

        Object obj = null;
        try {
            if (map == null){
                return null;
            }

            obj = beanClass.newInstance();

            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 消息处理
     *
     * @param message 消息处理
     * @return 处理结果
     */

    public static PcDeviceInfoBO messageHandle(String message){
        PcDeviceInfoBO bo = new PcDeviceInfoBO();

        bo.setIp(strSub("ip",SqlConstant.USER_AGENT, message));

        bo.setUserAgent(strSub(SqlConstant.USER_AGENT, SqlConstant.COLOR_DEPTH, message));

        bo.setColorDepth(strSub(SqlConstant.COLOR_DEPTH, SqlConstant.WEBGL_VENDOR, message));

        bo.setWebglVendor(strSub(SqlConstant.WEBGL_VENDOR, SqlConstant.WEBGL_RENDERER, message));

        bo.setWebglRenderer(strSub(SqlConstant.WEBGL_RENDERER, SqlConstant.SESSION_STORAGE, message));

        bo.setSessionStorage(strSub(SqlConstant.SESSION_STORAGE, SqlConstant.LOCAL_STORAGE, message));

        bo.setLocalStorage(strSub(SqlConstant.LOCAL_STORAGE, SqlConstant.HASHINDEX_DB, message));

        bo.setHashIndexedDB(strSub(SqlConstant.HASHINDEX_DB, SqlConstant.CANVAS_ID, message));

        bo.setCanvasId(strSub(SqlConstant.CANVAS_ID, SqlConstant.CPU_CLASS, message));

        bo.setCpuClass(strSub(SqlConstant.CPU_CLASS, SqlConstant.PLATFORM, message));

        bo.setPlatform(strSub(SqlConstant.PLATFORM, SqlConstant.TIME_ZONE, message));

        bo.setTimeZone(strSub(SqlConstant.TIME_ZONE, SqlConstant.PLUGINS, message));

        bo.setPlugins(strSub(SqlConstant.PLUGINS, SqlConstant.AD_BLOCK, message));

        bo.setAdBlock(strSub(SqlConstant.AD_BLOCK, "pretendSystem", message));

        bo.setPretendSystem(strSub("pretendSystem", "pretendResolution", message));

        bo.setPretendResolution(strSub("pretendResolution", "pretendBrowser", message));

        bo.setPretendBrowser(strSub("pretendBrowser", SqlConstant.CAMERA_ID, message));

        bo.setCameraId(strSub(SqlConstant.CAMERA_ID, SqlConstant.AUDIO_ID, message));

        bo.setAudioId(strSub(SqlConstant.AUDIO_ID, SqlConstant.LANGUAGE, message));

        bo.setLanguage(strSub(SqlConstant.LANGUAGE, SqlConstant.DEVICE_MEMORY, message));

        bo.setDeviceMemory(strSub(SqlConstant.DEVICE_MEMORY, SqlConstant.HARDWARE_CONCURRENCY, message));

        bo.setHardwareConcurrency(strSub(SqlConstant.HARDWARE_CONCURRENCY, SqlConstant.RESOLUTION, message));

        bo.setResolution(strSub(SqlConstant.RESOLUTION, SqlConstant.OPEN_DATABASE, message));

        bo.setOpenDatabase(strSub(SqlConstant.OPEN_DATABASE, "hasLiedLanguages", message));

        bo.setHasLiedLanguages(strSub("hasLiedLanguages", SqlConstant.TOUCH_SUPPORT, message));

        bo.setTouchSupport(strSub(SqlConstant.TOUCH_SUPPORT, SqlConstant.JS_FONTS, message));

        bo.setJsFonts(strSub(SqlConstant.JS_FONTS, SqlConstant.MICROPHONE, message));

        bo.setMicrophone(strSub(SqlConstant.MICROPHONE, SqlConstant.PIXEL_RATIO, message));

        bo.setPixelRatio(strSub(SqlConstant.PIXEL_RATIO, SqlConstant.EVER_COOKIE, message));

        bo.setEverCookie(strSub(SqlConstant.EVER_COOKIE, SqlConstant.VIDEO_ID, message));

        bo.setVideoId(strSub(SqlConstant.VIDEO_ID, "deviceSys", message));

        bo.setDeviceSys(strSub("deviceSys","transLogId", message));
        return bo;
    }

    /**
     * 安卓请求信息处理
     *
     *
     * @param message 消息内容
     * @return 处理结果
     */

    public static AndroidDeviceInfoBO androidMessageHandle(String message){
        AndroidDeviceInfoBO bo = new AndroidDeviceInfoBO();

        bo.setDeviceName(strSub(SqlConstant.DEVICE_NAME,SqlConstant.CPU, message));

        bo.setCpu(strSub(SqlConstant.CPU, SqlConstant.UNIT_TYPE, message));

        bo.setUnitType(strSub(SqlConstant.UNIT_TYPE, SqlConstant.SYS_VER, message));

        bo.setSysVer(strSub(SqlConstant.SYS_VER, SqlConstant.IMEI, message));

        bo.setImei(strSub(SqlConstant.IMEI, SqlConstant.SIMULATOR, message));

        bo.setSimulator(strSub(SqlConstant.SIMULATOR, SqlConstant.TIME_ZONE, message));

        bo.setTimeZone(strSub(SqlConstant.TIME_ZONE, SqlConstant.SYSTEM_LAN, message));

        bo.setSystemLan(strSub(SqlConstant.SYSTEM_LAN, SqlConstant.IS_ROOT, message));

        bo.setIsRoot(strSub(SqlConstant.IS_ROOT,SqlConstant.FONTNUM, message));

        bo.setFontNum(strSub(SqlConstant.FONTNUM, SqlConstant.SCREENRES, message));

        bo.setScreenRes(strSub(SqlConstant.SCREENRES, SqlConstant.BIOS, message));

        bo.setBios(strSub(SqlConstant.BIOS,SqlConstant.IMSI, message));

        bo.setImsi(strSub(SqlConstant.IMSI, SqlConstant.PHONENUM, message));

        bo.setPhoneNum(strSub(SqlConstant.PHONENUM, SqlConstant.MAC, message));

        bo.setMac(strSub(SqlConstant.MAC, SqlConstant.FCPU, message));

        bo.setFcpu(strSub(SqlConstant.FCPU, SqlConstant.TOTAL_SIZE, message));

        bo.setTotalSize(strSub(SqlConstant.TOTAL_SIZE, SqlConstant.ANDROID_ID, message));

        bo.setAndroidId(strSub(SqlConstant.ANDROID_ID, "isVirtualApp", message));

        return bo;
    }

    /**
     * 字段截取
     *
     */
    private static String strSub(String firstFeild, String lastFeild, String message){
        return message.substring(message.indexOf(firstFeild.concat("="))+firstFeild.length()+1,message.indexOf(lastFeild.concat("="))-2);
    }
}
