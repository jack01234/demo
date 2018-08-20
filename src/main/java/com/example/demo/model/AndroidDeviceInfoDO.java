package com.example.demo.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 移动端设备采集信息
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Data
@Table(name = "T_DEVICE_ANDROID_HASH_INFO")
@Entity
@DynamicUpdate
public class AndroidDeviceInfoDO implements Serializable {

    /**
     *  序列化ID
     */
    private static final long serialVersionUID = -3017665976484555699L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 数据库主键
     */
    @Column(name = "XYID")
    private String xyid;
    /**
     * 设备名称
     */
    @Column(name = "DEVICE_NAME")
    private Integer deviceName;

    /**
     * CPU
     */
    @Column(name = "CPU")
    private Integer cpu;

    /**
     * 设备类型
     */
    @Column(name = "UNIT_TYPE")
    private Integer unitType;

    /**
     * 系统版本
     */
    @Column(name = "SYS_VER")
    private Integer sysVer;

    /**
     * imei
     */
    @Column(name = "IMEI")
    private Integer imei;


    /**
     * 模拟器
     */
    @Column(name = "SIMULATOR")
    private Integer simulator;

    /**
     * 时区
     */
    @Column(name = "TIME_ZONE")
    private Integer timeZone;

    /**
     * 系统语言
     */
    @Column(name = "SYSTEM_LAN")
    private Integer systemLan;

    /**
     * 是否是管理员权限
     */
    @Column(name = "IS_ROOT")
    private Integer isRoot;

    /**
     * 字体大小
     */
    @Column(name = "FONT_NUM")
    private Integer fontNum;

    /**
     * 屏幕分辨率
     *
     */
    @Column(name = "SCREEN_RES")
    private Integer screenRes;

    /**
     * BIOS
     */
    @Column(name = "BIOS")
    private Integer bios;

    /**
     * IMSI
     */
    @Column(name = "IMSI")
    private Integer imsi;

    /**
     * 电话号码
     */
    @Column(name = "PHONE_NUM")
    private Integer phoneNum;

    /**
     * MAC
     */
    @Column(name = "MAC")
    private Integer mac;

    /**
     * 指令周期
     */
    @Column(name = "FCPU")
    private Integer fcpu;

    /**
     * 总内存
     */
    @Column(name = "TOTAL_SIZE")
    private Integer totalSize;

    /**
     * android 设备序列号
     */
    @Column(name = "ANDROID_ID")
    private Integer androidId;
    /**
     * 处理标识
     */
    @Column(name = "DEAL_FLAG")
    private String dealFlag;
    /**
     * 备用字段1
     */
    @Column(name = "EXTEND1")
    private Integer extend1;
    /**
     * 备用字段2
     */
    @Column(name = "EXTEND2")
    private Integer extend2;
    /**
     * 备用字段3
     */
    @Column(name = "EXTEND3")
    private Integer extend3;
    /**
     * imsi号
     */
    @Transient
    private String imsiStr;
    /**
     * imei号
     */
    @Transient
    private String imeiStr;
    /**
     * 手机号码
     */
    @Transient
    private String phoneNUmStr;
}
