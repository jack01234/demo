package com.example.demo.common;


import com.example.demo.model.PcDeviceInfoDO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.LevenshteinUtil;
import com.example.demo.utils.SqlConstant;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.demo.common.CommonConstant.PC_ORI_UA_SCORE;
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

        //视屏ID
        PcSqlTemplate.novalScoreTemplate(sourceDo.getVideoId(),tarDo.getVideoId(),weightMap.get(VIDEO_ID), res,
                score, lose, VIDEO_ID);


        //浏览器设备指纹
        PcSqlTemplate.compareTemplate(sourceDo.getCanvasId(),tarDo.getCanvasId(),weightMap.get(CANVAS_ID), res,
                score, lose, CANVAS_ID);


        //音频ID
        PcSqlTemplate.novalScoreTemplate(sourceDo.getAudioId(),tarDo.getAudioId(),weightMap.get(AUDIO_ID), res,
                score, lose, AUDIO_ID);


        //一种屏蔽广告的插件
        PcSqlTemplate.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
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

        //视屏ID
        PcSqlTemplate.novalScoreTemplate(sourceDo.getVideoId(),tarDo.getVideoId(),weightMap.get(VIDEO_ID), res,
                score, lose, VIDEO_ID);

        //浏览器设备指纹
        PcSqlTemplate.compareTemplate(sourceDo.getCanvasId(),tarDo.getCanvasId(),weightMap.get(CANVAS_ID), res,
                score, lose, CANVAS_ID);

        //一种屏蔽广告的插件
        PcSqlTemplate.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
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

        //一种屏蔽广告的插件
        PcSqlTemplate.compareTemplate(sourceDo.getAdBlock(),tarDo.getAdBlock(),weightMap.get(AD_BLOCK), res,
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

        boolean sourceFlag = NOT_FLAG.equals(sourceDo.getPretendBrowser()) &&
                NOT_FLAG.equals(sourceDo.getPretendSystem());

        boolean tarFlag = NOT_FLAG.equals(tarDo.getPretendBrowser()) &&
                NOT_FLAG.equals(tarDo.getPretendSystem());


        //浏览器
        if (NOT_FLAG.equals(sourceDo.getPretendResolution()) && sourceFlag) {
            PcSqlTemplate.resTemplate(sourceDo.getResolution(),tarDo.getResolution(),weightMap.get(RESOLUTION), res,
                    score, lose, RESOLUTION, !tarFlag || !NOT_FLAG.equals(tarDo.getPretendResolution()));
        } else {

            PcSqlTemplate.scoreTemplate(weightMap.get(RESOLUTION), res, score,RESOLUTION);
        }

        //浏览器支持的语言
        if (NOT_FLAG.equals(sourceDo.getHasLiedLanguages())) {

            PcSqlTemplate.resTemplate(sourceDo.getLanguage(),tarDo.getLanguage(),weightMap.get(LANGUAGE), res, score,
                    lose, LANGUAGE, !NOT_FLAG.equals(tarDo.getHasLiedLanguages()));

        } else {

            PcSqlTemplate.scoreTemplate(weightMap.get(LANGUAGE), res, score,LANGUAGE);
        }

        //用户代理
        if (sourceFlag) {

            PcSqlTemplate.resTemplate(sourceDo.getUserAgent(),tarDo.getUserAgent(),weightMap.get(USER_AGENT), res,
                    score, lose, USER_AGENT, !tarFlag);

        }else {

            PcSqlTemplate.scoreTemplate(weightMap.get(USER_AGENT), res, score,USER_AGENT);
        }

        if (sourceFlag) {

            //IP和evercookie
            if (sourceDo.getEverCookie().equals(tarDo.getEverCookie())){
                res.setScore(res.getScore().add(weightMap.get(EVER_COOKIE)));
                score.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
            } else if (sourceDo.getExtend1().equals(tarDo.getExtend1()) && tarFlag) {
                res.setScore(res.getScore().add(weightMap.get(EXTEND1)));
                score.put("ip",weightMap.get(EXTEND1));
            } else {
                lose.put(EVER_COOKIE,weightMap.get(EVER_COOKIE));
                lose.put("ip",weightMap.get(EXTEND1));
            }
        } else {

            PcSqlTemplate.compareTemplate(sourceDo.getEverCookie(), tarDo.getEverCookie(), weightMap.get(EVER_COOKIE),
                    res, score,lose,EVER_COOKIE);
        }




        //浏览器session存储
        PcSqlTemplate.compareTemplate(sourceDo.getSessionStorage(),tarDo.getSessionStorage(),weightMap.get(SESSION_STORAGE), res,
                score, lose, SESSION_STORAGE);


        //浏览器本地存储
        PcSqlTemplate.compareTemplate(sourceDo.getLocalStorage(),tarDo.getLocalStorage(),weightMap.get(LOCAL_STORAGE), res,
                score, lose, LOCAL_STORAGE);


        //浏览器中对数据库操作的方法
        PcSqlTemplate.compareTemplate(sourceDo.getOpenDatabase(),tarDo.getOpenDatabase(),weightMap.get(OPEN_DATABASE), res,
                score, lose, OPEN_DATABASE);


        //jsFont
        if (sourceFlag) {

            PcSqlTemplate.resTemplate(sourceDo.getJsFonts(),tarDo.getJsFonts(),weightMap.get(JS_FONTS), res,
                    score, lose, JS_FONTS, !tarFlag);
        } else {

            PcSqlTemplate.scoreTemplate(weightMap.get(JS_FONTS), res, score,JS_FONTS);
        }


        //存储在客户端本地的 NoSQL 数据库
        PcSqlTemplate.compareTemplate(sourceDo.getHashIndexedDB(),tarDo.getHashIndexedDB(),weightMap.get(HASHINDEX_DB), res,
                score, lose, HASHINDEX_DB);

        //浏览器插件
        if (sourceFlag){

            PcSqlTemplate.resTemplate(sourceDo.getPlugins(),tarDo.getPlugins(),weightMap.get(PLUGINS), res,
                    score, lose, PLUGINS, !tarFlag);
        } else {

            PcSqlTemplate.scoreTemplate(weightMap.get(PLUGINS), res, score,PLUGINS);
        }


        //时区
        PcSqlTemplate.compareTemplate(sourceDo.getTimeZone(),tarDo.getTimeZone(),weightMap.get(TIME_ZONE), res,
                score, lose, TIME_ZONE);

        //WebGL的渲染器
        PcSqlTemplate.compareTemplate(sourceDo.getWebglRenderer(),tarDo.getWebglRenderer(),weightMap.get(WEBGL_RENDERER), res,
                score, lose, WEBGL_RENDERER);

        //设备内存
        PcSqlTemplate.novalScoreTemplate(sourceDo.getDeviceMemory(),tarDo.getDeviceMemory(),weightMap.get(DEVICE_MEMORY),
                res, score, lose, DEVICE_MEMORY);

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
            PcSqlTemplate.compareTemplate(sourceDo.getResolution(),tarDo.getResolution(),weightMap.get(RESOLUTION), res,
                    score, lose, RESOLUTION);
        }

        //用户代理
        if (NOT_FLAG.equals(sourceDo.getPretendBrowser()) && NOT_FLAG.equals(sourceDo.getPretendSystem())) {

            PcSqlTemplate.compareTemplate(sourceDo.getUserAgent(),tarDo.getUserAgent(),weightMap.get(USER_AGENT), res,
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
        PcSqlTemplate.compareTemplate(sourceDo.getSessionStorage(),tarDo.getSessionStorage(),weightMap.get(SESSION_STORAGE), res,
                score, lose, SESSION_STORAGE);


        //浏览器本地存储
        PcSqlTemplate.compareTemplate(sourceDo.getLocalStorage(),tarDo.getLocalStorage(),weightMap.get(LOCAL_STORAGE), res,
                score, lose, LOCAL_STORAGE);


        //浏览器中对数据库操作的方法
        PcSqlTemplate.compareTemplate(sourceDo.getOpenDatabase(),tarDo.getOpenDatabase(),weightMap.get(OPEN_DATABASE), res,
                score, lose, OPEN_DATABASE);


        //jsFont
        PcSqlTemplate.compareTemplate(sourceDo.getJsFonts(),tarDo.getJsFonts(),weightMap.get(JS_FONTS), res,
                score, lose, JS_FONTS);

        //存储在客户端本地的 NoSQL 数据库
        PcSqlTemplate.compareTemplate(sourceDo.getHashIndexedDB(),tarDo.getHashIndexedDB(),weightMap.get(HASHINDEX_DB), res,
                score, lose, HASHINDEX_DB);

        //浏览器插件
        PcSqlTemplate.compareTemplate(sourceDo.getPlugins(),tarDo.getPlugins(),weightMap.get(PLUGINS), res,
                score, lose, PLUGINS);

        //时区
        PcSqlTemplate.compareTemplate(sourceDo.getTimeZone(),tarDo.getTimeZone(),weightMap.get(TIME_ZONE), res,
                score, lose, TIME_ZONE);

        //WebGL的渲染器
        PcSqlTemplate.compareTemplate(sourceDo.getWebglRenderer(),tarDo.getWebglRenderer(),weightMap.get(WEBGL_RENDERER), res,
                score, lose, WEBGL_RENDERER);

    }
    /**
     *userAgent 的相似度计分
     *
     */
    public static void numUaScore(PcDeviceInfoDO sourceDo,PcDeviceInfoDO tarDO,DeviceGradeResDTO res,Map<String, Object> score,
                                  Map<String, Object> lose){

        //判断UA特征字段是否已经加分
        if (!(NOT_FLAG.equals(sourceDo.getPretendBrowser()) && NOT_FLAG.equals(sourceDo.getPretendSystem()))) {
            return;
        }

        if (sourceDo.getUserAgent().equals(tarDO.getUserAgent())) {
            return;
        }

        //进行相似度计算
        BigDecimal similarityRatio = LevenshteinUtil.
                getSimilarityRatio(sourceDo.getUserAgent(), tarDO.getUserAgent());
        res.setSimilarity(similarityRatio);

        //判断相似度计算阈值(evercookie不同,IP一样阈值为0.978,否则为0.88)
        BigDecimal similarityScore;

        if (CommonUtil.judgeSimilarity(sourceDo, tarDO)) {
            similarityScore = PC_ORI_UA_SCORE;
        } else {
            similarityScore = PC_UA_SCORE;
        }

        //如果相似度大于0.978进行UA字段的加分计算
        if (similarityRatio.compareTo(similarityScore)==BigDecimal.ONE.intValue()) {
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
