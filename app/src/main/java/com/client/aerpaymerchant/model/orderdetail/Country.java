
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;


public class Country {

    @SerializedName("active")
    private String mActive;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("country_code")
    private String mCountryCode;
    @SerializedName("currency_code")
    private String mCurrencyCode;
    @SerializedName("currency_symbol")
    private String mCurrencySymbol;
    @SerializedName("id")
    private String mId;
    @SerializedName("iso")
    private String mIso;
    @SerializedName("iso3")
    private String mIso3;
    @SerializedName("name")
    private String mName;
    @SerializedName("numcode")
    private String mNumcode;

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public String getCurrencyCode() {
        return mCurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        mCurrencyCode = currencyCode;
    }

    public String getCurrencySymbol() {
        return mCurrencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        mCurrencySymbol = currencySymbol;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getIso() {
        return mIso;
    }

    public void setIso(String iso) {
        mIso = iso;
    }

    public String getIso3() {
        return mIso3;
    }

    public void setIso3(String iso3) {
        mIso3 = iso3;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNumcode() {
        return mNumcode;
    }

    public void setNumcode(String numcode) {
        mNumcode = numcode;
    }

}
