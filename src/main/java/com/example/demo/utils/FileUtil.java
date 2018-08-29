package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.PcDeviceNewInfoDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * 文件操作工具类
 *
 * @author: yingmuhuadao
 * @date: Created in 9:56 2018/8/29
 */
@Slf4j
public class FileUtil {

    /**
     * 文件读取
     *
     * @param path 文件路径
     *
     */
    public static List<String> readTxtFile(String path){

        InputStreamReader isr = null;
        BufferedReader br = null;
        List list = new ArrayList();
        try {
            File file = new File(path);

            if (file.isFile() && file.exists()){
                isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
                br = new BufferedReader(isr);
                String lineTxt;
                while ((lineTxt = br.readLine()) != null){

                    System.out.println(lineTxt);
                    list.add(lineTxt);

                }

            } else {
              log.warn("文件不存在或者格式不正确");
            }
        } catch (Exception e) {
            log.error("读取文件发生异常,{}",e);
        }finally {
            close(isr, br);
        }
        return list;
    }


    /**
     * 生产excel文件
     *
     */
    public static void writeExcelFile(List<String> list){
        try {
            //1.创建wb对象
            XSSFWorkbook wb = new XSSFWorkbook();
            //2.整理元数据
            List<PcDeviceNewInfoDO> lists = new ArrayList<>();
            list.forEach(result->lists.add(JSONObject.parseObject(result, PcDeviceNewInfoDO.class)));
            //3.创建sheet
            createSheet(wb,lists,"PC采集数据分析");
            //4.生成excel文件
            String filePath = "E://设备指纹相关//导出数据//"+System.currentTimeMillis()+"-PC采集数据分析";
            exportExcel(wb,filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 创建sheet
     *
     * @param wb         对象
     *
     *
     * @param list       data
     * @param sheetTitle sheet title
     * @throws Exception 异常信息
     */
    public static void createSheet(XSSFWorkbook wb, List<?> list, String sheetTitle) throws Exception {

        LinkedHashMap map = getFileHeadInfo();
        List<Map> exportData = mergeData(list);
        // 生成一个样式
        XSSFCellStyle style = wb.createCellStyle();
        // 居中
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 设置背景色
        style.setFillForegroundColor(HSSFColor.LIME.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //设置字体
        XSSFFont font = wb.createFont();
        font.setFontName("黑体");
        style.setFont(font);
        Sheet sheetAt = wb.createSheet(sheetTitle);
        sheetAt.setDefaultRowHeight((short) (2 * 256));
        sheetAt.setDefaultColumnWidth(15);
        Row row = sheetAt.createRow(0);
        int i = 0;
        for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext(); ) {
            java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            cell.setCellValue((propertyEntry.getValue() != null) ? (String) propertyEntry.getValue() : "");
            i++;
        }
        int j = 0;
        for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
            Object myRow = iterator.next();
            row = sheetAt.createRow(j + 1);
            int k = 0;
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
                    .hasNext(); ) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                        .next();
                String str = BeanUtils.getProperty(myRow, (String) propertyEntry.getKey());
                String str1 = str == null ? "" : str;
                row.createCell(k).setCellValue(str1);
                k++;
            }
            j++;
            iterator.remove();
        }
    }


