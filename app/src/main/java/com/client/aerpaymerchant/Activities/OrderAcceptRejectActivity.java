package com.client.aerpaymerchant.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.ApiResponse;
import com.client.aerpaymerchant.model.OrdersResModel;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAcceptRejectActivity extends BaseActivity {


    @BindView(R.id.ll_accept)
    LinearLayout llAccept;

    @BindView(R.id.ll_decline)
    LinearLayout llDecline;

    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_accept_reject);
        ButterKnife.bind(this);

        orderId = getIntent().getStringExtra("orderId");

    }

    @OnClick({R.id.ll_accept,R.id.ll_decline})
     void onButtonClick(View view){
        switch (view.getId()){
            case R.id.ll_accept:{
                acceptOrDeclineOrders(true);
                finish();
                break;
            }
            case R.id.ll_decline:{
                acceptOrDeclineOrders(false);
                finish();
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void acceptOrDeclineOrders(boolean acceptStatus) {
        JsonObject object = new JsonObject();

        object.addProperty("order_id", orderId);
        object.addProperty("is_accepted", acceptStatus);


        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.STORE_ACCEPT_DECLINE_ORDER)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();

                        try {
                            ApiResponse resModel = new Gson().fromJson(response, ApiResponse.class);
                            if (resModel.getMsg() != null && resModel.getCode() == 200) {
                                showToast(resModel.getMsg());
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

}