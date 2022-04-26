package com.client.aerpaymerchant.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.adapters.ProductAdapter;
import com.client.aerpaymerchant.model.ProductDetails;
import com.client.aerpaymerchant.model.ProductResModel;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StoreProductsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mAddNewProductBtn)
    Button mAddNewProductBtn;

    @BindView(R.id.rv_product)
    RecyclerView rvProduct;

    @BindView((R.id.customSwitch))
    SwitchCompat isStoreEnable;

    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_products);
        ButterKnife.bind(this);

        isStoreEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateStoreStatus(isChecked);
            }
        });

    }

    private void setupData(ArrayList<ProductDetails> list) {
        productAdapter = new ProductAdapter(this,list,(pos, productDetails,action) -> {
            switch (action){
                case 1:
                    setProductOutOfStock(productDetails.getProduct().getId());
                    break;
                case 2 :
                    updateProductDetail();
                    break;
                case 3 :
                    deleteProduct(pos, productDetails);
                    break;
            }
            return null;
        });
        rvProduct.setAdapter(productAdapter);

    }

    private void setProductOutOfStock(String id) {
        HashMap<String, String> object = new HashMap<>();
        HashMap<String, File> files = new HashMap<>();

       /* if (pathsList.length() > 0)
            files.put("images", new File(pathsList));
*/

        object.put("title", "");
        object.put("category_id", "");
        object.put("price", "");
        object.put("sale_price", "");
        object.put("quantity","0");
        object.put("size", "");
        object.put("description", "");
        object.put("store_id", "1");
        object.put("id", id);
        object.put("min_quantity", "");


        new NetworkCall(this)
                .setRequestParams(object)
                .setFiles(files)
                .setEndPoint(APIEndPoints.ADD_PRODUCTS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        Toast.makeText(StoreProductsActivity.this,"Product status changed Successfully!",Toast.LENGTH_LONG).show();
                        hideProgressDialog();
                        finish();
                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }

    private void updateProductDetail() {
    }



    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    @OnClick(R.id.mAddNewProductBtn)
    public void onClick() {
        startActivity(new Intent(StoreProductsActivity.this, AddProductActivity.class));
    }


    private void getProducts(){
        JsonObject object = new JsonObject();

       /* object.addProperty("store_id", getStoreId());
        object.addProperty("user_id",getUser().getId() );*/
        object.addProperty("store_id", getStoreId());
        object.addProperty("user_id",getUser().getId() );

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.GET_PRODUCTS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            ProductResModel resModel = new Gson().fromJson(response,ProductResModel.class);
                            if(resModel.getCode() == 200){
                                    if (resModel.getMsg().size() > 0){
                                        setupData((ArrayList<ProductDetails>) resModel.getMsg());
                                    }
                            }else
                                showToast(jsonObject.getString("msg"));

                        }catch (Exception e){
                            e.printStackTrace();
                            try {
                                showToast(jsonObject.getString("msg"));
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }


    private void deleteProduct(Integer pos, ProductDetails productDetails){
        JsonObject object = new JsonObject();

        object.addProperty("product_id", productDetails.getProduct().getId());

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.DELETE_PRODUCTS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            JSONObject resModel = new JSONObject(response);
                            if(resModel.getInt("code") == 200){
                                    productAdapter.removePos(pos);
                            }else
                                showToast(resModel.getString("msg"));

                        }catch (Exception e){
                            e.printStackTrace();
                            try {
                                showToast(jsonObject.getString("msg"));
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();

    }

    private void updateStoreStatus(boolean isChecked){
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());
        object.addProperty("status", isChecked);

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.UPDATE_STORE_STATUS)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();
                        try{
                            JSONObject resModel = new JSONObject(response);
                            if(resModel.getInt("code") == 200){
                                showToast(resModel.getString("msg"));
                            }else
                                showToast(resModel.getString("msg"));

                        }catch (Exception e){
                            e.printStackTrace();
                            try {
                                showToast(jsonObject.getString("msg"));
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onPreExecute() {
                        showProgressDialog();
                    }


                }).makeCall();
    }
}