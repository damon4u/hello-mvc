package com.damon4u.mvc.bean.supplierA;

import com.damon4u.mvc.bean.SubmitRequest;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:57
 */
public class SupplierASubmitRequest extends SubmitRequest {

    /**
     * supplierA下单接口的订单号
     */
    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "SupplierASubmitRequest{" +
                "orderNo='" + orderNo + '\'' +
                "orderId='" + getOrderId() + '\'' +
                "productName='" + getProductName() + '\'' +
                "orderTime='" + getOrderTime() + '\'' +
                '}';
    }
}
