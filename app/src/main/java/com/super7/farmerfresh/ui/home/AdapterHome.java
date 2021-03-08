package com.super7.farmerfresh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.product.ActivityProductList;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder>  {
    Context context;
    public AdapterHome(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_home, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHome.MyViewHolder holder, int position) {
        holder.ivFarm.setClipToOutline(true);
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ActivityProductList.class));
            }
        });

        bgRound(holder.ivFarm);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent_view;
        ImageView ivFarm;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_view=itemView.findViewById(R.id.parent_view);
            ivFarm=itemView.findViewById(R.id.iv_farm);
        }
    }

    private void bgRound(View view){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.WHITE);
        gradientDrawable.setStroke(3,Color.GRAY);
        gradientDrawable.setCornerRadii(new float[] { 10, 10, 10, 10, 0, 0, 0, 0 });

//or setCornerRadius(gradientDrawable, 20f, 40f, 60f, 80f);

        view.setBackground(gradientDrawable);
    }
}
