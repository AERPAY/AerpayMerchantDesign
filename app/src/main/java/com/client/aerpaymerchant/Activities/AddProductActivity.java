package com.client.aerpaymerchant.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.network.APIEndPoints;
import com.client.aerpaymerchant.network.NetworkCall;
import com.client.aerpaymerchant.network.listeners.RetrofitResponseListener;
import com.client.aerpaymerchant.preference.FileUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.mCategoryLl)
    LinearLayout mCategoryLl;

    @BindView(R.id.mLlQtyType)
    LinearLayout mLlQtyType;

    @BindView(R.id.btn_add_product)
    Button mBtnAddProduct;

    @BindView(R.id.et_product_name)
    EditText mEtPName;

    @BindView(R.id.et_product_category)
    EditText mEtPCategory;

    @BindView(R.id.et_price)
    EditText mEtPrice;

    @BindView(R.id.et_qty_type)
    EditText mEtqtyType;

    @BindView(R.id.et_qty_available)
    EditText mEtqtyAvailable;

    @BindView(R.id.et_product_desc)
    EditText mEtPDesc;

    @BindView(R.id.edtMinOrderQty)
    EditText mEtMinQty;

    @BindView(R.id.imagePathListText)
    TextView mEtImagePath;

    BottomSheetDialog dialog, qtyDialog,sizeDialog;

    @BindView(R.id.mSizeLl)
    LinearLayout mSizeLl;
    @BindView(R.id.mQtyLl)
    TextInputLayout mQtyLl;

    @BindView(R.id.addCategory)
    ImageView mAddCategory;

    @BindView((R.id.inventorySwitch))
    SwitchCompat isInventoryEnable;

    String inventoryStatus = "true";

    private int PICKER_REQUEST_CODE = 100;
    private String pathsList = "";

    QtyAdapter qtyAdapter;

    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        isInventoryEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                inventoryStatus = Boolean.toString(isChecked);
            }
        });

        setCategoriesMenu();

        setSizeMenu();

        setQtyRecyclerView();

        mCategoryLl.setOnClickListener(v -> dialog.show());

        mLlQtyType.setOnClickListener(v -> qtyDialog.show());

        mSizeLl.setOnClickListener(v -> sizeDialog.show());

        mAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void setSizeMenu() {

        View view = getLayoutInflater().inflate(R.layout.menu_size, null);

        sizeDialog = new BottomSheetDialog(AddProductActivity.this);
        sizeDialog.setContentView(view);

    }

    private void setQtyRecyclerView() {

        List<String> qtyList=new ArrayList<>();
        qtyList.add("Packet");
        qtyList.add("Number");
        qtyList.add("Box");
        qtyList.add("Glass Size");
        qtyList.add("Kg");
        qtyList.add("Dozen");
        qtyList.add("Litre");
        qtyList.add("Gram");
        qtyList.add("Plate");
        qtyList.add("Bundle");

        View view = getLayoutInflater().inflate(R.layout.qty_layout, null);

        RecyclerView mRecyclerView=view.findViewById(R.id.mRecyclerView);

        qtyDialog = new BottomSheetDialog(AddProductActivity.this);
        qtyDialog.setContentView(view);

        qtyAdapter = new QtyAdapter(AddProductActivity.this, qtyList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(AddProductActivity.this));
        mRecyclerView.setAdapter(qtyAdapter);




    }


    private void setCategoriesMenu() {
        List<String> categoryList=new ArrayList<>();
        categoryList.add("Chicken");
        categoryList.add("Burgers");
        categoryList.add("Noodles");
        categoryList.add("French Fries");

        View view = getLayoutInflater().inflate(R.layout.menu_products_categories, null);

        RecyclerView categoryRecyclerView=view.findViewById(R.id.categoryRecyclerView);

        dialog = new BottomSheetDialog(AddProductActivity.this);
        dialog.setContentView(view);

        categoryAdapter = new CategoryAdapter(AddProductActivity.this, categoryList);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(AddProductActivity.this));
        categoryRecyclerView.setAdapter(categoryAdapter);

    }

    @OnClick(R.id.btn_add_product)
    void addProduct() {
        HashMap<String, String> object = new HashMap<>();
        HashMap<String, File> files = new HashMap<>();

        if (pathsList.length() > 0) {
            files.put("images", new File(pathsList));
            object.put("title", mEtPName.getText().toString());
            object.put("category_id", "1");
            object.put("price", mEtPrice.getText().toString());
            object.put("sale_price", mEtPrice.getText().toString());
            object.put("quantity", mEtqtyAvailable.getText().toString());
            object.put("size", mEtqtyAvailable.getText().toString());
            object.put("description", mEtPDesc.getText().toString());
            object.put("store_id", getStoreId());
            object.put("id", "1");
            object.put("min_quantity", mEtMinQty.getText().toString());
            object.put("is_alert_enable", inventoryStatus);



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
                            Toast.makeText(AddProductActivity.this, "Product Added Successfully!", Toast.LENGTH_LONG).show();
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

    @OnClick(R.id.ll_add_img)
    void selectProduct() {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .start(PICKER_REQUEST_CODE);
//        new GligarPicker().requestCode(PICKER_REQUEST_CODE).limit(2).withActivity(this).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            pathsList = FileUtils.INSTANCE.getPath(this, data.getData()); // return list of selected images paths.
            Log.e("File path", pathsList);
            mEtImagePath.setText(pathsList);
        }

    }



    //--------------------------------------Qty Adapter-----------------------------------------
    public class QtyAdapter extends RecyclerView.Adapter<QtyAdapter.MyViewHolder> {

        Context context;
        List<String> childFeedList;

        public QtyAdapter(Context context, List<String > childFeedList) {
            this.context = context;
            this.childFeedList = childFeedList;

        }

        @NonNull
        @Override
        public QtyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_qty, parent, false);
            return new QtyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(QtyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.mNameTv.setText(childFeedList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(childFeedList.get(position).equalsIgnoreCase("Glass Size")){
                        mQtyLl.setVisibility(View.GONE);
                        mSizeLl.setVisibility(View.VISIBLE);
                        mEtqtyType.setText(childFeedList.get(position));
                    }else{
                        mQtyLl.setVisibility(View.VISIBLE);
                        mSizeLl.setVisibility(View.GONE);
                        mEtqtyType.setText(childFeedList.get(position));
                    }

                    qtyDialog.dismiss();
                }
            });

        }


        @Override
        public int getItemCount() {
            return childFeedList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView mNameTv;


            public MyViewHolder(View itemView) {
                super(itemView);

                mNameTv=itemView.findViewById(R.id.mNameTv);




            }
        }
    }

    public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

        Context context;
        List<String> categoryList;

        public CategoryAdapter(Context context, List<String > childFeedList) {
            this.context = context;
            this.categoryList = childFeedList;

        }

        @NonNull
        @Override
        public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_qty, parent, false);
            return new CategoryAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CategoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.mNameTv.setText(categoryList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mEtPCategory.setText(categoryList.get(position));
                    dialog.dismiss();
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