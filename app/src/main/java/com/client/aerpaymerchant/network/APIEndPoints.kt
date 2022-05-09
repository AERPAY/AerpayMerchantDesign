package com.client.aerpaymerchant.network

class APIEndPoints {


    companion object {

        const val REGISTER_MEMBER = "api/registerUser"
        const val SIGN_IN = "api/login"
        const val GET_ORDERS = "api/showActiveOrCompletedOrders"
        const val GET_PRODUCTS = "api/showAllProducts"
        const val ADD_PRODUCTS = "api/addProduct"
        const val DELETE_PRODUCTS = "api/deleteProduct"
        const val UPDATE_STORE_STATUS = "api/updateStoreStatus"
        const val STORE_ACCEPT_DECLINE_ORDER = "api/storeAcceptOrder"
        const val SHOW_COUPONS = "api/showCoupons"
        const val DELETE_COUPON = "api/deleteCoupon"
        const val ADD_COUPON = "api/AddCouponCode"
        const val STORE_STATS = "api/StoreSalesStats"
        const val ORDER_DETAILS = "api/showOrderDetail"
        const val ADD_CATEGORY = "api/addCategory"
        const val SHOW_CATEGORY = "api/showCategory"

    }
}