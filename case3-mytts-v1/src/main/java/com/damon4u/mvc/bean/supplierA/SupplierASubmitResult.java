package com.damon4u.mvc.bean.supplierA;

import com.damon4u.mvc.bean.SubmitResult;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 16:01
 */
public class SupplierASubmitResult extends SubmitResult {

    /**
     * supplierA下单接口返回的订单号
     */
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
