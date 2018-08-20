package com.example.demo.utils;

import com.example.demo.enums.FlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * ip地址获取
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Slf4j
public class IpAddrUtil {
    /**
     * 常量字符
     */
    private static final String UN_KNOWN = "unKnown";
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String X_REAL_IP = "X-Real-IP";
    private static final String HTTP_VIA = "Http_Via";
    /**
     * 从Request对象中获得客户端IP，处理了HTTP代理服务器和Nginx的反向代理截取了ip
     * @param request
     * @return ip
     */
    public static String getLocalIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader(X_FORWARDED_FOR);
        String realIp = request.getHeader(X_REAL_IP);

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

    /**
     * 判断是否代理
     * @param request 请求参数
     * @return 查询结果
     */
    public static String isAgent(HttpServletRequest request){
        //proxy ip
        String remoteAddr = request.getRemoteAddr();
        log.info(">>>>>>>>>>>>>> remoteAddr = {} <<<<<<<<<<<<<<<<",remoteAddr);
        String forwarded = request.getHeader(X_FORWARDED_FOR);
        log.info(">>>>>>>>>>>>>> forwarded = {} <<<<<<<<<<<<<<<<",forwarded);
        String proxyIp = request.getHeader(HTTP_VIA);
        log.info(">>>>>>>>>>>>>> Http_Via = {} <<<<<<<<<<<<<<<<",proxyIp);
        String realIp = request.getHeader(X_REAL_IP);
        log.info(">>>>>>>>>>>>>> X-Real-IP = {} <<<<<<<<<<<<<<<<",realIp);
        if (StringUtils.isEmpty(forwarded)) {
            return isHighAgent(request);
        } else {
            //first is real ip
            String[] ips = forwarded.split(",");
            log.info(">>>>>>>>>>>>>>>> ips = {} <<<<<<<<<<<<<<<<<",ips);
            if (ips.length > 1) {
                //proxy ip such as : 203.98.182.163, 203.98.182.163, 203.129.72.215
                //first is real ip,last is last proxy ip
                //is real ip equals proxy ip
                return FlagEnum.TRUE.getKey();
            }
            if (!remoteAddr.equals(ips[0])) {
                return FlagEnum.TRUE.getKey();
            }
            return FlagEnum.FALSE.getKey();
        }
    }

    /**
     * 判断是否高匿名
     * @param request 请求参数
     * @return 结果
     */
    private static String isHighAgent(HttpServletRequest request){
        //判断是否是高匿名
        String userAgent = request.getHeader("User-Agent");
        log.info(">>>>>>>>>>>>>> User-Agent = {} <<<<<<<<<<<<<<<<",userAgent);
        String referer = request.getHeader("Referer");
        log.info(">>>>>>>>>>>>>> Referer = {} <<<<<<<<<<<<<<<<",referer);
        Cookie[] cookies = request.getCookies();
        log.info(">>>>>>>>>>>>>> cookies = {} <<<<<<<<<<<<<<<<",cookies);
        boolean flag = (cookies == null || cookies.length<=0) && StringUtils.isEmpty(userAgent) &&
                StringUtils.isEmpty(referer);
        if (flag) {
            return FlagEnum.TRUE.getCode();
        }
        return FlagEnum.FALSE.getCode();
    }

    /**
     *
     * @param request
     * @return
     */

    public static String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader(X_FORWARDED_FOR);
        String realIp = request.getHeader(X_REAL_IP);
        String ip;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = forwarded.split(",")[0];
            }
        } else {
            ip = realIp;
        }
        return ip;
    }

    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader(X_FORWARDED_FOR);
        if(StringUtils.isNotEmpty(ip) && !UN_KNOWN.equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(',');
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader(X_REAL_IP);
        if(StringUtils.isNotEmpty(ip) && !UN_KNOWN.equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 通过HttpServletRequest返回IP地址
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader(X_FORWARDED_FOR);
        log.info(">>>>>>>>>>>>>> Proxy-Client-IP = {} <<<<<<<<<<<<<<<",request.getHeader("Proxy-Client-IP"));
        log.info(">>>>>>>>>>>>>> WL-Proxy-Client-IP = {} <<<<<<<<<<<<<<<",request.getHeader("WL-Proxy-Client-IP"));
        log.info(">>>>>>>>>>>>>> HTTP_CLIENT_IP = {} <<<<<<<<<<<<<<<",request.getHeader("HTTP_CLIENT_IP"));
        log.info(">>>>>>>>>>>>>> HTTP_X_FORWARDED_FOR = {} <<<<<<<<<<<<<<<",request.getHeader("HTTP_X_FORWARDED_FOR"));
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UN_KNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }



    /**
     * 通过IP地址获取MAC地址
     * @param ip String,127.0.0.1格式
     * @return mac String
     * @throws Exception
     */
    public String getMACAddress(String ip) throws Exception {
        String line = "";
        String macAddress = "";
        final String macAddressPrefix = "MAC Address = ";
        final String loopbackAddress = "127.0.0.1";
        //如果为127.0.0.1,则获取本地MAC地址。
        if (loopbackAddress.equals(ip)) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            //貌似此方法需要JDK1.6。
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            //下面代码是把mac地址拼装成String
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //mac[i] & 0xFF 是为了把byte转化为正整数
                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);
            }
            //把字符串所有小写字母改为大写成为正规的mac地址并返回
            macAddress = sb.toString().trim().toUpperCase();
            return macAddress;
        }
        //获取非本地IP的MAC地址
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line != null) {
                    int index = line.indexOf(macAddressPrefix);
                    if (index != -1) {
                        macAddress = line.substring(index + macAddressPrefix.length()).trim().toUpperCase();
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            log.error("getMACAddress error {}",e);
        }
        return macAddress;
    }

}
