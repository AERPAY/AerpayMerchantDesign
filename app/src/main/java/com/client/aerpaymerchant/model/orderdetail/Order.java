
package com.client.aerpaymerchant.model.orderdetail;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("cart_random_id")
    private String mCartRandomId;
    @SerializedName("cod")
    private String mCod;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("delivery")
    private String mDelivery;
    @SerializedName("delivery_address_id")
    private String mDeliveryAddressId;
    @SerializedName("delivery_datetime")
    private String mDeliveryDatetime;
    @SerializedName("delivery_fee")
    private String mDeliveryFee;
    @SerializedName("device")
    private String mDevice;
    @SerializedName("discount")
    private String mDiscount;
    @SerializedName("id")
    private String mId;
    @SerializedName("instruction")
    private String mInstruction;
    @SerializedName("note")
    private String mNote;
    @SerializedName("payment_id")
    private String mPaymentId;
    @SerializedName("rider_tip")
    private String mRiderTip;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("store_id")
    private String mStoreId;
    @SerializedName("tax")
    private String mTax;
    @SerializedName("total")
    private String mTotal;
    @SerializedName("transaction_id")
    private String mTransactionId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("version")
    private String mVersion;

    public String getCartRandomId() {
        return mCartRandomId;
    }

    public void setCartRandomId(String cartRandomId) {
        mCartRandomId = cartRandomId;
    }

    public String getCod() {
        return mCod;
    }

    public void setCod(String cod) {
        mCod = cod;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getDelivery() {
        return mDelivery;
    }

    public void setDelivery(String delivery) {
        mDelivery = delivery;
    }

    public String getDeliveryAddressId() {
        return mDeliveryAddressId;
    }

    public void setDeliveryAddressId(String deliveryAddressId) {
        mDeliveryAddressId = deliveryAddressId;
    }

    public String getDeliveryDatetime() {
        return mDeliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        mDeliveryDatetime = deliveryDatetime;
    }

    public String getDeliveryFee() {
        return mDeliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        mDeliveryFee = deliveryFee;
    }

    public String getDevice() {
        return mDevice;
    }

    public void setDevice(String device) {
        mDevice = device;
    }

    public String getDiscount() {
        return mDiscount;
    }

    public void setDiscount(String discount) {
        mDiscount = discount;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getInstruction() {
        return mInstruction;
    }

    public void setInstruction(String instruction) {
        mInstruction = instruction;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getPaymentId() {
        return mPaymentId;
    }

    public void setPaymentId(String paymentId) {
        mPaymentId = paymentId;
    }

    public String getRiderTip() {
        return mRiderTip;
    }

    public void setRiderTip(String riderTip) {
        mRiderTip = riderTip;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStoreId() {
        return mStoreId;
    }

    public void setStoreId(String storeId) {
        mStoreId = storeId;
    }

    public String getTax() {
        return mTax;
    }

    public void setTax(String tax) {
        mTax = tax;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

    public String getTransactionId() {
        return mTransactionId;
    }

    public void setTransactionId(String transactionId) {
        mTransactionId = transactionId;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String version) {
        mVersion = version;
    }

}
