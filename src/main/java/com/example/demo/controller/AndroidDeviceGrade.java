package com.example.demo.controller;

import com.example.demo.convert.DeviceSearchConverter;
import com.example.demo.model.AndroidDeviceInfoDO;
import com.example.demo.model.bo.AndroidDeviceInfoBO;
import com.example.demo.model.dto.DeviceGradeDTO;
import com.example.demo.model.dto.DeviceGradeResDTO;
import com.example.demo.utils.AesUtil;
import com.example.demo.utils.CommonUtil;
import com.example.demo.utils.DeviceGradeUtil;
import com.example.demo.utils.MessageParseUtil;
import com.system.ext.logback.util.TraceLogIdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 安卓设备算分
 *
 * @author: yingmuhuadao
 * @date: Created in 10:41 2018/8/20
 */
@Slf4j
@Controller
@RequestMapping(value = "/android", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "AndroidDeviceGrade", description = "安卓设备特征计分")
public class AndroidDeviceGrade {

    /**
     * 安卓设备评分
     *
     * @param req 请求参数
     * @return 响应结果
     */

    @RequestMapping(value = "/deviceGrade",method = RequestMethod.POST)
    @ApiOperation(value = "pc设备特征计分",notes = "pc设备特征计分接口")
    @ResponseBody
    public DeviceGradeResDTO deviceGrade(@RequestBody DeviceGradeDTO req){
        MDC.put("TRACE_LOG_ID",TraceLogIdUtil.createTraceLogId());

        DeviceGradeResDTO res = new DeviceGradeResDTO();

        log.info("call deviceGrade param:{}",req);

        try {
            //1.参数解析
            AndroidDeviceInfoBO sourceBo = MessageParseUtil.androidMessageHandle(AesUtil.decrypt(req.getSource(),
                    "DEVICE-AES000002"));
            log.info("call deviceGrade 处理前源参数：{}",sourceBo);

            AndroidDeviceInfoBO tarBo = MessageParseUtil.androidMessageHandle(AesUtil.decrypt(req.getTarget(),
                    "DEVICE-AES000002"));

            log.info("call deviceGrade 处理前目标参数：{}",tarBo);

            //2.参数处理
            sourceBo = DeviceSearchConverter.androidDtoConvertToBo(sourceBo);
            log.info("call pcDeviceGrade 处理后源参数：{}",sourceBo);

            tarBo = DeviceSearchConverter.androidDtoConvertToBo(tarBo);
            log.info("call pcDeviceGrade 处理后目标参数：{}",tarBo);

            AndroidDeviceInfoDO sourceDo = CommonUtil.toHash(sourceBo);

            AndroidDeviceInfoDO tarDo = CommonUtil.toHash(tarBo);

            //3.特征计分

            DeviceGradeUtil.androidGrade(sourceDo,tarDo,res);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;

    }
}
