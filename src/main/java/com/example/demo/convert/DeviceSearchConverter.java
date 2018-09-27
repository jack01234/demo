package com.example.demo.convert;

import com.example.demo.enums.FlagEnum;
import com.example.demo.model.bo.AndroidDeviceInfoBO;
import com.example.demo.model.bo.PcDeviceInfoBO;
import org.apache.commons.lang3.StringUtils;

/**
 * converter
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/13
 */
public class DeviceSearchConverter {
    /**
     * 默认值
     */
    public static final String NO_VAL = "NOVAL";
    /**
     * excludeOpenDatabase
     */
    public static final String NOT_FLAG = "0";

    /**
     * mac 默认值
     */
    public static final String MAC_DEFAULT_VALUE = "02:00:00:00:00:00";
    /**
     * client convert to do
     * @param req 请求对象
     * @return 转换结果
     */
    public static PcDeviceInfoBO pcDtoConvertToBo(PcDeviceInfoBO req){
        PcDeviceInfoBO res = new PcDeviceInfoBO();
        res.setCameraId(StringUtils.isNotBlank(req.getCameraId())?req.getCameraId():NO_VAL);
        res.setAudioId(StringUtils.isNotBlank(req.getAudioId())?req.getAudioId():NO_VAL);
        res.setWebglVendor(StringUtils.isNotBlank(req.getWebglVendor())?req.getWebglVendor():NO_VAL);
        res.setLanguage(StringUtils.isNotBlank(req.getLanguage())?req.getLanguage():NO_VAL);

        res.setDeviceMemory(StringUtils.isNotBlank(req.getDeviceMemory())?req.getDeviceMemory():NO_VAL);
        res.setTouchSupport(StringUtils.isNotBlank(req.getTouchSupport())?req.getTouchSupport():NO_VAL);
        res.setResolution(StringUtils.isNotBlank(req.getResolution())?req.getResolution():NO_VAL);
        res.setOpenDatabase(StringUtils.isNotBlank(req.getOpenDatabase())?req.getOpenDatabase():NO_VAL);
        res.setJsFonts(StringUtils.isNotBlank(req.getJsFonts())?req.getJsFonts():NO_VAL);
        res.setHardwareConcurrency(StringUtils.isNotBlank(req.getHardwareConcurrency())?req.getHardwareConcurrency():NO_VAL);

        res.setIp(req.getIp());
        res.setDeviceSys(req.getDeviceSys());
        res.setTransLogId(req.getTransLogId());
        res.setHasLiedLanguages(StringUtils.isNotBlank(req.getHasLiedLanguages())?req.getHasLiedLanguages():NOT_FLAG);

        res.setMicrophone(StringUtils.isNotBlank(req.getMicrophone())?req.getMicrophone():NO_VAL);
        res.setPixelRatio(StringUtils.isNotBlank(req.getPixelRatio())?req.getPixelRatio():NO_VAL);
        res.setAdBlock(StringUtils.isNotBlank(req.getAdBlock())?req.getAdBlock():NOT_FLAG);
        res.setCanvasId(StringUtils.isNotBlank(req.getCanvasId())?req.getCanvasId():NO_VAL);
        res.setColorDepth(StringUtils.isNotBlank(req.getColorDepth())?req.getColorDepth():NO_VAL);

        res.setCpuClass(StringUtils.isNotBlank(req.getCpuClass())?req.getCpuClass():NO_VAL);
        res.setHashIndexedDB(StringUtils.isNotBlank(req.getHashIndexedDB())?req.getHashIndexedDB():NOT_FLAG);

        res.setLocalStorage(StringUtils.isNotBlank(req.getLocalStorage())?req.getLocalStorage():NOT_FLAG);

        res.setPlatform(StringUtils.isNotBlank(req.getPlatform())?req.getPlatform():NO_VAL);
        res.setPlugins(StringUtils.isNotBlank(req.getPlugins())?req.getPlugins():NO_VAL);
        res.setPretendBrowser(StringUtils.isNotBlank(req.getPretendBrowser())?req.getPretendBrowser():NOT_FLAG);

        res.setPretendResolution(StringUtils.isNotBlank(req.getPretendResolution())?req.getPretendResolution():NOT_FLAG);
        res.setPretendSystem(StringUtils.isNotBlank(req.getPretendSystem())?req.getPretendSystem():NOT_FLAG);
        res.setSessionStorage(StringUtils.isNotBlank(req.getSessionStorage())?req.getSessionStorage():NOT_FLAG);

        res.setTimeZone(StringUtils.isNotBlank(req.getTimeZone())?req.getTimeZone():NO_VAL);
        res.setUserAgent(StringUtils.isNotBlank(req.getUserAgent())?req.getUserAgent():NO_VAL);

        res.setWebglRenderer(StringUtils.isNotBlank(req.getWebglRenderer())?req.getWebglRenderer():NO_VAL);

        res.setEverCookie(StringUtils.isNotBlank(req.getEverCookie())?req.getEverCookie():NO_VAL);

        res.setVideoId(StringUtils.isNotBlank(req.getVideoId())?req.getVideoId():NO_VAL);
      return res;
    }



