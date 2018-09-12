package com.example.demo.controller;

import com.example.demo.enums.ErrorCodeEnum;
import com.example.demo.mapper.TestMapper;
import com.example.demo.model.TestDO;
import com.example.demo.utils.CreateCookieUtil;
import com.example.demo.utils.IpAddrUtil;
import com.system.commons.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
@Slf4j
public class TestController {

    @Autowired(required = false)
    private TestMapper testMapper;

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

    @RequestMapping(value = "/getCookie",method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getCookie(){
        Result<String> result = new Result<>();
        TestDO testDO = new TestDO();
        String cookie = CreateCookieUtil.getCookie();
        testDO.setCookie(cookie);
        try {
            testMapper.insert(testDO);
            log.info("cookie {}",cookie);
            result.setResult(cookie);
        } catch (Exception e) {
            log.error("getCookie error {}", e);
            result.setErrorCode(ErrorCodeEnum.SYSTEM_IS_BUSY_ENGINE.getErrorCode());
            result.setErrorMsg(e.toString());
        }
        return result;
    }
}
