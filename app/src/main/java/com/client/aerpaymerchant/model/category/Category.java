
package com.client.aerpaymerchant.model.category;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("active")
    private String mActive;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("featured")
    private String mFeatured;
    @SerializedName("id")
    private String mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("level")
    private String mLevel;
    @SerializedName("name")
    private String mName;
    @SerializedName("store_id")
    private String mStoreId;

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getFeatured() {
        return mFeatured;
    }

    public void setFeatured(String featured) {
        mFeatured = featured;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getLevel() {
        return mLevel;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }

}
