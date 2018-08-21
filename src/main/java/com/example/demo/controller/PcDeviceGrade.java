package com.example.demo.controller;

import com.example.demo.convert.DeviceSearchConverter;
import com.example.demo.model.PcDeviceInfoDO;
import com.example.demo.model.bo.PcDeviceInfoBO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.DeviceGradeUtil;
import com.example.demo.utils.MessageParseUtil;
import com.example.demo.utils.ParamCheckUtil;
import com.system.ext.logback.util.TraceLogIdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

import static com.example.demo.common.CommonConstant.*;

/**
 * pc设备目标特征和源特征匹配算分
 *
 * @author: yingmuhuadao
 * @date: Created in 15:13 2018/8/17
 */
@Slf4j
@Controller
@RequestMapping(value = "/pc")
@Api(value = "PcDeviceGrade", description = "pc设备特征计分")
public class PcDeviceGrade {

    /**
     * pc 设备特征计分接口
     * @return 分数
     */

    @RequestMapping(value = "/deviceGrade",method = RequestMethod.GET)
    @ApiOperation(value = "pc设备特征计分",notes = "pc设备特征计分接口,source和target参数取自于search-pc项目请求参数")
    @ResponseBody
    public DeviceGradeResDTO deviceGrade(@RequestParam String source,@RequestParam String target){
        MDC.put("TRACE_LOG_ID",TraceLogIdUtil.createTraceLogId());

        DeviceGradeResDTO res = new DeviceGradeResDTO();

        log.info("call pcDeviceGrade param:{},{}",source,target);

        try {
            //1.参数解析
            PcDeviceInfoBO sourceBo = MessageParseUtil.messageHandle(source);
            log.info("call pcDeviceGrade 处理前源参数：{}",sourceBo);

            PcDeviceInfoBO tarBo = MessageParseUtil.messageHandle(target);

            log.info("call pcDeviceGrade 处理前目标参数：{}",tarBo);


            //2.参数处理
            sourceBo = DeviceSearchConverter.pcDtoConvertToBo(sourceBo);
            log.info("call pcDeviceGrade 处理后源参数：{}",sourceBo);


            tarBo = DeviceSearchConverter.pcDtoConvertToBo(tarBo);
            log.info("call pcDeviceGrade 处理后目标参数：{}",tarBo);

            PcDeviceInfoDO sourceDo = CommonUtil.pcToHash(sourceBo);

            //当前请求是否走IE8请求

            boolean ieFlag = ParamCheckUtil.paramsCheck(sourceBo);

            sourceDo.setFlag(CommonUtil.getFlag(sourceBo.getDeviceSys(),ieFlag));


            PcDeviceInfoDO tarDo = CommonUtil.pcToHash(tarBo);

            //3.特征计分

            BigDecimal divisor;
            if ("PC".equals(sourceBo.getDeviceSys())) {
                divisor = ieFlag?IE_PC_TOTAL_SCORE:PC_TOTAL_SCORE;
            } else {
                divisor = ieFlag?IE_CLIENT_TOTAL_SCORE:CLIENT_TOTAL_SCORE;
            }
            res.setTotalScore(divisor);

            DeviceGradeUtil.pcGrade(sourceDo, tarDo, res);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return res;
    }
}
