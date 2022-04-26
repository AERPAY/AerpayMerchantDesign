
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;


public class DeliveryAddress {

    @SerializedName("apartment")
    private Object mApartment;
    @SerializedName("city")
    private Object mCity;
    @SerializedName("country")
    private Country mCountry;
    @SerializedName("created")
    private Object mCreated;
    @SerializedName("default")
    private Object mDefault;
    @SerializedName("id")
    private Object mId;
    @SerializedName("instructions")
    private Object mInstructions;
    @SerializedName("lat")
    private Object mLat;
    @SerializedName("long")
    private Object mLong;
    @SerializedName("state")
    private Object mState;
    @SerializedName("street")
    private Object mStreet;
    @SerializedName("user_id")
    private Object mUserId;
    @SerializedName("zip")
    private Object mZip;

    public Object getApartment() {
        return mApartment;
    }

    public void setApartment(Object apartment) {
        mApartment = apartment;
    }

    public Object getCity() {
        return mCity;
    }

    public void setCity(Object city) {
        mCity = city;
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        mCountry = country;
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

    public Object getInstructions() {
        return mInstructions;
    }

    public void setInstructions(Object instructions) {
        mInstructions = instructions;
    }

    public Object getLat() {
        return mLat;
    }

    public void setLat(Object lat) {
        mLat = lat;
    }

    public Object getLong() {
        return mLong;
    }


    public Object getState() {
        return mState;
    }

    public void setState(Object state) {
        mState = state;
    }

    public Object getStreet() {
        return mStreet;
    }

    public void setStreet(Object street) {
        mStreet = street;
    }

    public Object getUserId() {
        return mUserId;
    }

    public void setUserId(Object userId) {
        mUserId = userId;
    }

    public Object getZip() {
        return mZip;
    }

    public void setZip(Object zip) {
        mZip = zip;
    }

}
