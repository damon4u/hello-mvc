package com.damon4u.mvc.bean;

import java.util.Date;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:53
 */
public class SubmitResult extends Result {

    private long orderId;

    private String productName;

    private Date orderTime;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
