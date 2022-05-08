package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.OrderAdapter;
import com.client.aerpaymerchant.adapters.OrderDetailsAdapter;
import com.client.aerpaymerchant.model.OrderDetailsModel;
import com.client.aerpaymerchant.model.OrdersResModel;
import com.client.aerpaymerchant.model.orderdetail.OrderDetail;
import com.client.aerpaymerchant.model.orderdetail.OrderStoreProduct;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.mTrackOrderBtn)
    Button mTrackOrderBtn;

    @BindView(R.id.tvOrder_id)
    TextView tvOrderId;

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;

    @BindView(R.id.tvCustomerMobile)
    TextView tvCustomerMobile;

    @BindView(R.id.tvCustomerAddress)
    TextView tvCustomerAddress;

    @BindView(R.id.tvTotalOrderPrice)
    TextView tvOrderTotal;

    @BindView(R.id.rv_orderDetail)
    RecyclerView rvOrderDetail;

    String orderId;

    ArrayList<OrderStoreProduct> orderList;
    static OrderStoreProduct OrderStoreProduct;

    OrderDetailsAdapter orderDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);

        orderId = getIntent().getStringExtra("orderId");

        setupData();

        mTrackOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,TrackOrderActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderDetail(orderId);
    }


    private void setupData() {

    }


    private void getOrderDetail(String orderId){
        JsonObject object = new JsonObject();

        object.addProperty("order_id", 1);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.ORDER_DETAILS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try {
                            OrderDetail resModel = new Gson().fromJson(response, OrderDetail.class);
                            if (resModel.getCode() == 200 && resModel.getMsg() != null){
                                initData(resModel);
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

    private void initData(OrderDetail resModel) {
        tvOrderId.setText(resModel.getMsg().getOrder().getId());
        tvCustomerName.setText(resModel.getMsg().getUser().getFirstName() + " " + resModel.getMsg().getUser().getLastName());
        tvCustomerMobile.setText(resModel.getMsg().getUser().getPhone());
        tvOrderTotal.setText("Rs. " + resModel.getMsg().getOrder().getTotal());

        StringBuffer address = new StringBuffer();
        if (resModel.getMsg().getDeliveryAddress().getApartment()!= null){
            address.append(resModel.getMsg().getDeliveryAddress().getApartment());
        }else if (resModel.getMsg().getDeliveryAddress().getStreet() != null){
            address.append(resModel.getMsg().getDeliveryAddress().getStreet());
        }else if (resModel.getMsg().getDeliveryAddress().getCity()!= null){
            address.append(resModel.getMsg().getDeliveryAddress().getCity());
        }else if (resModel.getMsg().getDeliveryAddress().getState() != null){
            address.append(resModel.getMsg().getDeliveryAddress().getState());
        }else if (resModel.getMsg().getDeliveryAddress().getCountry() != null){
            address.append(resModel.getMsg().getDeliveryAddress().getCountry());
        }else if (resModel.getMsg().getDeliveryAddress().getZip() != null){
            address.append(resModel.getMsg().getDeliveryAddress().getZip());
        }

        tvCustomerAddress.setText(address);
        orderList = (ArrayList<OrderStoreProduct>) resModel.getMsg().getOrderStoreProduct();
        if (orderList.size() > 0){
            orderDetailsAdapter = new OrderDetailsAdapter(this,orderList);
            rvOrderDetail.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this));
            rvOrderDetail.setAdapter(orderDetailsAdapter);
            orderDetailsAdapter.notifyDataSetChanged();
        }else{
            showToast("Unable to fetch items for this product!");
        }



    }


}