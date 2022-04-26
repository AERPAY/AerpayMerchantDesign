
package com.client.aerpaymerchant.model.stats;

import com.google.gson.annotations.SerializedName;


public class TopOrder {

    @SerializedName("CreatedTime")
    private String mCreatedTime;
    @SerializedName("OrderId")
    private Long mOrderId;
    @SerializedName("TotalPrice")
    private Long mTotalPrice;

    public String getCreatedTime() {
        return mCreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        mCreatedTime = createdTime;
    }

    public Long getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Long orderId) {
        mOrderId = orderId;
    }

    public Long getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        mTotalPrice = totalPrice;
    }

}
