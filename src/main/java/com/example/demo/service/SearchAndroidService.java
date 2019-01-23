package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.bo.AndroidDeviceInfoBO;
import com.example.demo.utils.ApiPostUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 14:57 2018/9/19
 */
@Slf4j
public class SearchAndroidService {
    private static String url = "https://10.0.21.74:8901/gateway/dfpas/radar/v1/deviceRadar";
    @Test
    public void searchTest(){
        AndroidDeviceInfoBO req = new AndroidDeviceInfoBO();
        req.setGivingXyid("11809031117023550370101");
        req.setImei("123");
        req.setImsi("456");
        req.setAndroidId("123456");
        req.setTotalSize("123456");
        req.setFcpu("456");
        req.setMac("123");
        req.setPhoneNum("18637002608");
        req.setDeviceName("123");
        req.setBios("123");
        req.setCpu("123");
        req.setScreenRes("123");
        req.setUnitType("123");
        req.setSysVer("123");
        req.setFontNum("123");
        req.setIsRoot("0");
        req.setSimulator("0");
        req.setSystemLan("123");
        req.setTimeZone("123");
        String s = JSONObject.toJSONString(req);
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> result = template.postForEntity("", s, String.class);
        String body = result.getBody();



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("member_id","8013932531");
        jsonObject.put("terminal_id","1811070000");
        jsonObject.put("encryption_type","RSA");
        jsonObject.put("data_type","json");
        jsonObject.put("data_content","621553e5e87205fbf2d340738ea0e3fe7d1a28a6af389acc8e4f7f10348701a5322cbc86f3fd466f842a468576f00c65ef7945bed038da8d745ff6ed9b6360727b7d30051cd062eb971d702b70f504df7f057c977bc84a0bace318af7dcdb45199c46ce6d707919626820d51d8949134d8ce22e9efb444f3a8699dfe4bbd715d754dc19f03dcfe6261c881f3bbad55a8184c68e8042228388aaadf295173bf6441515069ff025fa516dc3cc9e63c9c5836edbab68ca924d39cfb0b81296807733754fa0e9e4402be429748348c64520d36a3bf0e64f1222788deb7616351585a6d00bd6fa07bbe1688f4c74904fe4e1abe3d7a76221dd902182ff6aa234194ff3f68ea13844c2c7f3e26b698e983468b4d789d2c6166551b82869b037d566e94d36aeb7302820560bb7a7ef546b63df3c8fbcc23547f7356dfa60652b629eab2ead3f08430676cee438b945e24ac7ec2f428b1540e24622e49072268ebd8e90a8ed1bab94a141c74a1b889e7ee84fb076f760937d8b9cd7c27c8dc71fca24cdd");
        try {
            ApiPostUtil postUtil = new ApiPostUtil(url,10000,10000);
            String responseData = postUtil.sendHttps(jsonObject.toJSONString(),"POST","UTF-8");
            System.out.println(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
