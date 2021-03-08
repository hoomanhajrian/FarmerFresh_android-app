package com.super7.farmerfresh.ui.order.pending;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.orderDetails.ActivityOrderDetails;
import com.super7.farmerfresh.ui.product.ActivityProductList;

public class AdapterPendingOrder extends RecyclerView.Adapter<AdapterPendingOrder.MyViewHolder> {
    Context context;
    public AdapterPendingOrder(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_pending_order, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ActivityOrderDetails.class));
            }
        });

//        if (position%2==0){
//            bgRound(holder.parent_view,context.getResources().getColor(R.color.lightGrey));
//        }else{
//
//            bgRound(holder.parent_view,context.getResources().getColor(R.color.Pri_Green_1));
//        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout parent_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_view=itemView.findViewById(R.id.rl_parent);
        }
    }

    private void bgRound(View view,int color){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
//      gradientDrawable.setStroke(3,color);
//      gradientDrawable.setCornerRadii(new float[] { 10, 10, 10, 10, 0, 0, 0, 0 });
        gradientDrawable.setCornerRadius(10);
//      or setCornerRadius(gradientDrawable, 20f, 40f, 60f, 80f);

        view.setBackground(gradientDrawable);
    }
}
