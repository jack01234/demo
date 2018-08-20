package com.example.demo.utils;

/**
 * 移动端sql常量配置
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/11
 */
public class SqlConstant {

    /**
     * private constructor
     */
    private SqlConstant(){}

    /**
     * NOVAL
     */
    public static final String NO_VAL = "NOVAL";
    /**
     * 等号
     */
    public static final String EQUAL_SIGN = " = ";
    /**
     * 不等号
     */
    public static final String NOT_EQUAL_SIGN = " != ";
    /**
     * 逗号
     */
    public static final String COMMA_SYMBOL = ",";

    /**
     * and 连接符
     */
    public static final String AND = " AND ";

    /**
     * OR 连接符
     */
    public static final String OR = " OR ";

    /**
     * WHEN 连接符
     */
    public static final String WHEN = " WHEN ";
    /**
     * THEN 连接符
     */
    public static final String THEN = " THEN ";

    /**
     * CASE_WHEN_SQL
     */
    public static final String  CASE_WHEN_SQL = " CASE WHEN ";

    /**
     * select sql
     */
    public static final String SELECT_SQL = "SELECT ( ";

    /**
     * update end sql
     */
    public static final String UPDATE_WHERE_SQL = " WHERE XYID = ?";
    /**
     * NAVAL的hash值
     */
    public static final Integer NAVAL_HASH = NO_VAL.hashCode();

    /**
     * PC_FLAG
     */
    public static final String PC_FLAG = "PC";

    /**
     * cpu
     */
    public static final String CPU = "cpu";

    /**
     * 设备类型
     */
    public static final String UNIT_TYPE = "unitType";

    /**
     * bios
     */
    public static final String BIOS = "bios";

    /**
     * imsi
     */
    public static final String IMSI = "imsi";

    /**
     * imei
     */
    public static final String IMEI = "imei";

    /**
     * MAC
     */
    public static final String MAC = "mac";

    /**
     * simulator
     */
    public static final String SIMULATOR = "simulator";

    /**
     * fcpu
     */
    public static final String FCPU = "fcpu";

    /**
     * screenRes
     */
    public static final String SCREENRES = "screenRes";

    /**
     * 手机号
     */
    public static final String PHONENUM = "phoneNum";

    /**
     * fontNum
     */
    public static final String FONTNUM = "fontNum";

    /**
     * isRoot
     */
    public static final String IS_ROOT = "isRoot";

    /**
     * systemLan
     */
    public static final String SYSTEM_LAN = "systemLan";

    /**
     * 时区
     */
    public static final String TIME_ZONE = "timeZone";

    /**
     * sysVer
     */
    public static final String SYS_VER = "sysVer";

    /**
     * deviceName
     */
    public static final String DEVICE_NAME = "deviceName";

    /**
     * totalSize
     */
    public static final String TOTAL_SIZE = "totalSize";

    /**
     * 像素比
     */
    public static final String PIXEL_RATIO = "pixelRatio";

    /**
     * romSize
     */
    public static final String ANDROID_ID = "androidId";

    /**
     * ip
     */
    public static final String EXTEND1 = "extend1";

    /**
     * 设备操作系统
     */
    public static final String DEVICE_SYS = "deviceSys";

    /****************************************************************************/
    /**
     * cpuClass
     */
    public static final String CPU_CLASS = "cpuClass";

    /**
     * canvasId
     */
    public static final String CANVAS_ID = "canvasId";

    /**
     * platform
     */
    public static final String PLATFORM = "platform";

    /**
     * webglVendor
     */
    public static final String WEBGL_VENDOR = "webglVendor";

    /**
     * webglRenderer
     */
    public static final String WEBGL_RENDERER = "webglRenderer";

    /**
     * addBehavior
     */
    public static final String DEVICE_MEMORY = "deviceMemory";

    /**
     * userAgent
     */
    public static final String USER_AGENT = "userAgent";

    /**
     * userLanguage
     */
    public static final String HARDWARE_CONCURRENCY = "hardwareConcurrency";

    /**
     * SYSTEMLANGUAGE
     */
    public static final String RESOLUTION = "resolution";

    /**
     * LANGUAGE
     */
    public static final String LANGUAGE = "language";

    /**
     * colorDepth
     */
    public static final String COLOR_DEPTH = "colorDepth";

    /**
     * sessionStorage
     */
    public static final String SESSION_STORAGE = "sessionStorage";

    /**
     * localStorage
     */
    public static final String LOCAL_STORAGE = "localStorage";

    /**
     * hashIndexedDB
     */
    public static final String HASHINDEX_DB = "hashIndexedDB";

    /**
     * MICROPHONE
     */
    public static final String MICROPHONE = "microphone";

    /**
     * excludeOpenDatabase
     */
    public static final String NOT_FLAG = "0";

    /**
     * plugins
     */
    public static final String PLUGINS = "plugins";

    /**
     * adBlock
     */
    public static final String AD_BLOCK = "adBlock";

    /**
     * everCookie
     */
    public static final String EVER_COOKIE = "everCookie";

    /**
     * devicePixelRatio
     */
    public static final String OPEN_DATABASE = "openDatabase";

    /**
     * cameraId
     */
    public static final String CAMERA_ID = "cameraId";

    /**
     * audioId
     */
    public static final String AUDIO_ID = "audioId";
    /**
     * VIDEO_ID
     */
    public static final String VIDEO_ID = "videoId";
    public static final String TOUCH_SUPPORT = "touchSupport";
    public static final String JS_FONTS = "jsFonts";
    /**
     * ie浏览器标识
     */
    public static final String IE_FLAG = "Trident";



    /**SQL信息**/
    /**
     * fromTableSql
     */
    public static final String FROM_TABLE_SQL = " ) AS SCORE,XYID,USERAGENT FROM T_PC_DEVICE_INFO WHERE ";
}
