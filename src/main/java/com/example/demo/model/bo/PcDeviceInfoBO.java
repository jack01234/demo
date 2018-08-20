package com.example.demo.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * PC端设备采集信息BO
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Data
public class PcDeviceInfoBO implements Serializable {

    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 4330619121348114361L;

    /**
     * 请求IP地址
     */
    private String ip;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 设备或缓冲器上的调色板的比特深度
     */
    private String colorDepth;

    /**
     * WebGL的发货商
     */
    private String webglVendor;

    /**
     * WebGL的渲染器
     */
    private String webglRenderer;

    /**
     * 浏览器session存储
     */
    private String sessionStorage;

    /**
     * 浏览器本地存储
     */
    private String localStorage;

    /**
     * 存储在客户端本地的 NoSQL 数据库
     */
    private String hashIndexedDB;

    /**
     * Canvas的设备指纹
     */
    private String canvasId;

    /**
     * 浏览器系统的 CPU 等级
     */
    private String cpuClass;

    /**
     * 平台
     */
    private String platform;

    /**
     * 时区
     */
    private String timeZone;

    /**
     * 浏览器插件
     */
    private String plugins;

    /**
     * 一种屏蔽广告的插件
     */
    private String adBlock;

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
     * 摄像头ID
     */
    private String cameraId;

    /**
     * 音频IDdeviceMemory
     */
    private String audioId;

    /**
     * language
     */
    private String language;

    /**
     * 设备内存
     */
    private String deviceMemory;

    /**
     * 支持硬件并发数
     */
    private String hardwareConcurrency;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * database
     */
    private String openDatabase;

    /**
     *支持的语言
     */
    private String hasLiedLanguages;

    /**
     * 是否支持触屏
     */
    private String touchSupport;

    /**
     * 字体
     */
    private String jsFonts;

    /**
     * microphone
     */
    private String microphone;
    /**
     * 像素比
     */
    private String pixelRatio;
    /**
     *客户端传递ID
     */
    private String everCookie;
    /**
     * 视屏ID
     */
    private String videoId;
    /**
     * 设备操作系统 PC\ANDROID\IOS
     */
    private String deviceSys;
    /**
     * 日志ID
     */
    private String transLogId;
}
