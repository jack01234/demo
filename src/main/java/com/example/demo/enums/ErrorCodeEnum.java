package com.example.demo.enums;

import com.system.commons.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码
 *
 * @author thank_wd
 * @version 1.0.0
 * @date 2018/4/2
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum implements ErrorCode {

    /*************** 参数校验错误 *******************/
    PARAM_NULL_IS_ILLEGAL("PARAM_NULL_IS_ILLEGAL", "参数为null是非法的"),
    PARAM_VALID_NOT_PASS("PARAM_VALID_NOT_PASS", "参数校验不通过"),
    PARAM_BIZ_TYPE_ERROR("PARAM_BIZ_TYPE_ERROR", "非法的业务类型"),
    PARAM_ENCRYPT_ERROR("PARAM_ENCRYPT_ERROR", "请求报文加密异常"),
    PARAM_DECRYPT_ERROR("PARAM_DECRYPT_ERROR", "请求报文解密异常"),
    PARAM_BIZ_CONTENT_TRANSFER_ERROR("PARAM_BIZ_CONTENT_TRANSFER_ERROR", "业务参数格式不正确"),
    PARAM_SMS_VALIDPROID_INVALID("PARAM_SMS_VALIDPROID_INVALID", "短信有效期不能为空"),
    PARAM_SMS_SMS_CODE_INVALID("PARAM_SMS_SMS_CODE_INVALID", "短信验证码不能为空"),
    PARAM_SMS_SMS_SIGN_INVALID("PARAM_SMS_SMS_SIGN_INVALID", "短信签名信息不能为空"),
    PARAM_MERCHANT_NO_NOT_EXIST("PARAM_MERCHANT_NO_NOT_EXIST", "商户号不存在,请确认商户号是否正确"),
    /*************** 业务层错误 *******************/
    BIZ_MD5_TO_XYID_ERROR("BIZ_MD5_TO_XYID_ERROR", "根据MD5 -> XYID异常"),
    BIZ_DEVICE_SEARCH_IS_NULL("BIZ_DEVICE_SEARCH_IS_NULL", "查询XYID为空"),
    BIZ_DEVICE_SEARCH_ERROR("BIZ_DEVICE_SEARCH_ERROR", "亲，设备检索系统资源繁忙，请稍后重试！"),
    BIZ_DEVICE_IP_PROXY_ERROR("BIZ_DEVICE_IP_PROXY_ERROR", "亲，IP代理系统资源繁忙，请稍后重试！"),
    BIZ_DEVICE_CORE_ERROR("BIZ_DEVICE_CORE_ERROR", "亲，设备核心系统资源繁忙，请稍后重试！"),
    BIZ_TOKEN_INVALID("BIZ_TOKEN_INVALID", "TOKEN无效或者已过期，建议重新采集设备信息！"),
    BIZ_LIMIT_QUANTITY_IS_MAX("BIZ_LIMIT_QUANTITY_IS_MAX","交易限制已经达到上限，停止提供服务"),
    BIZ_MERCHANT_BALANCE_OUT("BIZ_MERCHANT_BALANCE_OUT", "商户余额不足"),

    BIZ_SMS_QUERY_ERROR("BIZ_SMS_QUERY_ERROR", "短信验证码查询异常"),
    BIZ_SMS_ADD_ERROR("BIZ_SMS_ADD_ERROR", "新增短信订单异常"),
    BIZ_NOT_USE_SIMULATOR_DEVICE("BIZ_NOT_USE_SIMULATOR_DEVICE", "请勿使用模拟器！"),
    BIZ_XYID_SMS_OVER_LIMIT_ONE_DAY("BIZ_XYID_SMS_OVER_LIMIT_ONE_DAY", "风险用户（1天内设备使用大于等于30个手机号接收验证码）"),
    BIZ_PHONE_SMS_OVER_LIMIT_ONE_HOUR("BIZ_PHONE_SMS_OVER_LIMIT_ONE_HOUR", "风险用户（1小时内手机接收验证码次数大于等于30）"),
    BIZ_MERCHANT_AGREEMENT_IS_NOT_EXIST("BIZ_MERCHANT_AGREEMENT_IS_NOT_EXIST", "商户未签署该产品或渠道"),

    BIZ_CHECK_AGREEMENT_ERROR("BIZ_CHECK_AGREEMENT_ERROR", "查询商户协议错误"),
    BIZ_QUERY_AUTH_KEY_ERROR("BIZ_QUERY_AUTH_KEY_ERROR", "查询商户秘钥错误"),
    BIZ_SMS_SEND_ERROR("BIZ_SMS_SEND_ERROR", "短信验证码发送异常"),
    BIZ_CHARGING_ERROR("BIZ_CHARGING_ERROR", "计费失败"),

    /**
     * 调用会员服务失败
     */
    BIZ_CIF_INVOKE_FAIL("BIZ_CIF_INVOKE_FAIL", "调用会员服务失败"),

    /**
     * 调用会员服务超时
     */
    BIZ_CIF_INVOKE_TIMEOUT("BIZ_CIF_INVOKE_TIMEOUT", "调用会员服务超时"),

    /*************** 系统级别错误 *******************/
    SYSTEM_IS_BUSY_ENGINE("SYSTEM_IS_BUSY_ENGINE", "亲，系统繁忙，请稍后重试！"),


    /*************** 黑镜外部系统错误模式 *******************/
    S0001("S0001", "亲，新颜黑镜系统繁忙，请稍后再试！"),

    ;
    /**
     * 异常码
     */
    private String errorCode;

    /**
     * 异常描述
     */
    private String errorDesc;


    /**
     * @param errorCode 名称
     * @return 枚举类
     */
    public static ErrorCodeEnum explain(String errorCode) {

        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.getErrorCode().equals(errorCode)) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    @Override
    public String getErrorDesc() {
        return this.errorDesc;
    }

    @Override
    public void setErrorDesc(String errorMsg) {
        this.errorDesc = errorMsg;
    }
}
