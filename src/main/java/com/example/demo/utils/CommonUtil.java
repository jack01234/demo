package com.example.demo.utils;


import com.example.demo.model.PcDeviceInfoDO;
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
}
