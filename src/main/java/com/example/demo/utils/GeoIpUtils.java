package com.example.demo.utils;

import com.example.demo.model.GeoLocation;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 10:49 2018/12/25
 */
@Slf4j
public class GeoIpUtils {

    private static final String URL = "http://ip.taobao.com/service/getIpInfo.php?ip=";
    private static DatabaseReader reader;

    private static DatabaseReader getReader(){
        try{
            if(reader == null){
                log.warn("打开ip数据库");
                File database =  new File("E:\\goeip解析\\GeoLite2-City_20181218\\GeoLite2-City.mmdb");
                reader = new DatabaseReader.Builder(database).build();
            }
            return reader;
        }catch(Exception e){
            return reader;
        }

    }

    /**
     * 根据ip获取国家对象,不存在则返回null
     * @param ip
     * @return
     */
    public static City getCountry(String ip){
        try{
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse city = getReader().city(ipAddress);
            City city1 = city.getCity();
            return city1;
        }catch(Exception e){
            return null;
        }
    }

    /**
     * 根据ip获取国家代码,不存在则返回null
     * @param ip
     * @return
     */
    public static String getCountryCode(String ip){
        City country = getCountry(ip);
        return country != null ? country.getName() : null;
    }

    /**
     * 根据ip获取国家名称,不存在则返回null
     * @param ip
     * @return
     */
    public static String getCountryName(String ip){
        City country = getCountry(ip);
        return country != null ? country.getName() : null;
    }

    public static String getIpInfo(String ip){
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity(URL.concat(ip), String.class);
        return forEntity.getBody();
    }

    /**
     * 获取ip地址映射的国家
     * @param ipAddress
     * @return
     */
    private static GeoLocation getLocationV2(String ipAddress) {
        GeoLocation geoLocation = null;
        try {
            geoLocation = new GeoLocation();
            InetAddress ipAdd = InetAddress.getByName(ipAddress);
            CityResponse response = getReader().city(ipAdd);


            Country country = response.getCountry();
            geoLocation.setCountryCode(country.getIsoCode());
            geoLocation.setCountryName(country.getName());


            Subdivision subdivision = response.getMostSpecificSubdivision();
            geoLocation.setRegionName(subdivision.getName());


            City city = response.getCity();
            geoLocation.setCity(city.getName());
            geoLocation.setPostalCode(response.getPostal().getCode());
            geoLocation.setLatitude(String.valueOf(response.getLocation().getLatitude()));
            geoLocation.setLongitude(String.valueOf(response.getLocation().getLongitude()));
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }

        return geoLocation;
    }
    public static void main(String[] args) {
//        String ipInfo = getIpInfo("222.221.141.174");
//        log.info("result {}",ipInfo);
        City country = getCountry("222.221.141.174");
        log.info("result {}",country);
        GeoLocation locationV2 = getLocationV2("222.221.141.174");
        log.info("result {}",locationV2);
    }
}
