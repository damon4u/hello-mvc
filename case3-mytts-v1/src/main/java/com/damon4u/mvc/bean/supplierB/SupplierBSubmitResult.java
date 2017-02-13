package com.damon4u.mvc.bean.supplierB;

import com.damon4u.mvc.bean.SubmitResult;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 16:02
 */
public class SupplierBSubmitResult extends SubmitResult {

    /**
     * supplierB下单接口返回的商品名称
     */
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
