package com.example.demo.convert;

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
}
