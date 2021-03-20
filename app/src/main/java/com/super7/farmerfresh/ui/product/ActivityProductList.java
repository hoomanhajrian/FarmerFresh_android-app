package com.super7.farmerfresh.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.FarmListResponse;
import com.super7.farmerfresh.network.model.ProductListResponse;
import com.super7.farmerfresh.ui.home.AdapterHome;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductList extends AppCompatActivity {

    RecyclerView product_rv;
    AdapterProduct adapterProduct;
    List<ProductListResponse> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        product_rv=findViewById(R.id.product_rv);
        initView();
    }

    private void initView(){
        adapterProduct = new AdapterProduct(this,productList);
        product_rv.setHasFixedSize(true);
        product_rv.setLayoutManager(new GridLayoutManager(this,2));
        getProductResponse();
    }

    public void getProductResponse() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ProductListResponse>> call = apiService.getProduct();
        call.enqueue(new Callback<List<ProductListResponse>>() {
            @Override
            public void onResponse(Call<List<ProductListResponse>> call, Response<List<ProductListResponse>> response) {
                productList = response.body();
                Log.d("TAG","Response = "+productList);
                adapterProduct.setProductList(productList);
                product_rv.setAdapter(adapterProduct);
            }

            @Override
            public void onFailure(Call<List<ProductListResponse>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }


}