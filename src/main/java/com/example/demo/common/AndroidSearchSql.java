package com.example.demo.common;

import com.example.demo.model.AndroidDeviceInfoDO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.DeviceDalUtil;
import com.example.demo.utils.SqlConstant;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.demo.utils.DeviceDalUtil.SYMBOL;
import static com.example.demo.utils.SqlConstant.NAVAL_HASH;

/**
 * 安卓设备匹配SQL
 *
 * @author: yingmuhuadao
 * @date: Created in 11:34 2018/8/20
 */
public class AndroidSearchSql {

    private String s;
    private String a;
    private String d;

    public AndroidSearchSql(String s, String a, String d){
        this.s = s;
        this.a = a;
        this.d = d;
    }

    /**
     *
     * 判断一级特征是否相等
     *
     */
    public static boolean judgeStairFeature(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo){

        return sourceDo.getCpu().equals(tarDo.getCpu()) && sourceDo.getUnitType().equals(tarDo.getUnitType()) &&
                sourceDo.getBios().equals(tarDo.getBios()) &&
                sourceDo.getSimulator().equals(tarDo.getSimulator());

    }


    /**
     * 构建sql
     *
     * @param sourceDo
     * @param tarDo
     * @param res
     * @param score
     * @param lose
     * @return
     */
    public static void buildMatchSql(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo,DeviceGradeResDTO res,
                                                  Map<String, Object> score, Map<String, Object> lose){

        //分辨率
        DeviceDalUtil.normalCompareTemplate(sourceDo.getScreenRes(),tarDo.getScreenRes(),BigDecimal.valueOf(3),res,
                score, lose, SqlConstant.SCREENRES);

        //IMEI
        DeviceDalUtil.normalCompareTemplate(sourceDo.getImei(),tarDo.getImei(),BigDecimal.valueOf(5),res,
                score, lose, SqlConstant.IMEI);

        //IMSI和phoneNum

        if (!NAVAL_HASH.equals(sourceDo.getImsi()) && !NAVAL_HASH.equals(sourceDo.getPhoneNum())) {

            buildImsiAndPhoneNumSql(sourceDo, tarDo, res, score, lose);

        }else if (!NAVAL_HASH.equals(sourceDo.getPhoneNum())){

            buildPhoneNumSql(sourceDo, tarDo, res, score, lose);

        } else if (!NAVAL_HASH.equals(sourceDo.getImsi())) {

            buildImsiSql(sourceDo,tarDo,res,score,lose);

        }

        //mac
        DeviceDalUtil.normalCompareTemplate(sourceDo.getMac(), tarDo.getMac(), BigDecimal.valueOf(3), res, score, lose,
                SqlConstant.MAC);

        //fontNum
        DeviceDalUtil.normalCompareTemplate(sourceDo.getFontNum(), tarDo.getFontNum(), BigDecimal.valueOf(2), res, score,
                lose, SqlConstant.FONTNUM);

        //systemLan
        DeviceDalUtil.normalCompareTemplate(sourceDo.getSystemLan(), tarDo.getSystemLan(), BigDecimal.valueOf(2), res,
                score, lose,SqlConstant.SYSTEM_LAN);


        //timeZone
        DeviceDalUtil.normalCompareTemplate(sourceDo.getTimeZone(), tarDo.getTimeZone(), BigDecimal.valueOf(2), res,
                score, lose, SqlConstant.TIME_ZONE);

        //sysVer
        DeviceDalUtil.normalCompareTemplate(sourceDo.getSysVer(), tarDo.getSysVer(), BigDecimal.valueOf(2), res, score,
                lose, SqlConstant.SYS_VER);

        //deviceName
        DeviceDalUtil.normalCompareTemplate(sourceDo.getDeviceName(), tarDo.getDeviceName(), BigDecimal.valueOf(3), res,
                score, lose, SqlConstant.DEVICE_NAME);

        //totalSize
        DeviceDalUtil.normalCompareTemplate(sourceDo.getTotalSize(), tarDo.getTotalSize(), BigDecimal.valueOf(4), res,
                score, lose, SqlConstant.TOTAL_SIZE);

        //fcpu
        DeviceDalUtil.normalCompareTemplate(sourceDo.getFcpu(), tarDo.getFcpu(), BigDecimal.valueOf(4), res, score, lose,
                SqlConstant.FCPU);

        //isRoot
        DeviceDalUtil.normalCompareTemplate(sourceDo.getIsRoot(), tarDo.getIsRoot(), BigDecimal.valueOf(2), res, score,
                lose, SqlConstant.IS_ROOT);

        //android
        if (!NAVAL_HASH.equals(sourceDo.getAndroidId())) {
            if (NAVAL_HASH.equals(sourceDo.getImsi()) && NAVAL_HASH.equals(sourceDo.getPhoneNum())
                    && NAVAL_HASH.equals(sourceDo.getImei())) {

                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(), tarDo.getAndroidId(), BigDecimal.valueOf(10),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (!NAVAL_HASH.equals(sourceDo.getImsi()) && NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && NAVAL_HASH.equals(sourceDo.getImei())) {
                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getImsi())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }


                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (NAVAL_HASH.equals(sourceDo.getImsi()) && !NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && NAVAL_HASH.equals(sourceDo.getImei())){

                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getPhoneNum())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }
                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (NAVAL_HASH.equals(sourceDo.getImsi()) && NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && !NAVAL_HASH.equals(sourceDo.getImei())) {

                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getImei())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }

                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (!NAVAL_HASH.equals(sourceDo.getImsi()) && !NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && NAVAL_HASH.equals(sourceDo.getImei())) {
                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getImsi()) && NAVAL_HASH.equals(tarDo.getPhoneNum())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }
                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);
            } else if (!NAVAL_HASH.equals(sourceDo.getImsi()) && NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && !NAVAL_HASH.equals(sourceDo.getImei())) {

                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getImsi()) && NAVAL_HASH.equals(tarDo.getImei())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }

                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (NAVAL_HASH.equals(sourceDo.getImsi()) && !NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && !NAVAL_HASH.equals(sourceDo.getImei())) {
                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getPhoneNum()) && NAVAL_HASH.equals(tarDo.getImei())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }
                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);

            } else if (!NAVAL_HASH.equals(sourceDo.getImsi()) && !NAVAL_HASH.equals(sourceDo.
                    getPhoneNum()) && !NAVAL_HASH.equals(sourceDo.getImei())) {

                if (!NAVAL_HASH.equals(tarDo.getAndroidId()) && !sourceDo.getAndroidId().equals(tarDo.getAndroidId()) &&
                        NAVAL_HASH.equals(tarDo.getPhoneNum()) && NAVAL_HASH.equals(tarDo.getImei()) &&
                        NAVAL_HASH.equals(tarDo.getImsi())){
                    res.setScore(res.getScore().add(BigDecimal.valueOf(10)));
                    score.put(SqlConstant.ANDROID_ID,BigDecimal.valueOf(10));
                    return;
                }
                DeviceDalUtil.normalCompareTemplate(sourceDo.getAndroidId(),tarDo.getAndroidId(),BigDecimal.valueOf(5),
                        res, score, lose, SqlConstant.ANDROID_ID);
            }
        }else {
            lose.put(SqlConstant.ANDROID_ID, BigDecimal.valueOf(5));
        }

    }

    /**
     * 构建相关字段SQL
     * @return SQL
     */
    public static void buildImsiAndPhoneNumSql(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo,DeviceGradeResDTO res,
                                                 Map<String, Object> score, Map<String, Object> lose){

        if (!StringUtils.isEmpty(sourceDo.getPhoneNUmStr()) && sourceDo.getPhoneNUmStr().split(SYMBOL).length>1 &&
                !StringUtils.isEmpty(sourceDo.getImsiStr()) && sourceDo.getImsiStr().split(SYMBOL).length>1) {
            String[] imsi = sourceDo.getImsiStr().split(DeviceDalUtil.SYMBOL);
            String[] phoneNum = sourceDo.getPhoneNUmStr().split(SYMBOL);

            if (moreImsi(tarDo,imsi,res,score,lose)) {

                return;

            }

            if (morePhoneNum(tarDo,phoneNum,res,score,lose)) {

                return;

            }
        }else if (!StringUtils.isEmpty(sourceDo.getPhoneNUmStr()) && sourceDo.getPhoneNUmStr().split(SYMBOL).length>1) {
            String[] phoneNum = sourceDo.getPhoneNUmStr().split(SYMBOL);

            if (simpleImsi(tarDo, sourceDo, res, score, lose)) {
                return;
            }

            if (morePhoneNum(tarDo,phoneNum,res,score,lose)) {

                return;

            }

        }else if (!StringUtils.isEmpty(sourceDo.getImsiStr()) && sourceDo.getImsiStr().split(SYMBOL).length>1) {
            String[] imsi = sourceDo.getImsiStr().split(SYMBOL);
            if (simplePhoneNum(tarDo, sourceDo,res,score,lose)) {

                return;

            }

            if (moreImsi(tarDo,imsi,res,score,lose)) {

                return;

            }

        }else {
            if (simplePhoneNum(tarDo, sourceDo, res, score, lose)){
                return;
            }

            if (simpleImsi(tarDo, sourceDo, res, score, lose)) {

                return;

            }

        }
    }


    public static boolean moreImsi(AndroidDeviceInfoDO tarDo, String[] imsi, DeviceGradeResDTO res,
                                   Map<String, Object> score, Map<String, Object> lose){
        if (!NAVAL_HASH.equals(tarDo.getImsi()) && !tarDo.getImsi().equals(imsi[0].hashCode()) &&
                !tarDo.getImsi().equals(imsi[1].hashCode())) {

            res.setScore(res.getScore().add(BigDecimal.valueOf(5)));
            score.put(SqlConstant.IMSI, BigDecimal.valueOf(5));
            return true;
        }else {

            lose.put(SqlConstant.IMSI, BigDecimal.valueOf(5));
            return false;
        }
    }


    public static boolean morePhoneNum(AndroidDeviceInfoDO tarDo, String[] phoneNum, DeviceGradeResDTO res,
                                       Map<String, Object> score, Map<String, Object> lose){

        if (!NAVAL_HASH.equals(tarDo.getPhoneNum()) && !tarDo.getPhoneNum().equals(phoneNum[0].hashCode()) &&
                !tarDo.getPhoneNum().equals(phoneNum[1].hashCode())) {
            res.setScore(res.getScore().add(BigDecimal.valueOf(5)));
            score.put(SqlConstant.PHONENUM, BigDecimal.valueOf(5));
            return true;
        } else {
            lose.put(SqlConstant.PHONENUM, BigDecimal.valueOf(5));
            return false;
        }
    }

    public static boolean simpleImsi(AndroidDeviceInfoDO tarDo, AndroidDeviceInfoDO sourceDo, DeviceGradeResDTO res,
                                     Map<String, Object> score, Map<String, Object> lose){
        if (!NAVAL_HASH.equals(tarDo.getImsi()) && !tarDo.getImsi().equals(sourceDo.getImsi())) {

            res.setScore(res.getScore().add(BigDecimal.valueOf(5)));
            score.put(SqlConstant.IMSI, BigDecimal.valueOf(5));
            return true;
        }else {

            lose.put(SqlConstant.IMSI, BigDecimal.valueOf(5));
            return false;
        }
    }

    public static boolean simplePhoneNum(AndroidDeviceInfoDO tarDo, AndroidDeviceInfoDO sourceDo, DeviceGradeResDTO res,
                                         Map<String, Object> score, Map<String, Object> lose){
        if (!NAVAL_HASH.equals(tarDo.getPhoneNum()) && !tarDo.getPhoneNum().equals(sourceDo.getPhoneNum())) {

            res.setScore(res.getScore().add(BigDecimal.valueOf(5)));
            score.put(SqlConstant.PHONENUM, BigDecimal.valueOf(5));
            return true;
        }else {

            lose.put(SqlConstant.PHONENUM, BigDecimal.valueOf(5));
            return false;
        }
    }



    /**
     * 构建IMSI字段SQL
     * @param sourceDo 请求对象
     * @return SQL
     */
    public static void buildImsiSql(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo, DeviceGradeResDTO res,
                                      Map<String, Object> score, Map<String, Object> lose){
        if (StringUtils.isEmpty(sourceDo.getImsiStr())) {
            DeviceDalUtil.normalCompareTemplate(sourceDo.getImsi(),tarDo.getImsi(), BigDecimal.valueOf(5),res, score,
                    lose, SqlConstant.IMSI);
            return ;
        }
        String[] split = sourceDo.getImsiStr().split(SYMBOL);
        if (split.length>1) {
           moreImsi(tarDo,split,res,score,lose);
        } else {
            DeviceDalUtil.normalCompareTemplate(sourceDo.getImsi(),tarDo.getImsi(), BigDecimal.valueOf(5),res, score,
                    lose, SqlConstant.IMSI);
        }
    }


    /**
     * 构建手机号SQL
     * @param sourceDo 请求对象
     * @return SQL
     */
    public static void buildPhoneNumSql(AndroidDeviceInfoDO sourceDo, AndroidDeviceInfoDO tarDo, DeviceGradeResDTO res,
                                          Map<String, Object> score, Map<String, Object> lose){
        if (StringUtils.isEmpty(sourceDo.getPhoneNUmStr())) {
            DeviceDalUtil.normalCompareTemplate(sourceDo.getPhoneNum(),tarDo.getPhoneNum(), BigDecimal.valueOf(5),res, score,
                    lose, SqlConstant.PHONENUM);
            return ;
        }
        String[] split = sourceDo.getPhoneNUmStr().split(SYMBOL);
        if (split.length>1) {

            morePhoneNum(tarDo,split,res,score,lose);

        }else {

            DeviceDalUtil.normalCompareTemplate(sourceDo.getPhoneNum(),tarDo.getPhoneNum(), BigDecimal.valueOf(5),res, score,
                    lose, SqlConstant.PHONENUM);

        }
    }
}
