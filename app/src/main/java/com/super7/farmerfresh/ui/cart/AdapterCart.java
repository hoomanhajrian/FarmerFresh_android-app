package com.super7.farmerfresh.ui.cart;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.CartListResponse;
import com.super7.farmerfresh.network.model.FarmsProductResponse;
import com.super7.farmerfresh.network.model.RemoveCartItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyViewHolder> {

    private Context context;
    private List<CartListResponse> cartList;

    public AdapterCart(Context context, List<CartListResponse> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    public void setCartList(List<CartListResponse> cartList){
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout cart_parent;
        ImageView cv_image,cancel_item;
        TextView cv_product,cv_farm,cv_qnty;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cart_parent = itemView.findViewById(R.id.cart_parent);
            cv_image = itemView.findViewById(R.id.cv_image);
            cv_product = itemView.findViewById(R.id.cv_product);
            cv_farm = itemView.findViewById(R.id.cv_farm);
            cv_qnty = itemView.findViewById(R.id.cv_qnty);
            cancel_item = itemView.findViewById(R.id.cancel_item);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.adapter_cart, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(cartList.get(position).getProductImg()!=null){
            // Load farm image
            Glide.with(context).load(cartList.get(position).getProductImg()).into(holder.cv_image);
        }

        holder.cv_product.setText(cartList.get(position).getProductName());
        holder.cv_farm.setText(cartList.get(position).getFarmName());
        holder.cv_qnty.setText(cartList.get(position).getQuantity() +" lbs");


        holder.cart_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ActivityCartDetail.class);
                intent.putExtra("pd_farm",cartList.get(position).getFarmName());
                intent.putExtra("pd_Img",cartList.get(position).getProductImg());
                intent.putExtra("pd_name",cartList.get(position).getProductName());
                intent.putExtra("pd_price",cartList.get(position).getProductPrice());
                intent.putExtra("pd_quantity",cartList.get(position).getQuantity());
                context.startActivity(intent);
            }
        });

        holder.cancel_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<RemoveCartItemResponse> call = apiService.deleteCartItem(cartList.get(position).getCartId());
                    call.enqueue(new Callback<RemoveCartItemResponse>() {
                        @Override
                        public void onResponse(Call<RemoveCartItemResponse> call, Response<RemoveCartItemResponse> response) {
                            Toast.makeText(context, "Item deleted from cart", Toast.LENGTH_SHORT).show();
                            cartList.remove(position);
                            if(cartList.size()==0){
                                CartFragment.no_item_msg.setVisibility(View.VISIBLE);
                            }else{
                                CartFragment.no_item_msg.setVisibility(View.GONE);
                            }
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<RemoveCartItemResponse> call, Throwable t) {

                        }
                    });
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

}
