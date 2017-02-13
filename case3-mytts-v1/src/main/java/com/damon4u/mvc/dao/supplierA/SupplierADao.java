package com.damon4u.mvc.dao.supplierA;

import com.damon4u.mvc.bean.supplierA.SupplierASubmitRequest;
import com.damon4u.mvc.bean.supplierA.SupplierASubmitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 16:09
 */
@Component
public class SupplierADao {
    public static final Logger logger = LoggerFactory.getLogger(SupplierADao.class);

    public SupplierASubmitResult submit(SupplierASubmitRequest request) {
        logger.info("SupplierA submit :" + request);
        String resultString = doHttp(request);
        SupplierASubmitResult result = new SupplierASubmitResult();
        if ("OK".equals(resultString)) {
            result.setRet(true);
            result.setOrderNo(request.getOrderNo());
            result.setProductName(request.getProductName());
            result.setOrderTime(request.getOrderTime());
        } else {
            result.setRet(false);
        }

        return result;
    }

    private String doHttp(SupplierASubmitRequest request) {
        return "OK";
    }
}
