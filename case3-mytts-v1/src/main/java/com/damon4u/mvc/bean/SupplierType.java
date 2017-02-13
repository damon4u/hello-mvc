package com.damon4u.mvc.bean;

/**
 * Description:
 *
 * @author damon4u
 * @version 2017-02-10 17:05
 */
public enum SupplierType {
    SupplierA(1001),
    SupplierB(1002);

    private int supplierId;

    SupplierType(int supplierId) {
        this.supplierId = supplierId;
    }

    public static SupplierType valueOf(int supplierId) {
        for (SupplierType value : SupplierType.values()) {
            if (value.getSupplierId() == supplierId) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid status supplierId:" + supplierId);
    }

    public int getSupplierId() {
        return supplierId;
    }

}
