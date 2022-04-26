
package com.client.aerpaymerchant.model.coupon;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Msg {

    @SerializedName("StoreCoupon")
    private StoreCoupon mStoreCoupon;
    @SerializedName("StoreCouponUsed")
    private List<Object> mStoreCouponUsed;

    public StoreCoupon getStoreCoupon() {
        return mStoreCoupon;
    }

    public void setStoreCoupon(StoreCoupon storeCoupon) {
        mStoreCoupon = storeCoupon;
    }

    public List<Object> getStoreCouponUsed() {
        return mStoreCouponUsed;
    }

    public void setStoreCouponUsed(List<Object> storeCouponUsed) {
        mStoreCouponUsed = storeCouponUsed;
    }

}