    /**
     * android convert to do
     * @param req 请求对象
     * @return 转换结果
     */
    public static AndroidDeviceInfoBO androidDtoConvertToBo(AndroidDeviceInfoBO req){
        AndroidDeviceInfoBO res = new AndroidDeviceInfoBO();
        res.setGivingXyid(req.getGivingXyid());
        res.setFirstXyid(req.getFirstXyid());
        res.setTotalSize(StringUtils.isNotBlank(req.getTotalSize())?req.getTotalSize():NO_VAL);
        res.setDeviceName(StringUtils.isNotBlank(req.getDeviceName())?req.getDeviceName():NO_VAL);
        res.setUnitType(StringUtils.isNotBlank(req.getUnitType())?req.getUnitType():NO_VAL);
        res.setBios(StringUtils.isNotBlank(req.getBios())?req.getBios():NO_VAL);
        res.setCpu(StringUtils.isNotBlank(req.getCpu())?req.getCpu():NO_VAL);
        res.setFcpu(StringUtils.isNotBlank(req.getFcpu())?req.getFcpu():NO_VAL);
        res.setFontNum(StringUtils.isNotBlank(req.getFontNum())?req.getFontNum():NO_VAL);
        res.setImei(StringUtils.isNotBlank(req.getImei())?req.getImei():NO_VAL);
        res.setImsi(StringUtils.isNotBlank(req.getImsi())?req.getImsi():NO_VAL);
        res.setIsRoot(StringUtils.isNotBlank(req.getIsRoot())?req.getIsRoot():FlagEnum.FALSE.getKey());
        if (StringUtils.isNotBlank(req.getMac())) {
            res.setMac(MAC_DEFAULT_VALUE.equals(req.getMac())?NO_VAL:req.getMac());
        } else {
            res.setMac(NO_VAL);
        }
        res.setPhoneNum(StringUtils.isNotBlank(req.getPhoneNum())?req.getPhoneNum():NO_VAL);
        res.setScreenRes(StringUtils.isNotBlank(req.getScreenRes())?req.getScreenRes():NO_VAL);
        res.setSimulator(StringUtils.isNotBlank(req.getSimulator())?req.getSimulator():FlagEnum.FALSE.getKey());
        res.setSystemLan(StringUtils.isNotBlank(req.getSystemLan())?req.getSystemLan():NO_VAL);
        res.setSysVer(StringUtils.isNotBlank(req.getSysVer())?req.getSysVer():NO_VAL);
        res.setTimeZone(StringUtils.isNotBlank(req.getTimeZone())?req.getTimeZone():NO_VAL);
        res.setTransLogId(req.getTransLogId());
        res.setAndroidId(StringUtils.isNotBlank(req.getAndroidId())?req.getAndroidId():NO_VAL);
        res.setIsVirtualApp(StringUtils.isNotBlank(req.getIsVirtualApp())?req.getIsVirtualApp():FlagEnum.FALSE.getKey());
        return res;
    }
}
