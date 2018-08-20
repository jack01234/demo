package com.example.demo.common;

import java.math.BigDecimal;

/**
 * 公共常量
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/11
 */
public class CommonConstant {
    /**
     * private constructor
     */
    private CommonConstant() {
    }
    /**
     * 客户端设备打分
     */
    public static final BigDecimal DEVICE_SCORE = BigDecimal.valueOf(10);
    /**
     * pc端设备打分
     */
    public static final BigDecimal PC_DEVICE_SCORE = BigDecimal.valueOf(0.9);
    /**
     * pc端设备ua评分
     */
    public static final BigDecimal PC_UA_SCORE = BigDecimal.valueOf(0.978);
    /**
     * pc端设备总分
     */
    public static final BigDecimal PC_TOTAL_SCORE = BigDecimal.valueOf(5.377);
    /**
     * 移动端设备总分
     */
    public static final BigDecimal CLIENT_TOTAL_SCORE = BigDecimal.valueOf(4.018);
    /**
     * IE pc端设备总分
     */
    public static final BigDecimal IE_PC_TOTAL_SCORE = BigDecimal.valueOf(4.859);
    /**
     * IE移动端设备总分
     */
    public static final BigDecimal IE_CLIENT_TOTAL_SCORE = BigDecimal.valueOf(7.582);
    /**
     * redis LOCK 超时时间
     */
    public static final Long REDIS_LOCK_OUT_TIME = 10L;

    /**
     * redis ignite status 超时时间
     */
    public static final Long REDIS_IGNITE_OUT_TIME = 2L;
    /**
     * mac 默认值
     */
    public static final String MAC_DEFAULT_VALUE = "02:00:00:00:00:00";
    /**
     * 默认值
     */
    public static final String NO_VAL = "NOVAL";
    /**
     * TOSTRING 连接符
     */
    public static final String JOIN_SYMBOL = ":";
}
