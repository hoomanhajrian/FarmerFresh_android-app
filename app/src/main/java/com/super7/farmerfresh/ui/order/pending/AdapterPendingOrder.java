package com.super7.farmerfresh.ui.order.pending;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.network.model.OrderPendingResponse;
import com.super7.farmerfresh.ui.orderDetails.ActivityOrderDetails;
import com.super7.farmerfresh.ui.product.ActivityProductList;

import java.util.List;

public class AdapterPendingOrder extends RecyclerView.Adapter<AdapterPendingOrder.MyViewHolder> {

    List<OrderPendingResponse> pendingList;
    Context context;

    public AdapterPendingOrder(Context context, List<OrderPendingResponse> pendingList) {
        this.context=context;
        this.pendingList=pendingList;
    }

    public void setPendingList(List<OrderPendingResponse> pendingList) {
        this.pendingList = pendingList;
        notifyDataSetChanged();
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
        holder.order_id.setText("Order: #"+pendingList.get(position).getOrderId());
        holder.order_schedule.setText(pendingList.get(position).getOrderSchedule());
        holder.order_farm.setText(pendingList.get(position).getFarmName());
        holder.order_price.setText("$"+pendingList.get(position).getPrice());
//        holder.parent_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(context, ActivityOrderDetails.class));
//            }
//        });
//
//        if (position%2==0){
//            bgRound(holder.parent_view,context.getResources().getColor(R.color.lightGrey));
//        }else{
//
//            bgRound(holder.parent_view,context.getResources().getColor(R.color.back_white));
//        }
    }

    @Override
    public int getItemCount() {
        return pendingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView parent_view;
        TextView order_id,order_schedule,order_farm,order_price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_view=itemView.findViewById(R.id.rl_parent);
            order_id=itemView.findViewById(R.id.order_id);
            order_schedule=itemView.findViewById(R.id.order_schedule);
            order_farm=itemView.findViewById(R.id.order_farm);
            order_price=itemView.findViewById(R.id.order_price);
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
