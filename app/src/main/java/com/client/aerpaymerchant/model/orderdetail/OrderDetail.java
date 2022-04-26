
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("msg")
    private Msg mMsg;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public Msg getMsg() {
        return mMsg;
    }

    public void setMsg(Msg msg) {
        mMsg = msg;
    }

}
