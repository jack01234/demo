package com.example.demo.utils;

import com.example.demo.common.AndroidSearchSql;
import com.example.demo.common.PcSearchSql;
import com.example.demo.model.AndroidDeviceInfoDO;
import com.example.demo.model.PcDeviceInfoDO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.example.demo.common.CommonConstant.DEVICE_SCORE;
import static com.example.demo.common.CommonConstant.PC_DEVICE_SCORE;
import static com.example.demo.utils.SqlConstant.*;

/**
 * 设备算分工具类
 *
 * @author: yingmuhuadao
 * @date: Created in 11:38 2018/8/18
 */
@Slf4j
public class DeviceGradeUtil {

    /**
     * 特征分数初始化缓存对象
     */
    public static Map<String, BigDecimal> pcMap = new HashMap<>();
    public static Map<String, BigDecimal> clientMap = new HashMap<>();
    public static Map<String, BigDecimal> iePcMap = new HashMap<>();
    public static Map<String, BigDecimal> ieClientMap = new HashMap<>();

    static {
        log.info("初始化特征分数开始");

        /**非IE8浏览器JS特征分数5.377**/

        pcMap.put(USER_AGENT, BigDecimal.valueOf(0.6));
        pcMap.put(EVER_COOKIE,BigDecimal.valueOf(1.843));
        pcMap.put(EXTEND1,BigDecimal.valueOf(1.843));
        pcMap.put(LANGUAGE, BigDecimal.valueOf(0.344));
        pcMap.put(DEVICE_MEMORY,BigDecimal.valueOf(0.37));
        pcMap.put(RESOLUTION,BigDecimal.valueOf(0.25));
        pcMap.put(TIME_ZONE,BigDecimal.valueOf(0.198));
        pcMap.put(SESSION_STORAGE,BigDecimal.valueOf(0.024));
        pcMap.put(LOCAL_STORAGE,BigDecimal.valueOf(0.024));
        pcMap.put(OPEN_DATABASE,BigDecimal.valueOf(0.024));
        pcMap.put(HASHINDEX_DB,BigDecimal.valueOf(0.024));
        pcMap.put(PLUGINS,BigDecimal.valueOf(0.2));
        pcMap.put(CANVAS_ID,BigDecimal.valueOf(0.3));
        pcMap.put(WEBGL_RENDERER,BigDecimal.valueOf(0.2));
        pcMap.put(AD_BLOCK,BigDecimal.valueOf(0.056));
        pcMap.put(JS_FONTS,BigDecimal.valueOf(0.42));
        pcMap.put(AUDIO_ID,BigDecimal.valueOf(0.2));
        pcMap.put(VIDEO_ID,BigDecimal.valueOf(0.3));

        /**IE8浏览器JS特征分数4.859**/

        iePcMap.put(USER_AGENT, BigDecimal.valueOf(0.5));
        iePcMap.put(EVER_COOKIE,BigDecimal.valueOf(2.223));
        iePcMap.put(EXTEND1,BigDecimal.valueOf(2.223));
        iePcMap.put(LANGUAGE, BigDecimal.valueOf(0.344));
        iePcMap.put(DEVICE_MEMORY,BigDecimal.valueOf(0.37));
        iePcMap.put(RESOLUTION,BigDecimal.valueOf(0.25));
        iePcMap.put(TIME_ZONE,BigDecimal.valueOf(0.198));
        iePcMap.put(SESSION_STORAGE,BigDecimal.valueOf(0.024));
        iePcMap.put(LOCAL_STORAGE,BigDecimal.valueOf(0.024));
        iePcMap.put(OPEN_DATABASE,BigDecimal.valueOf(0.024));
        iePcMap.put(HASHINDEX_DB,BigDecimal.valueOf(0.024));
        iePcMap.put(PLUGINS,BigDecimal.valueOf(0.2));
        iePcMap.put(WEBGL_RENDERER,BigDecimal.valueOf(0.202));
        iePcMap.put(AD_BLOCK,BigDecimal.valueOf(0.056));
        iePcMap.put(JS_FONTS,BigDecimal.valueOf(0.42));


        /**非IE8浏览器H5特征分数4.018**/

        clientMap.put(USER_AGENT,BigDecimal.valueOf(0.5));
        clientMap.put(EVER_COOKIE,BigDecimal.valueOf(1.517));
        clientMap.put(EXTEND1,BigDecimal.valueOf(1.517));
        clientMap.put(LANGUAGE,BigDecimal.valueOf(0.32));
        clientMap.put(DEVICE_MEMORY,BigDecimal.valueOf(0.35));
        clientMap.put(RESOLUTION,BigDecimal.valueOf(0.2));
        clientMap.put(TIME_ZONE,BigDecimal.valueOf(0.245));
        clientMap.put(SESSION_STORAGE,BigDecimal.valueOf(0.037));
        clientMap.put(LOCAL_STORAGE,BigDecimal.valueOf(0.037));
        clientMap.put(OPEN_DATABASE,BigDecimal.valueOf(0.037));
        clientMap.put(HASHINDEX_DB,BigDecimal.valueOf(0.037));
        clientMap.put(PLUGINS,BigDecimal.valueOf(0.081));
        clientMap.put(CANVAS_ID,BigDecimal.valueOf(0.25));
        clientMap.put(WEBGL_RENDERER,BigDecimal.valueOf(0.145));
        clientMap.put(AD_BLOCK,BigDecimal.valueOf(0.029));
        clientMap.put(JS_FONTS,BigDecimal.valueOf(0.033));
        clientMap.put(VIDEO_ID,BigDecimal.valueOf(0.2));


        /**IE8浏览器H5特征分数7.582**/

        ieClientMap.put(USER_AGENT,BigDecimal.valueOf(0.841));
        ieClientMap.put(EVER_COOKIE,BigDecimal.valueOf(4.9));
        ieClientMap.put(EXTEND1,BigDecimal.valueOf(4.9));
        ieClientMap.put(LANGUAGE,BigDecimal.valueOf(0.424));
        ieClientMap.put(DEVICE_MEMORY,BigDecimal.valueOf(0.35));
        ieClientMap.put(RESOLUTION,BigDecimal.valueOf(0.366));
        ieClientMap.put(TIME_ZONE,BigDecimal.valueOf(0.245));
        ieClientMap.put(SESSION_STORAGE,BigDecimal.valueOf(0.037));
        ieClientMap.put(LOCAL_STORAGE,BigDecimal.valueOf(0.037));
        ieClientMap.put(OPEN_DATABASE,BigDecimal.valueOf(0.037));
        ieClientMap.put(HASHINDEX_DB,BigDecimal.valueOf(0.037));
        ieClientMap.put(PLUGINS,BigDecimal.valueOf(0.081));
        ieClientMap.put(WEBGL_RENDERER,BigDecimal.valueOf(0.165));
        ieClientMap.put(AD_BLOCK,BigDecimal.valueOf(0.029));
        ieClientMap.put(JS_FONTS,BigDecimal.valueOf(0.033));

        log.info("初始化特征分数结束");
    }

