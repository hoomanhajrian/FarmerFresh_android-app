package com.super7.farmerfresh.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.ui.product.ActivityProductDetail;
import com.super7.farmerfresh.ui.product.ActivityProductList;

import java.util.List;

public class  AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder>  {

    private List<FarmListResponse> farmList;
    private Context context;

    public AdapterHome(Context context, List<FarmListResponse> farmList) {
        this.context = context;
        this.farmList = farmList;
    }


    public void setFarmList(List<FarmListResponse> farmList) {
        this.farmList = farmList;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parent_view;
        ImageView ivFarm;
        TextView name, description, address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parent_view = itemView.findViewById(R.id.parent_view);
            ivFarm = itemView.findViewById(R.id.iv_farm);
            name = itemView.findViewById(R.id.farm_name);
            description = itemView.findViewById(R.id.farm_desc);
            address = itemView.findViewById(R.id.farm_address);
        }
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

        //holder.ivFarm.setClipToOutline(true);

        if(farmList.get(position).getFarmImg()!=null){
            // Load farm image
            Glide.with(context).load(farmList.get(position).getFarmImg()).into(holder.ivFarm);
        }

        holder.name.setText(farmList.get(position).getFarmName());
        holder.description.setText("\""+ farmList.get(position).getFarmDescription() +"\"");
        holder.address.setText(farmList.get(position).getFarmAddress());
        holder.parent_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityProductList.class);
                intent.putExtra("farm_id",farmList.get(position).getFarmId());
                intent.putExtra("farm_name",farmList.get(position).getFarmName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return farmList.size();
    }

}
