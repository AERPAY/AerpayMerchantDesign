
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("about")
    private String mAbout;
    @SerializedName("active")
    private String mActive;
    @SerializedName("category_id")
    private String mCategoryId;
    @SerializedName("cover")
    private String mCover;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("distance_unit")
    private String mDistanceUnit;
    @SerializedName("id")
    private String mId;
    @SerializedName("is_online")
    private Boolean mIsOnline;
    @SerializedName("logo")
    private String mLogo;
    @SerializedName("name")
    private String mName;
    @SerializedName("shipping_base_fee")
    private String mShippingBaseFee;
    @SerializedName("shipping_fee_per_distance")
    private String mShippingFeePerDistance;
    @SerializedName("StoreLocation")
    private StoreLocation mStoreLocation;
    @SerializedName("User")
    private User mUser;
    @SerializedName("user_id")
    private String mUserId;

    public String getAbout() {
        return mAbout;
    }

    public void setAbout(String about) {
        mAbout = about;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getCover() {
        return mCover;
    }

    public void setCover(String cover) {
        mCover = cover;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getDistanceUnit() {
        return mDistanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        mDistanceUnit = distanceUnit;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsOnline() {
        return mIsOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        mIsOnline = isOnline;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getShippingBaseFee() {
        return mShippingBaseFee;
    }

    public void setShippingBaseFee(String shippingBaseFee) {
        mShippingBaseFee = shippingBaseFee;
    }

    public String getShippingFeePerDistance() {
        return mShippingFeePerDistance;
    }

    public void setShippingFeePerDistance(String shippingFeePerDistance) {
        mShippingFeePerDistance = shippingFeePerDistance;
    }

    public StoreLocation getStoreLocation() {
        return mStoreLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        mStoreLocation = storeLocation;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
