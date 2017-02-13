package com.damon4u.mvc.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 15:48
 */
public class SubmitRequest extends Request {

    private long orderId;

    private String productName;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
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

    @Override
    public String toString() {
        return "SubmitRequest{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