    /**
     *
     * pc 请求参数匹配算分
     *
     */

    public static void pcGrade(PcDeviceInfoDO sourceDo, PcDeviceInfoDO tarDo, DeviceGradeResDTO res){
        Map<String, Object> score = new HashMap(100);
        Map<String, Object> lose = new HashMap(100);

        //判断一级特征是否相等

        if (!PcSearchSql.judgeStairFeature(sourceDo, tarDo)) {
            res.setResult("新设备，一级特征不匹配");
            return;
        }

        //flag 1：非IE8JS 2：非IE8H5 3：IE8JS 4：IE8H5
        //非1,2,3肯定是4
        switch (sourceDo.getFlag()){
            case 1:
                PcSearchSql.buildPcMatchSql(sourceDo, tarDo, pcMap, res, score, lose);
                break;
            case 2:
                PcSearchSql.buildClientMatchSql(sourceDo, tarDo,clientMap,  res, score, lose);
                break;
            case 3:
                PcSearchSql.buildIe8CliOrPcMatchSql(sourceDo, tarDo,iePcMap,res, score, lose);
                break;
            default:
                PcSearchSql.buildIe8CliOrPcMatchSql(sourceDo, tarDo,ieClientMap, res, score, lose);
        }

        PcSearchSql.numUaScore(sourceDo, tarDo.getUserAgent(),res, score, lose);

        res.setScoreField(score.toString());
        log.info("匹配加分字段详情:{}",score.toString());

        res.setLoseField(lose.toString());
        log.info("匹配未加分字段详情:{}",lose.toString());
        BigDecimal divide = res.getScore().divide(res.getTotalScore(), 4, BigDecimal.ROUND_HALF_UP);
        if (divide.compareTo(PC_DEVICE_SCORE) ==
                BigDecimal.ONE.intValue()){
            res.setResult("老设备，特征分值占比="+divide);
        }else {
            res.setResult("新设备，特征分值占比="+divide);
        }
    }


    /**
     * 安卓设备评分
     *
     * @return 评分结果
     */

    public static void androidGrade(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo,
                                                 DeviceGradeResDTO res){

        Map<String, Object> score = new HashMap(100);
        Map<String, Object> lose = new HashMap(100);

        if (!AndroidSearchSql.judgeStairFeature(sourceDo,tarDo)) {
            res.setResult("新设备，一级特征不匹配");
            return;
        }

        AndroidSearchSql.buildMatchSql(sourceDo, tarDo,res,score, lose);

        res.setScoreField(score.toString());
        log.info("匹配加分字段详情:{}",score.toString());

        res.setLoseField(lose.toString());
        log.info("匹配未加分字段详情:{}",lose.toString());

        if (res.getScore().compareTo(DEVICE_SCORE) == -BigDecimal.ONE.intValue()) {
            res.setResult("老设备");
        }else {
            res.setResult("新设备");
        }
    }
    public static void getScore(Map<String, BigDecimal> weight){
        Set<String> strings = weight.keySet();
        Iterator<String> iterator = strings.iterator();
        BigDecimal score = new BigDecimal(0.0);
        while (iterator.hasNext()){
            String key = iterator.next();
            if ("extend1".equals(key)) {
                continue;
            }
            BigDecimal value = weight.get(key);
            score = score.add(value);
        }

        System.out.println(score);
    }

    public static void main(String[] args) {


        getScore(pcMap);
        getScore(clientMap);
        getScore(iePcMap);
        getScore(ieClientMap);


        System.out.println("20180831103355597941505462619201b2ba949220e2994b87f7e95cff1f78d3".hashCode());

        System.out.println("20180830095607696785779603311143733ae0f05b95c7c6e6a3e53f5d77b03c".hashCode());
    }
}
