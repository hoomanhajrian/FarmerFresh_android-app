package com.super7.farmerfresh.ui.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.CartListResponse;
import com.super7.farmerfresh.network.model.FarmListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    AdapterCart adapterCart;
    RecyclerView rv_cart;
    List<CartListResponse> cartList;
    //Button payment_btn;
    public static TextView no_item_msg;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        rv_cart=view.findViewById(R.id.rv_cart);
        no_item_msg=view.findViewById(R.id.no_item_msg);
        no_item_msg.setVisibility(View.GONE);
        //payment_btn=view.findViewById(R.id.payment_btn);
        initView();
        return view;
    }

    private void initView() {
        adapterCart = new AdapterCart(getActivity(),cartList);
        rv_cart.setHasFixedSize(true);
        rv_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        getCartResponse();
    }

    private void getCartResponse() {

       ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CartListResponse>> call = apiService.getCarts("1");
        call.enqueue(new Callback<List<CartListResponse>>() {
            @Override
            public void onResponse(Call<List<CartListResponse>> call, Response<List<CartListResponse>> response) {
                cartList = response.body();
                Log.d("TAG","Response = "+cartList);
                if(cartList.size()==0){
                    no_item_msg.setVisibility(View.VISIBLE);
                }else{
                    no_item_msg.setVisibility(View.GONE);
                }
                adapterCart.setCartList(cartList);
                rv_cart.setAdapter(adapterCart);
            }

            @Override
            public void onFailure(Call<List<CartListResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}