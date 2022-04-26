package com.client.aerpaymerchant.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.horizontalChart.model.BarItem;
import com.client.aerpaymerchant.model.stats.Msg;
import com.client.aerpaymerchant.model.stats.OrderStats;
import com.client.aerpaymerchant.model.stats.TopProduct;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInsightActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mMenuLl)
    LinearLayout mMenuLl;

    @BindView(R.id.mDateLl)
    LinearLayout mDateLl;

    BottomSheetDialog dialog;
    private int mYear, mMonth, mDay;
    private Calendar selectedCal;
    private String productId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_insight);
        ButterKnife.bind(this);

        productId = getIntent().getStringExtra("productId");

        setDaysMenu();

        mMenuLl.setOnClickListener(v -> dialog.show());

        mDateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateDialog();
            }
        });

        getStatsDetail();
    }


    private void setDaysMenu() {
        View view = getLayoutInflater().inflate(R.layout.menu_days_layout, null);

        dialog = new BottomSheetDialog(ProductInsightActivity.this);
        dialog.setContentView(view);

    }

    private void startDateDialog() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(ProductInsightActivity.this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
            String fmonth, fDate;
            int month;

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                try {
                    if (monthOfYear < 10 && dayOfMonth < 10) {

                        fmonth = "0" + monthOfYear;
                        month = Integer.parseInt(fmonth) + 1;
                        fDate = "0" + dayOfMonth;
                        String paddedMonth = String.format("%02d", month);
                        //mDobEt.setText(fDate + "/" + paddedMonth + "/" + year);

                    } else {

                        fmonth = "0" + monthOfYear;
                        month = Integer.parseInt(fmonth) + 1;
                        String paddedMonth = String.format("%02d", month);
                        //mDobEt.setText(dayOfMonth + "/" + paddedMonth + "/" + year);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                selectedCal = Calendar.getInstance();
                selectedCal.set(Calendar.YEAR, year);
                selectedCal.set(Calendar.MONTH, monthOfYear);
                selectedCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.show();
    }

    private void getStatsDetail() {
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("productId", productId);
        object.addProperty("category", "");
        object.addProperty("fromdate", "");
        object.addProperty("todate", "");


        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.STORE_STATS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
/*
                        try {
                            OrderStats resModel = new Gson().fromJson(response, OrderStats.class);
                            if (resModel.getMsg() != null) {
                                Msg msg = resModel.getMsg();
                                item = new ArrayList<BarItem>();
                                List<TopProduct> topProductList = new ArrayList<TopProduct>();
                                topProductList = msg.getTopProducts();
                                if (topProductList!= null && topProductList.size() > 0){
                                    for (int i=0; i<topProductList.size();i++){
                                        item.add(new BarItem(topProductList.get(i).getProductName(), topProductList.get(i).getTotalOrders().doubleValue(),getColor(R.color.colorAccent), Color.WHITE));
                                    }
                                    horizontal.init(StatsActivity.this).hasAnimation(true).addAll(item).build();
                                }else {
                                    showToast("No Product has been added!");
                                }

                                tvTotalPrice.setText(jsonObject.optString("total_cash"));
                                tvTotalOrder.setText(jsonObject.optString("total_orders"));

                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }*/
                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }

}