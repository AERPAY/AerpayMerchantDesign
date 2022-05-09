
package com.client.aerpaymerchant.model.category;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("Category")
    private Category mCategory;
    @SerializedName("Product")
    private List<Object> mProduct;

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public List<Object> getProduct() {
        return mProduct;
    }

    public void setProduct(List<Object> product) {
        mProduct = product;
    }

}
