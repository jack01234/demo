package com.example.demo.utils;


import com.example.demo.model.AndroidDeviceInfoDO;
import com.example.demo.model.PcDeviceInfoDO;
import com.example.demo.model.bo.AndroidDeviceInfoBO;
import com.example.demo.model.bo.PcDeviceInfoBO;
import org.springframework.util.StringUtils;

/**
 * 通用方法
 *
 * @author yingmuhuadao
 * @version 5.0
 * @date 2018/4/2
 */
public class CommonUtil {

    private CommonUtil(){}

    /**
     * 转换为hash
     * @return 转换结果
     */
    public static PcDeviceInfoDO pcToHash(PcDeviceInfoBO pcDeviceInfoBO) {
        PcDeviceInfoDO infoDO = new PcDeviceInfoDO();
        //用于计分条件匹配
        infoDO.setPretendBrowser(pcDeviceInfoBO.getPretendBrowser());
        infoDO.setPretendSystem(pcDeviceInfoBO.getPretendSystem());
        infoDO.setHasLiedLanguages(pcDeviceInfoBO.getHasLiedLanguages());
        infoDO.setPretendResolution(pcDeviceInfoBO.getPretendResolution());
        infoDO.setDeviceSys(pcDeviceInfoBO.getDeviceSys());
        infoDO.setUserAgent(pcDeviceInfoBO.getUserAgent());


        //to hash
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getAdBlock())) {
            infoDO.setAdBlock(pcDeviceInfoBO.getAdBlock().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getCanvasId())) {
            infoDO.setCanvasId(pcDeviceInfoBO.getCanvasId().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getColorDepth())) {
            infoDO.setColorDepth(pcDeviceInfoBO.getColorDepth().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getCpuClass())) {
            infoDO.setCpuClass(pcDeviceInfoBO.getCpuClass().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getHashIndexedDB())) {
            infoDO.setHashIndexedDB(pcDeviceInfoBO.getHashIndexedDB().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getLocalStorage())) {
            infoDO.setLocalStorage(pcDeviceInfoBO.getLocalStorage().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getPlatform())) {
            infoDO.setPlatform(pcDeviceInfoBO.getPlatform().hashCode());
        }


        if (!StringUtils.isEmpty(pcDeviceInfoBO.getMicrophone())) {
            infoDO.setMicrophone(pcDeviceInfoBO.getMicrophone().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getPixelRatio())) {
            infoDO.setPixelRatio(pcDeviceInfoBO.getPixelRatio().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getSessionStorage())) {
            infoDO.setSessionStorage(pcDeviceInfoBO.getSessionStorage().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getTimeZone())) {
            infoDO.setTimeZone(pcDeviceInfoBO.getTimeZone().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getWebglRenderer())) {
            infoDO.setWebglRenderer(pcDeviceInfoBO.getWebglRenderer().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getWebglVendor())) {
            infoDO.setWebglVendor(pcDeviceInfoBO.getWebglVendor().hashCode());
        }
        //由于cameraId值大多为NOVAL，所以将cameraId的值取为videoId的取值,用于匹配
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getVideoId())) {
            infoDO.setCameraId(pcDeviceInfoBO.getVideoId().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getAudioId())) {
            infoDO.setAudioId(pcDeviceInfoBO.getAudioId().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getLanguage())) {
            infoDO.setLanguage(pcDeviceInfoBO.getLanguage().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getDeviceMemory())) {
            infoDO.setDeviceMemory(pcDeviceInfoBO.getDeviceMemory().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getHardwareConcurrency())) {
            infoDO.setHardwareConcurrency(pcDeviceInfoBO.getHardwareConcurrency().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getResolution())) {
            infoDO.setResolution(pcDeviceInfoBO.getResolution().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getTouchSupport())) {
            infoDO.setTouchSupport(pcDeviceInfoBO.getTouchSupport().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getOpenDatabase())) {
            infoDO.setOpenDatabase(pcDeviceInfoBO.getOpenDatabase().hashCode());
        }

        if (!StringUtils.isEmpty(pcDeviceInfoBO.getPlugins())) {
            infoDO.setPlugins(pcDeviceInfoBO.getPlugins().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getJsFonts())) {
            infoDO.setJsFonts(pcDeviceInfoBO.getJsFonts().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getEverCookie())) {
            infoDO.setEverCookie(pcDeviceInfoBO.getEverCookie().hashCode());
        }
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getVideoId())) {
            infoDO.setVideoId(pcDeviceInfoBO.getVideoId().hashCode());
        }
        //ip to hash
        if (!StringUtils.isEmpty(pcDeviceInfoBO.getIp())) {
            infoDO.setExtend1(pcDeviceInfoBO.getIp().hashCode());
        }
        return infoDO;
    }

    /**
     * 转换为hash
     * @return 转换结果
     */
    public static AndroidDeviceInfoDO toHash(AndroidDeviceInfoBO infoBO){
        AndroidDeviceInfoDO infoDO = new AndroidDeviceInfoDO();
        if (!StringUtils.isEmpty(infoBO.getDeviceName())) {
            infoDO.setDeviceName(infoBO.getDeviceName().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getCpu())) {
            infoDO.setCpu(infoBO.getCpu().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getUnitType())) {
            infoDO.setUnitType(infoBO.getUnitType().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getSysVer())) {
            infoDO.setSysVer(infoBO.getSysVer().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getImei())) {
            infoDO.setImei(ParamCheckUtil.strCompare(infoBO.getImei()).hashCode());
            infoDO.setImeiStr(infoBO.getImei());
        }
        if (!StringUtils.isEmpty(infoBO.getSimulator())) {
            infoDO.setSimulator(infoBO.getSimulator().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getTimeZone())) {
            infoDO.setTimeZone(infoBO.getTimeZone().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getSystemLan())) {
            infoDO.setSystemLan(infoBO.getSystemLan().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getIsRoot())) {
            infoDO.setIsRoot(infoBO.getIsRoot().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getFontNum())) {
            infoDO.setFontNum(infoBO.getFontNum().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getScreenRes())) {
            infoDO.setScreenRes(infoBO.getScreenRes().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getBios())) {
            infoDO.setBios(infoBO.getBios().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getImsi())) {
            infoDO.setImsi(ParamCheckUtil.strCompare(infoBO.getImsi()).hashCode());
            infoDO.setImsiStr(infoBO.getImsi());
        }
        if (!StringUtils.isEmpty(infoBO.getPhoneNum())) {
            infoDO.setPhoneNum(ParamCheckUtil.strCompare(infoBO.getPhoneNum()).hashCode());
            infoDO.setPhoneNUmStr(infoBO.getPhoneNum());
        }
        if (!StringUtils.isEmpty(infoBO.getMac())) {
            infoDO.setMac(infoBO.getMac().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getFcpu())) {
            infoDO.setFcpu(infoBO.getFcpu().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getTotalSize())) {
            infoDO.setTotalSize(infoBO.getTotalSize().hashCode());
        }
        if (!StringUtils.isEmpty(infoBO.getAndroidId())) {
            infoDO.setAndroidId(infoBO.getAndroidId().hashCode());
        }
        return infoDO;
    }
    /**
     * 判断请求所属算分逻辑
     *
     *
     * @return 结果
     */
    public static Integer getFlag(String deviceSys, boolean ieFlag){
        Integer flag;
        //IE8流程
        if (ieFlag) {
            if ("PC".equals(deviceSys)) {

                flag = 3;

            } else {

                flag = 4;

            }
        } else {
            if ("PC".equals(deviceSys)) {

                flag = 1;

            } else {

                flag = 2;

            }
        }

        return flag;
    }

    /**
     *
     * 根据不同的请求参数做出判断
     * 为相似度阈值的取值做判断
     *
     * @param req 请求DO
     * @param res ignite 搜索响应DO
     *
     */
    public static boolean judgeSimilarity(PcDeviceInfoDO req, PcDeviceInfoDO res){
        //evercookie不相等ip相等,返回true,否则false
        if (null == req.getEverCookie() || null == req.getExtend1()){
            return false;
        }

        if ((!req.getEverCookie().equals(res.getEverCookie())) && req.getExtend1().equals(res.getExtend1())) {
            return true;
        }

        return false;
    }
}
