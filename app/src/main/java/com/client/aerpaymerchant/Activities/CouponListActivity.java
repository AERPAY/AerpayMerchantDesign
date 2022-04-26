package com.client.aerpaymerchant.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.coupon.Coupons;
import com.client.aerpaymerchant.model.coupon.Msg;
import com.client.aerpaymerchant.model.coupon.StoreCoupon;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponListActivity extends BaseActivity {

    @BindView(R.id.mLlAddCoupon)
    LinearLayout mLlAddCoupon;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    CouponsAdapter couponsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_list);
        ButterKnife.bind(this);

        couponsAdapter = new CouponsAdapter(CouponListActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CouponListActivity.this));
        mRecyclerView.setAdapter(couponsAdapter);

        mLlAddCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CouponListActivity.this, AddCouponActivity.class).
                        putExtra("couponAction","Add").
                        putExtra("couponId",""));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCoupons();

    }

    private void getCoupons() {
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("counpon_id", "null");

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
                            Coupons resModel = new Gson().fromJson(response, Coupons.class);
                            if (resModel.getMsg() != null) {
                                couponsAdapter.setList(resModel.getMsg());
                                couponsAdapter.notifyDataSetChanged();
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



    //--------------------------------------Home Adapter-----------------------------------------
    public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.MyViewHolder> {

        Context context;
        List<Msg> childFeedList = new ArrayList<>();

        public CouponsAdapter(Context context) {
            this.context = context;

        }

        public void setList(List<Msg> couponList){
            childFeedList.clear();
            this.childFeedList = couponList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupon_item_design, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            StoreCoupon storeCoupon = childFeedList.get(position).getStoreCoupon();
            holder.txtCouponCode.setText(storeCoupon.getCouponCode().toString());
            holder.txtCouponDesc.setText(storeCoupon.getDisType() + " " + storeCoupon.getDisAmount() + " % discount on order on or above Rs." + storeCoupon.getMinOrder());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(CouponListActivity.this, AddCouponActivity.class).
                            putExtra("couponAction","Update").
                            putExtra("couponId",childFeedList.get(position).getStoreCoupon().getId()));
                }
            });
        }


        @Override
        public int getItemCount() {
            return childFeedList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtCouponCode;
            TextView txtCouponDesc;

            public MyViewHolder(View itemView) {
                super(itemView);
                txtCouponCode = (TextView) itemView.findViewById(R.id.tvCouponCode);
                txtCouponDesc = (TextView) itemView.findViewById(R.id.tvCouponDesc);

            }
        }
    }
}