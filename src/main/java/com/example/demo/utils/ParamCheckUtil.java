package com.example.demo.utils;


import com.example.demo.model.bo.PcDeviceInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

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
    public static void main(String[] args) {
        List<String> strings = FileUtil.readTxtFile("D://Desktop/设备指纹数据.txt");
        for (int i=0;i<strings.size();i++) {
            long startTime = System.currentTimeMillis();

           String result = getUnitType(strings.get(i));


           System.out.println("第"+ i +"次, 结果:"+ result);


            System.out.println("耗时:"+String.valueOf(System.currentTimeMillis()-startTime));
        }
    }
}
