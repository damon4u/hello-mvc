package com.damon4u.mvc.bean.supplierB;

import com.damon4u.mvc.bean.SubmitRequest;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:59
 */
public class SupplierBSubmitRequest extends SubmitRequest {

    /**
     * supplierB下单接口的商品名称
     */
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "SupplierASubmitRequest{" +
                "goodsName='" + goodsName + '\'' +
                "orderId='" + getOrderId() + '\'' +
                "productName='" + getProductName() + '\'' +
                "orderTime='" + getOrderTime() + '\'' +
                '}';
    }
}
