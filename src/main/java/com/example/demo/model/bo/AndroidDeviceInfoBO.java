package com.example.demo.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 设备采集信息BO
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Data
public class AndroidDeviceInfoBO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = -5898777809080635570L;
    /**
     * 设备ID
     */
    private String givingXyid;
    /**
     * 老的设备ID
     */
    private String firstXyid;
    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * CPU
     */
    private String cpu;

    /**
     * 设备类型
     */
    private String unitType;

    /**
     * 系统版本
     */
    private String sysVer;

    /**
     * imei
     */
    private String imei;

    /**
     * 模拟器
     */
    private String simulator;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 系统语言
     */
    private String systemLan;

    /**
     * 是否是管理员权限
     */
    private String isRoot;

    /**
     * 字体大小
     */
    private String fontNum;

    /**
     * 屏幕分辨率
     *
     */
    private String screenRes;

    /**
     * BIOS
     */
    private String bios;

    /**
     * IMSI
     */
    private String imsi;

    /**
     * 电话号码
     */
    private String phoneNum;

    /**
     * MAC
     */
    private String mac;

    /**
     * 指令周期
     */
    private String fcpu;

    /**
     * 总内存
     */
    private String totalSize;
    /**
     * android 设备序列号
     */
    private String androidId;
    /**
     * 是否双开
     */
    private String isVirtualApp;

    /**
     * 日志ID
     */
    private String transLogId;
}
