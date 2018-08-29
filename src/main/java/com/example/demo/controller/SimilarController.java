package com.example.demo.controller;

import com.example.demo.utils.LevenshteinUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 10:48 2018/8/29
 */
@Slf4j
@Controller
@Api(value = "SimilarController", description = "字符串相似度匹配")
public class SimilarController {

    @RequestMapping(value = "/similar",method = RequestMethod.GET)
    @ApiOperation(value = "相似度匹配",notes = "相似度匹配")
    @ResponseBody
    public BigDecimal similar(@RequestParam String source, @RequestParam String target){
        return LevenshteinUtil.getSimilarityRatio(source,target);
    }
}
