package com.client.aerpaymerchant.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.category.CategoryList;
import com.client.aerpaymerchant.model.coupon.Coupons;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends BaseActivity{

    RecyclerView categoryRecyclerView;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryRecyclerView = findViewById(R.id.categoryRecyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCategoryList();

    }

    private void getCategoryList() {
        JsonObject object = new JsonObject();

        object.addProperty("store_id", getStoreId());

        new NetworkCall(this)
                .setRequestObject(object)
                .setEndPoint(APIEndPoints.SHOW_CATEGORY)
                .setResponseListener(new RetrofitResponseListener() {
                    @Override
                    public void onError(int statusCode, @NonNull String message, @Nullable JSONObject jsonObject) {
                        hideProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, @NonNull JSONObject jsonObject, @NonNull String response) {
                        hideProgressDialog();

                        try {
                            CategoryList resModel = new Gson().fromJson(response, CategoryList.class);
                            if (resModel.getMsg() != null) {
                                List<String> categoryNameList  = new ArrayList<>();
                                for (int i =0;i<resModel.getMsg().size();i++){
                                    categoryNameList.add(resModel.getMsg().get(i).getCategory().getName());
                                }

                                categoryAdapter = new CategoryAdapter(CategoryListActivity.this, categoryNameList);
                                categoryRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
                                categoryRecyclerView.setAdapter(categoryAdapter);
                                categoryAdapter.notifyDataSetChanged();
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

    public class CategoryAdapter extends RecyclerView.Adapter<CategoryListActivity.CategoryAdapter.MyViewHolder> {

        Context context;
        List<String> categoryList;

        public CategoryAdapter(Context context, List<String > childFeedList) {
            this.context = context;
            this.categoryList = childFeedList;

        }

        @NonNull
        @Override
        public CategoryListActivity.CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_qty, parent, false);
            return new CategoryListActivity.CategoryAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryListActivity.CategoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.mNameTv.setText(categoryList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }


        @Override
        public int getItemCount() {
            return categoryList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mNameTv;


            public MyViewHolder(View itemView) {
                super(itemView);

                mNameTv=itemView.findViewById(R.id.mNameTv);




            }
        }
    }

}
