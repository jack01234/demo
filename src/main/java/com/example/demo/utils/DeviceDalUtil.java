package com.example.demo.utils;


import com.example.demo.model.dto.DeviceGradeResDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.demo.utils.SqlConstant.*;


/**
 * dal util
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/11
 */
@Slf4j
public class DeviceDalUtil {
    private static int NUM_TWO = 2;
    private static int NUM_THREE = 3;
    private static String SYMBOL = ",";
    private DeviceDalUtil(){}

    /**
     *
     * case when 模板
     *
     */
    public static String caseWhenTemplate(String fieldName,Integer value,BigDecimal score){
        return CASE_WHEN_SQL+fieldName+EQUAL_SIGN+value+
                " THEN "+score+" ELSE 0 END + ";
    }

    /**
     *
     * case when end 末尾字段模板
     *
     */
    public static String caseWhenEndTemplate(String fieldName,Integer value,BigDecimal score){
        return CASE_WHEN_SQL+fieldName+EQUAL_SIGN+value+
                " THEN "+score+" ELSE 0 END";
    }

    /**
     * sql 模板
     *
     * @param fieldName 字段名称
     * @param value 字段值
     * @param score 特征分数
     * @return SQL
     */

    public static String caseWhenStringTemplate(String fieldName, String value, BigDecimal score){
        return CASE_WHEN_SQL+fieldName+EQUAL_SIGN+"'"+value+"'"+
                " THEN "+score+" ELSE 0 END + ";
    }

    /**
     *
     * case then sql 模板
     *
     */

    public static String caseThenTemplate(String fieldNameOne, String fieldNameTwo, Integer valueOne, Integer valueTwo,
                                          Map<String, BigDecimal> weightMap){
        return CASE_WHEN_SQL+fieldNameOne+EQUAL_SIGN+valueOne+
                THEN+weightMap.get(fieldNameOne)+
                WHEN+fieldNameTwo+EQUAL_SIGN+valueTwo+
                THEN+weightMap.get(fieldNameTwo)+
                " ELSE 0 END + ";

    }

    /**
     *
     * or 条件SQL 模板（可用于取不到加分逻辑）
     *
     *
     * @param fieldName 字段名称
     * @param value 字段值
     * @param orValue or 语句值
     * @param score 特征分数
     * @return SQL
     *
     */
    public static String caseWhenOrTemplate(String fieldName, Integer value, Integer orValue, BigDecimal score){

        return CASE_WHEN_SQL+fieldName+EQUAL_SIGN+value+OR+fieldName+EQUAL_SIGN+orValue+
                " THEN "+score+" ELSE 0 END + ";

    }


    /**
     *
     * 值比较
     *
     *
     */
    public static void compareTemplate(Object sourceValue, Object tarValue, BigDecimal grade, DeviceGradeResDTO res,
                                       Map<String, Object> score, Map<String, Object> lose, String fieldName){
        if (sourceValue.equals(tarValue)){
            res.setScore(null == res.getScore()?BigDecimal.valueOf(0):res.getScore().add(grade));
            score.put(fieldName, grade);
        } else {
            if (USER_AGENT.equals(fieldName)){
                return;
            }
            lose.put(fieldName, grade);
        }
    }

}
