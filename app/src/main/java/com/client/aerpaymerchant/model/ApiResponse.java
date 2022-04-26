
package com.client.aerpaymerchant.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("code")
    private Long mCode;
    @SerializedName("msg")
    private String mMsg;

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public String getMsg() {
        return mMsg;
    }

    public void setMsg(String msg) {
        mMsg = msg;
    }

}
