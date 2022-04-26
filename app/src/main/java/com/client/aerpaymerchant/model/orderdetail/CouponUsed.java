
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;

public class CouponUsed {

    @SerializedName("coupon_id")
    private Object mCouponId;
    @SerializedName("created")
    private Object mCreated;
    @SerializedName("id")
    private Object mId;
    @SerializedName("order_id")
    private Object mOrderId;
    @SerializedName("user_id")
    private Object mUserId;

    public Object getCouponId() {
        return mCouponId;
    }

    public void setCouponId(Object couponId) {
        mCouponId = couponId;
    }

    public Object getCreated() {
        return mCreated;
    }

    public void setCreated(Object created) {
        mCreated = created;
    }

    public Object getId() {
        return mId;
    }

    public void setId(Object id) {
        mId = id;
    }

    public Object getOrderId() {
        return mOrderId;
    }

    public void setOrderId(Object orderId) {
        mOrderId = orderId;
    }

    public Object getUserId() {
        return mUserId;
    }

    public void setUserId(Object userId) {
        mUserId = userId;
    }

}
