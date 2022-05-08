package com.client.aerpaymerchant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.client.aerpaymerchant.R;
import com.client.aerpaymerchant.model.orderdetail.OrderStoreProduct;
import com.client.aerpaymerchant.network.APIConstants;

import java.util.ArrayList;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

    private Context context;
    private ArrayList<OrderStoreProduct> orderList;

    public OrderDetailsAdapter(Context context,ArrayList<OrderStoreProduct> mOrderList) {
        this.context = context;
        this.orderList = mOrderList;
    }


    @Override
    public OrderDetailsAdapter.OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_list_item, parent, false);
        return new OrderDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderDetailsViewHolder holder, final int position) {
        holder.prdt_name_qty.setText(orderList.get(position).getProductTitle() + " X " + orderList.get(position).getProductQuantity());
        holder.prdt_price.setText(orderList.get(position).getProductPrice());

        Glide.with(context)
                .load(APIConstants.BASE_URL+ orderList.get(position).getProductImage())
                .error(R.drawable.splash_logo)
                .into(holder.prdt_image);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView prdt_name_qty, prdt_price;
        ImageView prdt_image;

        public OrderDetailsViewHolder(View v) {
            super(v);
            prdt_name_qty = (TextView) v.findViewById(R.id.prdt_name_qty);
            prdt_price = (TextView) v.findViewById(R.id.prdt_price);
            prdt_image = (ImageView) v.findViewById(R.id.prdt_image);
        }
    }
}
