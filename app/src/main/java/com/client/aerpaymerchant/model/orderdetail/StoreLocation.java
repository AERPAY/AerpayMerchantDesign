
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;

public class StoreLocation {

    @SerializedName("city")
    private String mCity;
    @SerializedName("Country")
    private Country mCountry;
    @SerializedName("country_id")
    private String mCountryId;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("id")
    private String mId;
    @SerializedName("lat")
    private String mLat;
    @SerializedName("long")
    private String mLong;
    @SerializedName("state")
    private String mState;
    @SerializedName("store_id")
    private String mStoreId;
    @SerializedName("street")
    private String mStreet;
    @SerializedName("zip_code")
    private String mZipCode;

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        mCountry = country;
    }

    public String getCountryId() {
        return mCountryId;
    }

    public void setCountryId(String countryId) {
        mCountryId = countryId;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLat() {
        return mLat;
    }

    public void setLat(String lat) {
        mLat = lat;
    }


    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }

    public String getStreet() {
        return mStreet;
    }

    public void setStreet(String street) {
        mStreet = street;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

}
