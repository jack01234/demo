package com.example.demo.common;
/**
 * Ignite 配置常量
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/11
 */
public class IgniteConstant {

    /**
     * private constructor
     */
    private IgniteConstant(){}

    /**
     * cacheName
     */
    public static final String CACHE_NAME = "SQL_PUBLIC_T_ANDROID_DEVICE_INFO";

    /**
     * cacheName
     */
    public static final String PC_CACHE_NAME = "SQL_PUBLIC_T_PC_DEVICE_INFO";

    /**
     * ios cacheName
     */
    public static final String IOS_CACHE_NAME = "SQL_PUBLIC_T_IOS_DEVICE_INFO";

    /**
     * IGNITE INSTANCE
     */
    public static  final String IGNITEINSTANCE_NAME= "springDataNode";

    /**
     * ignite timeout time
     */
    public static final Long CONNECT_TIME_OUT = 5000L;

    /**
     * limitSql
     */
    public static final String LIMIT_SQL = " ORDER BY SCORE ASC LIMIT 5";
    /**
     * limitSql
     */
    public static final String PC_LIMIT_SQL = " ORDER BY SCORE DESC LIMIT 5";
}
