package com.example.demo.common;


import com.example.demo.model.PcDeviceInfoDO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.DeviceDalUtil;
import com.example.demo.utils.LevenshteinUtil;
import com.example.demo.utils.SqlConstant;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.demo.common.CommonConstant.PC_UA_SCORE;
import static com.example.demo.utils.DeviceGradeUtil.*;
import static com.example.demo.utils.SqlConstant.*;


/**
 * pc 端SQL处理
 *
 * @author: yingmuhuadao
 * @date: Created in 15:06 2018/8/16
 */

public class PcSearchSql {

    /**
     * 非IE8浏览器匹配SQL拼接
     *
     * @param weightMap 特征分数缓存对象
     * @return SQL
     */
    public static void buildPcMatchSql(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, Map<String, BigDecimal> weightMap,
                                         DeviceGradeResDTO res, Map<String, Object> score, Map<String, Object> lose){

        buildCommonSql(sourceDo, tarDo, weightMap, res, score, lose);

        //设备内存
        DeviceDalUtil.compareTemplate(sourceDo.getDeviceMemory(),tarDo.getDeviceMemory(),weightMap.get(DEVICE_MEMORY), res,
                score, lose, DEVICE_MEMORY);


        //视屏ID
        DeviceDalUtil.compareTemplate(sourceDo.getVideoId(),tarDo.getVideoId(),weightMap.get(VIDEO_ID), res,
                score, lose, VIDEO_ID);


        //浏览器设备指纹
        DeviceDalUtil.compareTemplate(sourceDo.getCanvasId(),tarDo.getCanvasId(),weightMap.get(CANVAS_ID), res,
                score, lose, CANVAS_ID);


        //音频ID
        DeviceDalUtil.compareTemplate(sourceDo.getAudioId(),tarDo.getAudioId(),weightMap.get(AUDIO_ID), res,
                score, lose, AUDIO_ID);


        //一种屏蔽广告的插件
        DeviceDalUtil.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
                score, lose, AD_BLOCK);
    }

    /**
     * 非 IE8 移动端浏览器匹配SQL
     *
     * @param weightMap 特征分数
     * @return SQL
     */
    public static void buildClientMatchSql(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, Map<String, BigDecimal> weightMap,
                                             DeviceGradeResDTO res, Map<String, Object> score, Map<String, Object> lose){

        buildCommonSql(sourceDo, tarDo, weightMap, res, score, lose);


        //浏览器支持的语言

        DeviceDalUtil.compareTemplate(sourceDo.getLanguage(),tarDo.getLanguage(),weightMap.get(LANGUAGE), res, score,
                    lose, LANGUAGE);

        //设备内存
        DeviceDalUtil.compareTemplate(sourceDo.getDeviceMemory(),tarDo.getDeviceMemory(),weightMap.get(DEVICE_MEMORY), res,
                score, lose, DEVICE_MEMORY);


        //视屏ID
        DeviceDalUtil.compareTemplate(sourceDo.getVideoId(),tarDo.getVideoId(),weightMap.get(VIDEO_ID), res,
                score, lose, VIDEO_ID);

        //浏览器设备指纹
        DeviceDalUtil.compareTemplate(sourceDo.getCanvasId(),tarDo.getCanvasId(),weightMap.get(CANVAS_ID), res,
                score, lose, CANVAS_ID);

        //一种屏蔽广告的插件
        DeviceDalUtil.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
                score, lose, AD_BLOCK);
    }


    /**
     * 移动端IE8浏览器匹配SQL拼装
     *
     *
     * @param weightMap 特征分数
     * @return SQL
     */

    public static void buildIe8CliOrPcMatchSql(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, Map<String, BigDecimal> weightMap,
                                                 DeviceGradeResDTO res, Map<String, Object> score, Map<String, Object> lose){


        buildCommonSql(sourceDo, tarDo, weightMap, res, score, lose);

        //设备内存(取不到加分)
        if (sourceDo.getDeviceMemory().equals(tarDo.getDeviceMemory()) || NO_VAL.equals(sourceDo.getDeviceMemory())) {
            res.setScore(res.getScore().add(weightMap.get(DEVICE_MEMORY)));
            score.put(DEVICE_MEMORY,weightMap.get(DEVICE_MEMORY));
        }else {
            lose.put(DEVICE_MEMORY, weightMap.get(DEVICE_MEMORY));
        }


        //一种屏蔽广告的插件
        //一种屏蔽广告的插件
        DeviceDalUtil.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
                score, lose, AD_BLOCK);

    }

    /**
     *
     * 判断一级特征是否相等
     *
     */
    public static boolean judgeStairFeature(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo){

        return sourceDo.getCpuClass().equals(tarDo.getCpuClass()) && sourceDo.getColorDepth().equals(tarDo.getColorDepth()) &&
                sourceDo.getHardwareConcurrency().equals(tarDo.getHardwareConcurrency()) &&
                sourceDo.getTouchSupport().equals(tarDo.getTouchSupport()) && sourceDo.getPlatform().equals(tarDo.getPlatform()) &&
                sourceDo.getWebglVendor().equals(tarDo.getWebglVendor()) && sourceDo.getPixelRatio().equals(tarDo.getPixelRatio());

    }


    /**
     * 公共特征匹配SQL拼接
     *
     * @param weightMap 特征分数
     * @return SQL
     *
     */

    private static void buildCommonSql(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, Map<String, BigDecimal> weightMap,
                                       DeviceGradeResDTO res, Map<String, Object> score, Map<String, Object> lose){

        //浏览器
        if (NOT_FLAG.equals(sourceDo.getPretendResolution())) {
            DeviceDalUtil.compareTemplate(sourceDo.getResolution(),tarDo.getResolution(),weightMap.get(RESOLUTION), res,
                    score, lose, RESOLUTION);
        }

        //浏览器支持的语言
        if (NOT_FLAG.equals(sourceDo.getHasLiedLanguages())) {

            DeviceDalUtil.compareTemplate(sourceDo.getLanguage(),tarDo.getLanguage(),weightMap.get(LANGUAGE), res, score,
                    lose, LANGUAGE);

        }

        //用户代理
        if (NOT_FLAG.equals(sourceDo.getPretendBrowser()) && NOT_FLAG.equals(sourceDo.getPretendSystem())) {

            DeviceDalUtil.compareTemplate(sourceDo.getUserAgent(),tarDo.getUserAgent(),weightMap.get(USER_AGENT), res,
                    score, lose, USER_AGENT);

        }

        //IP和evercookie
        if (sourceDo.getEverCookie().equals(tarDo.getEverCookie())){
            res.setScore(res.getScore().add(weightMap.get(EVER_COOKIE)));
            score.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
        } else if (sourceDo.getExtend1().equals(tarDo.getExtend1())) {
            res.setScore(res.getScore().add(weightMap.get(EXTEND1)));
            score.put("ip",weightMap.get(EXTEND1));
        } else {
          lose.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
          lose.put("ip",weightMap.get(EXTEND1));
        }



        //浏览器session存储
        DeviceDalUtil.compareTemplate(sourceDo.getSessionStorage(),tarDo.getSessionStorage(),weightMap.get(SESSION_STORAGE), res,
                score, lose, SESSION_STORAGE);


        //浏览器本地存储
        DeviceDalUtil.compareTemplate(sourceDo.getLocalStorage(),tarDo.getLocalStorage(),weightMap.get(LOCAL_STORAGE), res,
                score, lose, LOCAL_STORAGE);


        //浏览器中对数据库操作的方法
        DeviceDalUtil.compareTemplate(sourceDo.getOpenDatabase(),tarDo.getOpenDatabase(),weightMap.get(OPEN_DATABASE), res,
                score, lose, OPEN_DATABASE);


        //jsFont
        DeviceDalUtil.compareTemplate(sourceDo.getJsFonts(),tarDo.getJsFonts(),weightMap.get(JS_FONTS), res,
                score, lose, JS_FONTS);

        //存储在客户端本地的 NoSQL 数据库
        DeviceDalUtil.compareTemplate(sourceDo.getHashIndexedDB(),tarDo.getHashIndexedDB(),weightMap.get(HASHINDEX_DB), res,
                score, lose, HASHINDEX_DB);

        //浏览器插件
        DeviceDalUtil.compareTemplate(sourceDo.getPlugins(),tarDo.getPlugins(),weightMap.get(PLUGINS), res,
                score, lose, PLUGINS);

        //时区
        DeviceDalUtil.compareTemplate(sourceDo.getTimeZone(),tarDo.getTimeZone(),weightMap.get(TIME_ZONE), res,
                score, lose, TIME_ZONE);

        //WebGL的渲染器
        DeviceDalUtil.compareTemplate(sourceDo.getWebglRenderer(),tarDo.getWebglRenderer(),weightMap.get(WEBGL_RENDERER), res,
                score, lose, WEBGL_RENDERER);

    }


    /**
     * 公共特征匹配SQL拼接
     *
     * @param weightMap 特征分数
     * @return SQL
     *
     */

    private static void buildClientCommonSql(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, Map<String, BigDecimal> weightMap,
                                       DeviceGradeResDTO res, Map<String, Object> score, Map<String, Object> lose){

        //浏览器
        if (NOT_FLAG.equals(sourceDo.getPretendResolution())) {
            DeviceDalUtil.compareTemplate(sourceDo.getResolution(),tarDo.getResolution(),weightMap.get(RESOLUTION), res,
                    score, lose, RESOLUTION);
        }

        //用户代理
        if (NOT_FLAG.equals(sourceDo.getPretendBrowser()) && NOT_FLAG.equals(sourceDo.getPretendSystem())) {

            DeviceDalUtil.compareTemplate(sourceDo.getUserAgent(),tarDo.getUserAgent(),weightMap.get(USER_AGENT), res,
                    score, lose, USER_AGENT);

        }

        //IP和evercookie
        if (sourceDo.getEverCookie().equals(tarDo.getEverCookie())){
            res.setScore(res.getScore().add(weightMap.get(EVER_COOKIE)));
            score.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
        } else if (sourceDo.getExtend1().equals(tarDo.getExtend1())) {
            res.setScore(res.getScore().add(weightMap.get(EXTEND1)));
            score.put("ip",weightMap.get(EXTEND1));
        } else {
            lose.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
            lose.put("ip",weightMap.get(EXTEND1));
        }



        //浏览器session存储
        DeviceDalUtil.compareTemplate(sourceDo.getSessionStorage(),tarDo.getSessionStorage(),weightMap.get(SESSION_STORAGE), res,
                score, lose, SESSION_STORAGE);


        //浏览器本地存储
        DeviceDalUtil.compareTemplate(sourceDo.getLocalStorage(),tarDo.getLocalStorage(),weightMap.get(LOCAL_STORAGE), res,
                score, lose, LOCAL_STORAGE);


        //浏览器中对数据库操作的方法
        DeviceDalUtil.compareTemplate(sourceDo.getOpenDatabase(),tarDo.getOpenDatabase(),weightMap.get(OPEN_DATABASE), res,
                score, lose, OPEN_DATABASE);


        //jsFont
        DeviceDalUtil.compareTemplate(sourceDo.getJsFonts(),tarDo.getJsFonts(),weightMap.get(JS_FONTS), res,
                score, lose, JS_FONTS);

        //存储在客户端本地的 NoSQL 数据库
        DeviceDalUtil.compareTemplate(sourceDo.getHashIndexedDB(),tarDo.getHashIndexedDB(),weightMap.get(HASHINDEX_DB), res,
                score, lose, HASHINDEX_DB);

        //浏览器插件
        DeviceDalUtil.compareTemplate(sourceDo.getPlugins(),tarDo.getPlugins(),weightMap.get(PLUGINS), res,
                score, lose, PLUGINS);

        //时区
        DeviceDalUtil.compareTemplate(sourceDo.getTimeZone(),tarDo.getTimeZone(),weightMap.get(TIME_ZONE), res,
                score, lose, TIME_ZONE);

        //WebGL的渲染器
        DeviceDalUtil.compareTemplate(sourceDo.getWebglRenderer(),tarDo.getWebglRenderer(),weightMap.get(WEBGL_RENDERER), res,
                score, lose, WEBGL_RENDERER);

    }
    /**
     *userAgent 的相似度计分
     *
     */
    public static void numUaScore(PcDeviceInfoDO sourceDo,String userAgent,DeviceGradeResDTO res,Map<String, Object> score,
                                  Map<String, Object> lose){

        //判断UA特征字段是否已经加分
        if (!(NOT_FLAG.equals(sourceDo.getPretendBrowser()) && NOT_FLAG.equals(sourceDo.getPretendSystem()))) {
            return;
        }

        if (sourceDo.getUserAgent().equals(userAgent)) {
            return;
        }

        //进行相似度计算
        BigDecimal similarityRatio = LevenshteinUtil.
                getSimilarityRatio(sourceDo.getUserAgent(), userAgent);
        res.setSimilarity(similarityRatio);
        //如果相似度大于0.978进行UA字段的加分计算
        if (similarityRatio.compareTo(PC_UA_SCORE)==BigDecimal.ONE.intValue()) {
            switch (sourceDo.getFlag()) {
                case 1:
                    res.setScore(res.getScore().add(pcMap.get(SqlConstant.USER_AGENT)));
                    score.put(USER_AGENT,pcMap.get(SqlConstant.USER_AGENT));
                    break;
                case 2:
                    res.setScore(res.getScore().add(clientMap.get(SqlConstant.USER_AGENT)));
                    score.put(USER_AGENT,clientMap.get(SqlConstant.USER_AGENT));
                    break;
                case 3:
                    res.setScore(res.getScore().add(iePcMap.get(SqlConstant.USER_AGENT)));
                    score.put(USER_AGENT,iePcMap.get(SqlConstant.USER_AGENT));
                    break;
                case 4:
                    res.setScore(res.getScore().add(ieClientMap.get(SqlConstant.USER_AGENT)));
                    score.put(USER_AGENT,ieClientMap.get(SqlConstant.USER_AGENT));
                    break;
                default:
                    System.out.println("未知的标识类型:"+sourceDo.getFlag());
                    break;
            }
        }else {
            switch (sourceDo.getFlag()) {
                case 1:
                    lose.put(USER_AGENT,pcMap.get(SqlConstant.USER_AGENT));
                    break;
                case 2:
                    lose.put(USER_AGENT,clientMap.get(SqlConstant.USER_AGENT));
                    break;
                case 3:
                    lose.put(USER_AGENT,iePcMap.get(SqlConstant.USER_AGENT));
                    break;
                case 4:
                    lose.put(USER_AGENT,ieClientMap.get(SqlConstant.USER_AGENT));
                    break;
                default:
                    System.out.println("未知的标识类型:"+sourceDo.getFlag());
                    break;
            }
        }

    }

}
