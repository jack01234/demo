package com.example.demo.utils;


import com.example.demo.model.bo.PcDeviceInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


/**
 * 相关参数校验
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/4
 */
@Slf4j
public class ParamCheckUtil {
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
}
