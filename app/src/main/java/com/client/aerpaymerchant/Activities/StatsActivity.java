package com.client.aerpaymerchant.Activities;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.horizontalChart.HorizontalBar;
import com.client.aerpaymerchant.horizontalChart.model.BarItem;
import com.client.aerpaymerchant.horizontalChart.util.Util;
import com.client.aerpaymerchant.model.stats.Msg;
import com.client.aerpaymerchant.model.stats.OrderStats;
import com.client.aerpaymerchant.model.stats.TopProduct;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatsActivity extends BaseActivity {

    BottomSheetDialog dialog, categoryDialog;

    @BindView(R.id.mDaysLl)
    LinearLayout mDaysLl;

    @BindView(R.id.mCategoriesLl)
    LinearLayout mCategoriesLl;

    @BindView(R.id.mDateLl)
    LinearLayout mDateLl;

    @BindView(R.id.mDayEt)
    EditText mDayEt;

    @BindView(R.id.mCategoryEt)
    EditText mCategoryEt;

    private int mYear, mMonth, mDay;
    private Calendar selectedCal;

    @BindView(R.id.tv_stat_totalOrder)
    TextView tvTotalOrder;

    @BindView(R.id.tv_stat_totalCash)
    TextView tvTotalPrice;

    HorizontalBar horizontal;
    List<BarItem> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        ButterKnife.bind(this);

        setDaysMenu();
        setCategoriesDropdown();
        getStatsDetail();

        mDaysLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        mCategoriesLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

        mDateLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateDialog();
            }
        });

        horizontal = findViewById(R.id.horizontal);

    }

    /*private List<BarItem> items() {
        List<BarItem> items = new ArrayList<>();
        int darkenClr;

        int i = 0;
        items.add(new BarItem("Burger ", 850d,getColor(R.color.colorAccent), Color.WHITE));
        i++;

        darkenClr = Util.darken(getColor(R.color.colorAccent),0.2);
        items.add(new BarItem("Pizza ", 800d, darkenClr, Color.WHITE));
        i++;

        darkenClr = Util.darken(darkenClr,0.3);
        items.add(new BarItem("Chicken Tikka Masala", 750d, darkenClr, Color.WHITE));
        i++;

        darkenClr = Util.darken(darkenClr,0.4);
        items.add(new BarItem("Omelette ", 650d, darkenClr, Color.WHITE));
        i++;

        darkenClr = Util.darken(darkenClr,0.5);
        items.add(new BarItem("Soup ", 500d, darkenClr, Color.WHITE));

        return items;
    }*/



    private void startDateDialog() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(StatsActivity.this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
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

    private void setCategoriesDropdown() {
        View view = getLayoutInflater().inflate(R.layout.store_categories_layout, null);

        categoryDialog = new BottomSheetDialog(StatsActivity.this);
        categoryDialog.setContentView(view);
    }

    private void setDaysMenu() {
        View view = getLayoutInflater().inflate(R.layout.menu_days_layout, null);

        dialog = new BottomSheetDialog(StatsActivity.this);
        dialog.setContentView(view);

    }


    private void getStatsDetail() {
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("productId", "");
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
                        }
                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }




    public class IndexAxisValueFormatter extends ValueFormatter
    {
        private String[] mValues = new String[] {};
        private int mValueCount = 0;

        /**
         * An empty constructor.
         * Use `setValues` to set the axis labels.
         */
        public IndexAxisValueFormatter() {
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public IndexAxisValueFormatter(String[] values) {
            if (values != null)
                setValues(values);
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public IndexAxisValueFormatter(Collection<String> values) {
            if (values != null)
                setValues(values.toArray(new String[values.size()]));
        }

        @Override
        public String getFormattedValue(float value, AxisBase axisBase) {
            int index = Math.round(value);

            if (index < 0 || index >= mValueCount || index != (int)value)
                return "";

            return mValues[index];
        }

        public String[] getValues()
        {
            return mValues;
        }

        public void setValues(String[] values)
        {
            if (values == null)
                values = new String[] {};

            this.mValues = values;
            this.mValueCount = values.length;
        }
    }
}