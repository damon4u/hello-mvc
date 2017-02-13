package com.damon4u.mvc.service;

import com.damon4u.mvc.bean.SubmitRequest;
import com.damon4u.mvc.bean.SubmitResult;
import com.damon4u.mvc.bean.SupplierType;
import com.damon4u.mvc.bean.supplierA.SupplierASubmitRequest;
import com.damon4u.mvc.bean.supplierA.SupplierASubmitResult;
import com.damon4u.mvc.bean.supplierB.SupplierBSubmitRequest;
import com.damon4u.mvc.bean.supplierB.SupplierBSubmitResult;
import com.damon4u.mvc.dao.supplierA.SupplierADao;
import com.damon4u.mvc.dao.supplierB.SupplierBDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 16:05
 */
@Service
public class TTSService {

    @Resource
    private SupplierADao supplierADao;

    @Resource
    private SupplierBDao supplierBDao;

    public SubmitResult submit(SubmitRequest submitRequest) {
        int supplierId = submitRequest.getSupplierId();
        switch (SupplierType.valueOf(supplierId)) {
            case SupplierA: {
                // 将通用的SubmitRequest请求参数转为SupplierASubmitRequest
                SupplierASubmitRequest request = new SupplierASubmitRequest();
                request.setSupplierId(submitRequest.getSupplierId());
                request.setOrderNo(String.valueOf(submitRequest.getOrderId()));
                request.setProductName(submitRequest.getProductName());
                request.setOrderTime(submitRequest.getOrderTime());

                SupplierASubmitResult result = supplierADao.submit(request);

                // 将SupplierASubmitResult转为统一的SubmitResult
                SubmitResult submitResult = new SubmitResult();
                submitResult.setRet(result.getRet());
                submitResult.setErrorCode(result.getErrorCode());
                submitResult.setErrorMsg(result.getErrorMsg());
                submitResult.setOrderId(Long.parseLong(result.getOrderNo()));
                submitResult.setProductName(result.getProductName());
                submitResult.setOrderTime(result.getOrderTime());
                return submitResult;
            }
            case SupplierB: {
                // 将通用的SubmitRequest请求参数转为SupplierBSubmitRequest
                SupplierBSubmitRequest request = new SupplierBSubmitRequest();
                request.setOrderId(submitRequest.getOrderId());
                request.setGoodsName(submitRequest.getProductName());
                request.setOrderTime(submitRequest.getOrderTime());

                SupplierBSubmitResult result = supplierBDao.submit(request);

                // 将SupplierBSubmitResult转为统一的SubmitResult
                SubmitResult submitResult = new SubmitResult();
                submitResult.setRet(result.getRet());
                submitResult.setErrorCode(result.getErrorCode());
                submitResult.setErrorMsg(result.getErrorMsg());
                submitResult.setOrderId(result.getOrderId());
                submitResult.setProductName(result.getGoodsName());
                submitResult.setOrderTime(result.getOrderTime());
                return submitResult;
            }
        }
        throw new IllegalArgumentException("illegal supplier type");
    }
}
