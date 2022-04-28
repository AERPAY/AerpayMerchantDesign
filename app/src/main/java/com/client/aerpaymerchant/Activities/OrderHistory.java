package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.OrderAdapter;
import com.client.aerpaymerchant.model.OrderDetailsModel;
import com.client.aerpaymerchant.model.OrderModel;
import com.client.aerpaymerchant.model.OrdersResModel;
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
import butterknife.OnClick;

public class OrderHistory extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rvOrderHistory)
    RecyclerView rvOrderHistory;

    @BindView(R.id.edtOrderHistory)
    EditText edtSearchOrder;


    ArrayList<OrderDetailsModel> orderList;
    OrderAdapter orderAdapter;
    OrderModel model = new OrderModel();


    static OrderDetailsModel orderDetailsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        ButterKnife.bind(this);

        setupData();

        edtSearchOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getOrderHistory();
    }

    private void getOrderHistory(){
        JsonObject object = new JsonObject();

        object.addProperty("user_id", getUser().getId());

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.GET_ORDERS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try {
                            OrdersResModel resModel = new Gson().fromJson(response, OrdersResModel.class);
                            if (resModel.getMsg() != null) {
                                model = resModel.getMsg();
                                orderList = new ArrayList<>();
                                orderList.addAll(model.getPendingOrders());
                                orderList.addAll(model.getActive());
                                orderList.addAll(model.getCompletedOrders());
                                orderAdapter.setList(orderList);
                                orderAdapter.notifyDataSetChanged();
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

    private void setupData() {
        orderAdapter = new OrderAdapter(new ArrayList<>(), model -> {
            Intent intent;
            intent = new Intent(OrderHistory.this, OrderDetailsActivity.class).putExtra("orderId",model.getOrder().getId());
            orderDetailsModel = (model);
            startActivity(intent);
            return null;
        });
        rvOrderHistory.setAdapter(orderAdapter);
        orderAdapter.notifyDataSetChanged();
    }

    private void filter(String text) {
        ArrayList<OrderDetailsModel> filteredList = new ArrayList<>();

        for (OrderDetailsModel item : orderList) {
            if (item.getOrder().getId().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        orderAdapter.setList(filteredList);
        orderAdapter.notifyDataSetChanged();
    }
}