package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 移动端设备采集信息
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Data
public class AndroidDeviceInfoDO implements Serializable {

    /**
     *  序列化ID
     */
    private static final long serialVersionUID = -3017665976484555699L;

    /**
     * 主键
     */

    private Long id;

    /**
     * 数据库主键
     */

    private String xyid;
    /**
     * 设备名称
     */

    private Integer deviceName;

    /**
     * CPU
     */

    private Integer cpu;

    /**
     * 设备类型
     */

    private Integer unitType;

    /**
     * 系统版本
     */

    private Integer sysVer;

    /**
     * imei
     */
    private Integer imei;


    /**
     * 模拟器
     */
    private Integer simulator;

    /**
     * 时区
     */
    private Integer timeZone;

    /**
     * 系统语言
     */
    private Integer systemLan;

    /**
     * 是否是管理员权限
     */
    private Integer isRoot;

    /**
     * 字体大小
     */
    private Integer fontNum;

    /**
     * 屏幕分辨率
     *
     */
    private Integer screenRes;

    /**
     * BIOS
     */
    private Integer bios;

    /**
     * IMSI
     */
    private Integer imsi;

    /**
     * 电话号码
     */
    private Integer phoneNum;

    /**
     * MAC
     */
    private Integer mac;

    /**
     * 指令周期
     */
    private Integer fcpu;

    /**
     * 总内存
     */
    private Integer totalSize;

    /**
     * android 设备序列号
     */
    private Integer androidId;
    /**
     * 处理标识
     */
    private String dealFlag;
    /**
     * 备用字段1
     */
    private Integer extend1;
    /**
     * 备用字段2
     */
    private Integer extend2;
    /**
     * 备用字段3
     */
    private Integer extend3;
    /**
     * imsi号
     */
    private String imsiStr;
    /**
     * imei号
     */
    private String imeiStr;
    /**
     * 手机号码
     */
    private String phoneNUmStr;
}
