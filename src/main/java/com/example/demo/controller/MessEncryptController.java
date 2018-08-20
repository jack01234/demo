package com.example.demo.controller;

import com.example.demo.model.dto.MessEncryptResDTO;
import com.example.demo.utils.AesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 信息加密
 *
 * @author: yingmuhuadao
 * @date: Created in 16:24 2018/8/17
 */
@Slf4j
@Controller
@Api(value = "MessEncrypt", description = "信息加密")
public class MessEncryptController {

    @RequestMapping(value = "/encrypt",method = RequestMethod.GET)
    @ApiOperation(value = "信息加密",notes = "信息加密接口")
    @ResponseBody
    public MessEncryptResDTO encrypt(@RequestParam String message){
        log.info("call encrypt param：{}", message);

        String result = AesUtil.encrypt(message, "DEVICE-AES000002");

        log.info("success encrypt result：{}",message);
        MessEncryptResDTO res = new MessEncryptResDTO();
        res.setMessage(result);
        return res;
    }
}
