package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * PC端设备采集信息 DO
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Data
public class PcDeviceInfoDO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 7091130018491507638L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 设备ID
     */
    private String xyid;
    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 设备或缓冲器上的调色板的比特深度
     */
    private Integer colorDepth;

    /**
     * WebGL的发货商
     */
    private Integer webglVendor;

    /**
     * WebGL的渲染器
     */
    private Integer webglRenderer;

    /**
     * 浏览器session存储
     */
    private Integer sessionStorage;

    /**
     * 浏览器本地存储
     */
    private Integer localStorage;

    /**
     * 存储在客户端本地的 NoSQL 数据库
     */
    private Integer hashIndexedDB;

    /**
     * Canvas的设备指纹
     */
    private Integer canvasId;

    /**
     * 浏览器系统的 CPU 等级
     */
    private Integer cpuClass;

    /**
     * 平台
     */
    private Integer platform;

    /**
     * 时区
     */
    private Integer timeZone;

    /**
     * 一种屏蔽广告的插件
     */
    private Integer adBlock;

    /**
     * 摄像头ID
     */
    private Integer cameraId;

    /**
     * 音频ID
     */
    private Integer audioId;

    /**
     * language
     */
    private Integer language;

    /**
     * 设备内存
     */
    private Integer deviceMemory;

    /**
     * 支持硬件并发数
     */
    private Integer hardwareConcurrency;

    /**
     * 分辨率
     */
    private Integer resolution;

    /**
     * database
     */
    private Integer openDatabase;

    /**
     * 是否支持触屏
     */
    private Integer touchSupport;

    /**
     * 插件
     */
    private Integer plugins;

    /**
     * JS_FONTS
     */
    private Integer jsFonts;

    /**
     * 处理标识
     */
    private String dealFlag;

    /**
     *像素比
     */
    private Integer pixelRatio;

    /**
     * micro phone
     */
    private Integer microphone;

    /**
     *客户端传递ID
     */
    private Integer everCookie;

    /**
     *视屏iD
     */
    private Integer videoId;

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

    /**用于匹配标识条件**/

    /**
     * 设备操作系统 PC\ANDROID\IOS
     */
    private String deviceSys;

    /**
     * 是否伪装操作系统
     */
    private String pretendSystem;

    /**
     * 是否伪装分辨率
     */
    private String pretendResolution;

    /**
     * 是否伪装浏览器
     */
    private String pretendBrowser;

    /**
     * hasLiedLanguages
     */
    private String hasLiedLanguages;

    /**
     * 浏览器端标识(1：非IE8JS 2：非IE8H5 3：IE8JS 4：IE8H5)
     */
    private Integer flag;
}
