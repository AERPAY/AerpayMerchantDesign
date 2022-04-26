
package com.client.aerpaymerchant.model.stats;

import com.google.gson.annotations.SerializedName;

public class TopProduct {

    @SerializedName("ProductName")
    private String mProductName;
    @SerializedName("TotalOrders")
    private Long mTotalOrders;

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public Long getTotalOrders() {
        return mTotalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        mTotalOrders = totalOrders;
    }

}
