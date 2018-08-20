package com.example.demo.controller;

import com.example.demo.utils.BaiDuSignUtil;
import com.example.demo.utils.IpAddrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import static com.example.demo.utils.IpAddrUtil.getIp;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @description:
 * @version: 5.0
 * @date: Created in 15:31 2018/6/1
 */
@RestController
@RequestMapping(value ="hello")
@Slf4j
public class TestController {
    @RequestMapping(value = "v1/test",method = RequestMethod.GET)
    public String test(HttpServletRequest request){
        log.info(">>>>>>>>>>>>>>>>> demo test start<<<<<<<<<<<<<<<<<<<<<");
        String result;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            log.info(">>>>>>>>>>>>>>>>>>>> headName = {} , value = {} <<<<<<<<<<<<<<<<<<<<<<",name,value);
        }
        result = IpAddrUtil.isAgent(request);
        log.info(">>>>>>>>>>>>>>>>>>> isAgent result {} <<<<<<<<<<<<<<<<<<<<<<< ",result);
        String ip = getIp(request);
        log.info(">>>>>>>>>>>>>>>>>>> ip result {} <<<<<<<<<<<<<<<<<<<<<<< ",ip);
        return result;
    }

    public static void main(String[] args) {
        try {
            String ak = "ig6I4Bcyk6nNPfKfoy9us6z8Kh4hpNh2";

            String sk = "lGqiDlxM7ztc6UsKxrYG5cRDRtItvOIK";
            Map<String,String> map = new TreeMap<>();
            map.put("ip","222.141.207.0");
            map.put("ak",ak);
            map.put("coor","bd09ll");
            String sn = BaiDuSignUtil.toQueryString(map);
            String wholeStr = new String("/location/ip?" + sn+sk);
            // 对上面wholeStr再作utf8编码
            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
            String url = "http://api.map.baidu.com/location/ip?ip=222.141.207.0&ak="+ak+"&coor=bd09ll"+"&sn="+BaiDuSignUtil.MD5(tempStr);
            map.put("sn",BaiDuSignUtil.MD5(tempStr));
            SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
            httpRequestFactory.setReadTimeout(5000);
            httpRequestFactory.setConnectTimeout(5000);
            RestTemplate template = new RestTemplate(httpRequestFactory);
            template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            String body = template.postForEntity(url,map,String.class).getBody();
            String result = new String(body.getBytes("iso-8859-1"),"UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