    /**
     * Excel导出
     *
     * @param wb       excel生成对象
     * @throws Exception 异常处理
     */
    public final static void exportExcel(XSSFWorkbook wb, String filePath) throws Exception {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new File(filePath + ".xls"));
            wb.write(out);
            log.info(">>>>>>>>>>导出成功<<<<<<<<<<");
        } catch (Exception e) {
            log.error("导出异常:{} {}", e.getMessage(), e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * 为导出文件准备文件头
     *
     * @return LinkedHashMap
     */
    public static LinkedHashMap getFileHeadInfo() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1","字符编码(charset)");
        map.put("2","哈希校验和(hashCode)");
        map.put("3","mime 对象列表(mimeTypes)");
        map.put("4","内网IP(localIP)");
        map.put("5","屏幕信息(srcScreeSize)");
        map.put("6","用户代理(userAgent)");
        map.put("7","帆布指纹(canvasId)");
        map.put("8","3D绘图(webglVendor)");
        map.put("9","3D绘图引擎(webglRenderer)");
        map.put("10","插件(plugins)");
        map.put("11","屏幕尺寸(resolution)");
        map.put("12","字体(jsFonts)");
        map.put("13","会话缓存(sessionStorage)");
        map.put("14","本地缓存(localStorage)");
        map.put("15","本地数据库(hashIndexedDB)");
        map.put("16","操作系统(cpuClass)");
        map.put("17","平台(platform)");
        map.put("18","时区(timeZone)");
        map.put("19","安装的插件(adBlock)");
        map.put("20","语言(language)");
        map.put("21","设备内存(deviceMemory)");
        map.put("22","触摸设备信息(touchSupport)");
        map.put("23","像素比(pixelRatio)");
        map.put("24","sdk版本(sdkVer)");
        map.put("25","操作系统版本(sysVer)");
        map.put("26","调色板的比特深度(colorDepth)");
        map.put("27","伪装操作系统(pretendSystem)");
        map.put("28","伪装分辨率(pretendResolution)");
        map.put("29","伪装浏览器(pretendBrowser)");
        map.put("30","浏览器名称(browserName)");
        map.put("31","浏览器版本(browserVer)");
        map.put("32","网络类型(networkType)");
        map.put("33","设备类型(deviceSys)");
        map.put("34","桌面端还是移动端(wapWeb)");
        map.put("35","flash版本(flashVersion)");
        map.put("36","cpu核数(hardwareConcurrencyKey)");
        return map;
    }


    /**
     * 数据转换
     *
     * @param lists 集合对象
     * @return 返回结果
     */
    public static List<Map> mergeData(List<?> lists) {
        List<Map> list = new ArrayList<>();
        lists.forEach(data->{
            PcDeviceNewInfoDO req = (PcDeviceNewInfoDO) data;
            LinkedHashMap map = new LinkedHashMap();
            map.put("1",req.getCharset());
            map.put("2",req.getHashCode());
            map.put("3",req.getMimeTypes());
            map.put("4",req.getLocalIP());
            map.put("5",req.getSrcScreeSize());
            map.put("6",req.getUserAgent());
            map.put("7",req.getCanvasId());
            map.put("8",req.getWebglVendor());
            map.put("9",req.getWebglRenderer());
            map.put("10",req.getPlugins());
            map.put("11",req.getResolution());
            map.put("12",req.getJsFonts());
            map.put("13",req.getSessionStorage());
            map.put("14",req.getLocalStorage());
            map.put("15",req.getHashIndexedDB());
            map.put("16",req.getCpuClass());
            map.put("17",req.getPlatform());
            map.put("18",req.getTimeZone());
            map.put("19",req.getAdBlock());
            map.put("20",req.getLanguage());
            map.put("21",req.getDeviceMemory());
            map.put("22",req.getTouchSupport());
            map.put("23",req.getPixelRatio());
            map.put("24",req.getSdkVer());
            map.put("25",req.getSysVer());
            map.put("26",req.getColorDepth());
            map.put("27",req.getPretendSystem());
            map.put("28",req.getPretendResolution());
            map.put("29",req.getPretendBrowser());
            map.put("30",req.getBrowserName());
            map.put("31",req.getBrowserVer());
            map.put("32",req.getNetworkType());
            map.put("33",req.getDeviceSys());
            map.put("34",req.getWapWeb());
            map.put("35",req.getFlashVersion());
            map.put("36",req.getHardwareConcurrencyKey());
            list.add(map);
        });
        return list;
    }


    /**
     *
     * 关闭输出流
     */
    public static void close(InputStreamReader isr, BufferedReader br){
        try {
            if (null != isr){
                isr.close();
            }
            if (null != br){
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        writeExcelFile(readTxtFile("E://设备指纹相关/test.txt"));
    }
}
