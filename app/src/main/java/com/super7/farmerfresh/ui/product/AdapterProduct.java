package com.super7.farmerfresh.ui.product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.home.AdapterHome;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyviewHolder> {

    Context context;
    public AdapterProduct(Context context) {
        this.context=context;
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
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ActivityProductDetail.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent_view;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            parent_view=itemView.findViewById(R.id.parent_view);
        }
    }
}
