
package com.client.aerpaymerchant.model.stats;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("TopOrders")
    private List<TopOrder> mTopOrders;
    @SerializedName("TopProducts")
    private List<TopProduct> mTopProducts;
    @SerializedName("TotalCash")
    private Long mTotalCash;
    @SerializedName("TotalOrders")
    private Long mTotalOrders;

    public List<TopOrder> getTopOrders() {
        return mTopOrders;
    }

    public void setTopOrders(List<TopOrder> topOrders) {
        mTopOrders = topOrders;
    }

    public List<TopProduct> getTopProducts() {
        return mTopProducts;
    }

    public void setTopProducts(List<TopProduct> topProducts) {
        mTopProducts = topProducts;
    }

    public Long getTotalCash() {
        return mTotalCash;
    }

    public void setTotalCash(Long totalCash) {
        mTotalCash = totalCash;
    }

    public Long getTotalOrders() {
        return mTotalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        mTotalOrders = totalOrders;
    }

}
