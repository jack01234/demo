package com.example.demo.common;

import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.SqlConstant;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.demo.utils.SqlConstant.USER_AGENT;

/**
 * PC sql 模板
 *
 * @author: yingmuhuadao
 * @date: Created in 15:58 2018/9/5
 */
public class PcSqlTemplate {

    public static String IS_FLAG = "1";

    /**
     *
     * 值比较
     *
     *
     */
    public static void compareTemplate(Object sourceValue, Object tarValue, BigDecimal grade, DeviceGradeResDTO res,
                                       Map<String, Object> score, Map<String, Object> lose, String fieldName){
        if (sourceValue.equals(tarValue)){
            res.setScore(null == res.getScore()?grade:res.getScore().add(grade));
            score.put(fieldName, grade);
        } else {
            if (USER_AGENT.equals(fieldName)){
                return;
            }
            lose.put(fieldName, grade);
        }
    }

    /**
     *
     * 值比较
     *
     *
     */
    public static void novalScoreTemplate(Object sourceValue, Object tarValue, BigDecimal grade, DeviceGradeResDTO res,
                                       Map<String, Object> score, Map<String, Object> lose, String fieldName){

        if (SqlConstant.NAVAL_HASH.equals(sourceValue) || SqlConstant.NAVAL_HASH.equals(tarValue)
                || sourceValue.equals(tarValue)){
            res.setScore(null == res.getScore()?grade:res.getScore().add(grade));
            score.put(fieldName, grade);
        } else {
            if (USER_AGENT.equals(fieldName)){
                return;
            }
            lose.put(fieldName, grade);
        }
    }



    /**
     *
     * 加分模板
     *
     *
     */
    public static void scoreTemplate(BigDecimal grade, DeviceGradeResDTO res,
                                       Map<String, Object> score, String fieldName){

        res.setScore(null == res.getScore()?grade:res.getScore().add(grade));
        score.put(fieldName, grade);
    }


    /**
     *
     * 分辨率字段模板
     *
     *
     */
    public static void resTemplate(Object sourceValue, Object tarValue, BigDecimal grade, DeviceGradeResDTO res,
                                       Map<String, Object> score, Map<String, Object> lose, String fieldName, boolean flag){
        if (sourceValue.equals(tarValue) || flag){
            res.setScore(null == res.getScore()?grade:res.getScore().add(grade));
            score.put(fieldName, grade);
        } else {
            if (USER_AGENT.equals(fieldName)){
                return;
            }
            lose.put(fieldName, grade);
        }
    }
}
