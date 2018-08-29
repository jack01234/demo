package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * pc新采集字段数据
 *
 * @author: yingmuhuadao
 * @date: Created in 11:02 2018/8/29
 */
@Data
public class PcDeviceNewInfoDO implements Serializable {
    /**
     *
     * 序列化ID
     *
     */
    private static final long serialVersionUID = 6427379296835890009L;

    /**
     * 字符编码
     *
     */

    private String charset;

    /**
     *
     * 哈希校验和
     *
     */

    private String hashCode;

    /**
     *
     * 当前浏览器可识别的特定插件关联的 mime 对象列表
     *
     */

    private String mimeTypes;

    /**
     *
     *内网 IP
     */

    private String localIP;

    /**
     * 屏幕信息
     *（colorDepth x deviceXDPI x width x height）
     */

    private String srcScreeSize;

    /**
     *
     * 用户代理
     */

    private String userAgent;

    /**
     *
     * HTML5帆布指纹
     */

    private String canvasId;


    /**
     *
     * 3D 绘图
     */

    private String webglVendor;


    /**
     *
     *3D 绘图引擎
     *
     */
    private String webglRenderer;

    /**
     *
     * 浏览器安装插件列表
     *
     */
    private String plugins;


    /**
     * 屏幕尺寸（除去Windows 任务栏之后的宽乘高）
     *
     */
    private String resolution;

    /**
     *
     * 字体
     */
    private String jsFonts;

    /**
     * 会话缓存
     *
     */
    private String sessionStorage;

    /**
     *
     * 本地缓存
     *
     */
    private String localStorage;

    /**
     * 本地数据库
     *
     */
    private String hashIndexedDB;

    /**
     * 操作系统
     *
     */
    private String cpuClass;

    /**
     * 平台
     *
     */
    private String platform;

    /**
     * 时区
     *
     */
    private String timeZone;

    /**
     *  一种屏蔽广告的插件
     *
     */
    private String adBlock;

    /**
     * 语言
     *
     */
    private String language;

    /**
     * 设备内存
     *
     */
    private String deviceMemory;

    /**
     *
     * 触摸设备信息
     *
     */
    private String touchSupport;

    /**
     *
     * 像素比
     */
    private String pixelRatio;

    /**
     *
     * SDK版本
     */
    private String sdkVer;

    /**
     *
     * 系统版本
     */
    private String sysVer;

    /**
     *
     * 调色板的比特深度
     */
    private String colorDepth;

    /**
     * 是否伪装操作系统
     *
     */
    private String pretendSystem;

    /**
     *
     * 是否伪装浏览器
     */
    private String pretendResolution;

    /**
     * 是否伪装浏览器
     *
     */
    private String pretendBrowser;

    /**
     *
     * 浏览器名称
     */
    private String browserName;

    /**
     *
     * 浏览器版本号
     */
    private String browserVer;

    /**
     *
     * 网络类型
     */
    private String networkType;

    /**
     *
     * 设备类型
     */
    private String deviceSys;


    /**
     *
     * 桌面端还是移动端
     */
    private String wapWeb;

    /**
     *
     * flash版本
     */
    private String flashVersion;

    /**
     *
     * CPU核数
     */
    private String hardwareConcurrencyKey;
}
