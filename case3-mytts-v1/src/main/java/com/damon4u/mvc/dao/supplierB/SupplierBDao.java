package com.damon4u.mvc.dao.supplierB;

import com.damon4u.mvc.bean.supplierB.SupplierBSubmitRequest;
import com.damon4u.mvc.bean.supplierB.SupplierBSubmitResult;
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
public class SupplierBDao {
    public static final Logger logger = LoggerFactory.getLogger(SupplierBDao.class);

    public SupplierBSubmitResult submit(SupplierBSubmitRequest request) {
        logger.info("supplierB submit:" + request);
        String resultString = doHttp(request);
        SupplierBSubmitResult result = new SupplierBSubmitResult();
        if ("OK".equals(resultString)) {
            result.setRet(true);
            result.setOrderId(request.getOrderId());
            result.setGoodsName(request.getGoodsName());
            result.setOrderTime(request.getOrderTime());
        } else {
            result.setRet(false);
        }

        return result;
    }

    private String doHttp(SupplierBSubmitRequest request) {
        return "OK";
    }
}
