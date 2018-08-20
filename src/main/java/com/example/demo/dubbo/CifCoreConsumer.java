package com.example.demo.dubbo;

import com.baofu.cif.product.facade.CustomerProdFacade;
import com.baofu.cif.product.facade.enums.KeyType;
import com.baofu.cif.product.facade.models.CustomerOrgResDto;
import com.baofu.cif.product.facade.models.account.AccountInfoDto;
import com.system.commons.result.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.slf4j.helpers.SystemMarker;
import org.springframework.stereotype.Component;

/**
 * pc 端 api
 *
 * @author: yingmuhuadao
 * @date: Created in 15:15 2018/8/14
 */
@Slf4j
@Component
public class CifCoreConsumer {

    @Reference
    CustomerProdFacade customerProdFacade;

    public void queryMerchantInfo(String merchantNo){
        AccountInfoDto req = new AccountInfoDto();
        req.setQueryKeyType(KeyType.ORGCODE.getCode());
        req.setQueryKeyValue(merchantNo);
        req.setCreatedBy(merchantNo);
        long startTime = System.currentTimeMillis();
        Result<CustomerOrgResDto> res = customerProdFacade.findOrgCustomerInfo(req, MDC.get(SystemMarker.TRACE_LOG_ID));
        log.info("success 耗时 {}, result {}",System.currentTimeMillis()-startTime,res);
    }

    public static void main(String[] args) {
        String fieldName = "11222222222.xlx";
        String suffix = fieldName.substring(fieldName.lastIndexOf("."), fieldName.length());
    }
}
