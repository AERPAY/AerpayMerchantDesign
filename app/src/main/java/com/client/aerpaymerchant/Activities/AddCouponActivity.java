package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.coupon.Coupons;
import com.client.aerpaymerchant.model.coupon.StoreCoupon;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCouponActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edt_coupon_code)
    EditText coupon_code;

    @BindView(R.id.edt_userPerCustomer)
    EditText userPerCustomer;

    @BindView(R.id.edt_discountAmount)
    EditText discountAmount;

    @BindView(R.id.edt_minOrderAmount)
    EditText minOrderAmount;

    @BindView(R.id.mAddCouponBtn)
    Button btnAddCoupon;

   /* @BindView(R.id.mSelectBtn)
    Button mSelectBtn;

    @BindView(R.id.mAddCouponBtn)
    Button mAddCouponBtn;*/

    @BindView(R.id.tvDeleteCoupon)
    TextView mTextDeleteCoupon;

    private String couponId;
    private String couponAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);
        ButterKnife.bind(this);

        couponId = getIntent().getStringExtra("couponId");
        couponAction = getIntent().getStringExtra("couponAction");

        if (couponAction.equals("Update")){
            btnAddCoupon.setText("Update");
            mTextDeleteCoupon.setVisibility(View.VISIBLE);
            getCouponsDetail();
        }else {
            btnAddCoupon.setText("Add");
            mTextDeleteCoupon.setVisibility(View.GONE);

        }
    }



    @OnClick({R.id.mSelectBtn, R.id.mAddCouponBtn, R.id.tvDeleteCoupon})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mSelectBtn:
                startActivity(new Intent(AddCouponActivity.this,StoreProductsActivity.class));
            case R.id.mAddCouponBtn:
                addOrUpdateCoupon();
            case R.id.tvDeleteCoupon:
                deleteCoupon();
        }
    }

    private void getCouponsDetail() {
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("coupon_id", couponId);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.SHOW_COUPONS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();

                        try {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("StoreCoupon");
                          //  Coupons resModel = new Gson().fromJson(response, Coupons.class);
                            if (jsonObject1 != null) {
                                showCouponDetailOnUi(jsonObject1);
                            }
                        } catch (JsonSyntaxException | JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }


    private void deleteCoupon() {
        JsonObject object = new JsonObject();

        object.addProperty("coupon_id", couponId);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.DELETE_COUPON)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                        Toast.makeText(AddCouponActivity.this, message, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();

                        try {
                            if (statusCode == 200) {
                                Toast.makeText(AddCouponActivity.this, "Coupon delete successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }

    private void addOrUpdateCoupon()  {
        try {
            JSONObject object = new JSONObject();
            String couponCode = coupon_code.getText().toString();
            object.put("store_id", 1);
            object.put("coupon_code",couponCode);
            object.put("user_per_cust", userPerCustomer.getText().toString());
            object.put("dis_type", "flat");
            object.put("dis_amount", discountAmount.getText().toString());
            object.put("min_order", minOrderAmount.getText().toString());
            object.put("category_type", "");
            object.put("product_type", "");
            object.put("isActive", "");
            object.put("id", 1);

            new NetworkCall(this)
                    .setRequestObject(object)
                    .setEndPoint(APIEndPoints.ADD_COUPON)
                    .setResponseListener(new RetrofitResponseListener() {
                        @Override
                        public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                            hideProgressDialog();
                            Toast.makeText(AddCouponActivity.this, message, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                            hideProgressDialog();

                            try {
                                Coupons resModel = new Gson().fromJson(response, Coupons.class);
                                if (resModel.getCode() == 200){
                                    Toast.makeText(AddCouponActivity.this, "Coupon Updated Successfully!", Toast.LENGTH_SHORT).show();

                                }

                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onPreExecute() {
                            //showProgressDialog();
                        }


                    }).makeCall();
        }catch (JSONException je){

        }



    }

    private void showCouponDetailOnUi(JSONObject storeCoupon){
        coupon_code.setText(storeCoupon.optString("coupon_code"));
        userPerCustomer.setText(storeCoupon.optString("uses_per_cust"));
        discountAmount.setText(storeCoupon.optString("dis_amount"));
        minOrderAmount.setText(storeCoupon.optString("min_order"));
    }
}