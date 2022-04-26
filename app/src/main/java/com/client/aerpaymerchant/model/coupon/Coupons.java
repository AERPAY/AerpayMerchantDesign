
package com.client.aerpaymerchant.model.coupon;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Coupons {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("msg")
    private List<Msg> mMsg;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public List<Msg> getMsg() {
        return mMsg;
    }

    public void setMsg(List<Msg> msg) {
        mMsg = msg;
    }

}
