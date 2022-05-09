package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.coupon.Coupons;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import butterknife.ButterKnife;

public class AddCategoryActivity extends BaseActivity {

    private String pathsList = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        addCategory();

    }

    private void addCategory() {
        HashMap<String, String> object = new HashMap<>();
        HashMap<String, File> files = new HashMap<>();

        if (pathsList.length() > 0) {
            files.put("image", new File(pathsList));
            object.put("name", "");
            object.put("description", "");
            object.put("level","1");
            object.put("featured", "1");
            object.put("is_active", "");
            object.put("store_id", getStoreId());


            new NetworkCall(this)
                    .setRequestParams(object)
                    .setFiles(files)
                    .setEndPoint(APIEndPoints.ADD_CATEGORY)
                    .setResponseListener(new RetrofitResponseListener() {
                        @Override
                        public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                            hideProgressDialog();

                        }

                        @Override
                        public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                            Toast.makeText(AddCategoryActivity.this, "Category Added Successfully!", Toast.LENGTH_LONG).show();
                            hideProgressDialog();
                            finish();
                        }

                        @Override
                        public void onPreExecute() {
                            showProgressDialog();
                        }


                    }).makeCall();
        }else{
            showToast("please add product Image before adding product");
        }
    }
}
