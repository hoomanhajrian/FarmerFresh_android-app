package com.super7.farmerfresh.ui.product;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.model.FarmsProductResponse;
import com.super7.farmerfresh.network.model.ProductListResponse;
import com.super7.farmerfresh.ui.home.AdapterHome;
import com.super7.farmerfresh.ui.orderDetails.ActivityOrderDetails;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyviewHolder> {

     Context context;
     List<FarmsProductResponse> productList;
     String farmName;

    public AdapterProduct(Context context, List<FarmsProductResponse> productList, String farmName) {
        this.context = context;
        this.productList = productList;
        this.farmName = farmName;
    }

    public void setProductList(List<FarmsProductResponse> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        CardView parent_view;
        ImageView product_image;
        TextView product_name, product_cat, product_price;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            parent_view = itemView.findViewById(R.id.parent_view);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name);
            product_cat = itemView.findViewById(R.id.product_cat);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }

    @NonNull
    @Override
    public AdapterProduct.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_product_list, parent, false);
        return new MyviewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.MyviewHolder holder, int position) {

        if(productList.get(position).getProductImg()!=null){
            // Load farm image
            Glide.with(context).load(productList.get(position).getProductImg()).into(holder.product_image);
        }

        holder.product_name.setText(productList.get(position).getProductName());
        holder.product_cat.setText(productList.get(position).getProductCategory());
        holder.product_price.setText("$"+productList.get(position).getProductPrice());

        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityProductDetail.class);
                intent.putExtra("product_farm",farmName);
                intent.putExtra("product_img",productList.get(position).getProductImg());
                intent.putExtra("product_name",productList.get(position).getProductName());
                intent.putExtra("product_cat",productList.get(position).getProductCategory());
                intent.putExtra("product_price",productList.get(position).getProductPrice());
                intent.putExtra("product_desc",productList.get(position).getProductDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
