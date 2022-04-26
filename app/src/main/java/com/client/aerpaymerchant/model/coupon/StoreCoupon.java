
package com.client.aerpaymerchant.model.coupon;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class StoreCoupon {

    @SerializedName("category_type")
    private String mCategoryType;
    @SerializedName("coupon_code")
    private String mCouponCode;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("dis_amount")
    private String mDisAmount;
    @SerializedName("dis_type")
    private String mDisType;
    @SerializedName("id")
    private String mId;
    @SerializedName("isActive")
    private Boolean mIsActive;
    @SerializedName("min_order")
    private String mMinOrder;
    @SerializedName("product_type")
    private String mProductType;
    @SerializedName("store_id")
    private String mStoreId;
    @SerializedName("uses_per_cust")
    private String mUsesPerCust;

    public String getCategoryType() {
        return mCategoryType;
    }

    public void setCategoryType(String categoryType) {
        mCategoryType = categoryType;
    }

    public String getCouponCode() {
        return mCouponCode;
    }

    public void setCouponCode(String couponCode) {
        mCouponCode = couponCode;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getDisAmount() {
        return mDisAmount;
    }

    public void setDisAmount(String disAmount) {
        mDisAmount = disAmount;
    }

    public String getDisType() {
        return mDisType;
    }

    public void setDisType(String disType) {
        mDisType = disType;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getIsActive() {
        return mIsActive;
    }

    public void setIsActive(Boolean isActive) {
        mIsActive = isActive;
    }

    public String getMinOrder() {
        return mMinOrder;
    }

    public void setMinOrder(String minOrder) {
        mMinOrder = minOrder;
    }

    public String getProductType() {
        return mProductType;
    }

    public void setProductType(String productType) {
        mProductType = productType;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }

    public String getUsesPerCust() {
        return mUsesPerCust;
    }

    public void setUsesPerCust(String usesPerCust) {
        mUsesPerCust = usesPerCust;
    }

}
