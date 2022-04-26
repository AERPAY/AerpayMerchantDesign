
package com.client.aerpaymerchant.model.orderdetail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Msg {

    @SerializedName("CouponUsed")
    private CouponUsed mCouponUsed;
    @SerializedName("DeliveryAddress")
    private DeliveryAddress mDeliveryAddress;
    @SerializedName("Order")
    private Order mOrder;
    @SerializedName("OrderStoreProduct")
    private List<OrderStoreProduct> mOrderStoreProduct;
    @SerializedName("Store")
    private Store mStore;
    @SerializedName("User")
    private User mUser;

    public CouponUsed getCouponUsed() {
        return mCouponUsed;
    }

    public void setCouponUsed(CouponUsed couponUsed) {
        mCouponUsed = couponUsed;
    }

    public DeliveryAddress getDeliveryAddress() {
        return mDeliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        mDeliveryAddress = deliveryAddress;
    }

    public Order getOrder() {
        return mOrder;
    }

    public void setOrder(Order order) {
        mOrder = order;
    }

    public List<OrderStoreProduct> getOrderStoreProduct() {
        return mOrderStoreProduct;
    }

    public void setOrderStoreProduct(List<OrderStoreProduct> orderStoreProduct) {
        mOrderStoreProduct = orderStoreProduct;
    }

    public Store getStore() {
        return mStore;
    }

    public void setStore(Store store) {
        mStore = store;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

}
