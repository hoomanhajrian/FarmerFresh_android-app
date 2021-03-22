package com.super7.farmerfresh.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.FarmsProductResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductList extends AppCompatActivity implements View.OnClickListener {

    RecyclerView product_rv;
    AdapterProduct adapterProduct;
    List<FarmsProductResponse> productList;
    ImageView back_btn;
    String farmId, farmName;
    TextView farm_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        product_rv=findViewById(R.id.product_rv);
        back_btn=findViewById(R.id.back_btn);
        farm_name=findViewById(R.id.farm_name);
        back_btn.setOnClickListener(this);
        initView();

    }


    private void initView(){

        Intent intent = getIntent();
        if(intent!=null){
            farmName= intent.getStringExtra("farm_name");
            farmId = intent.getStringExtra("farm_id");
            Log.d("PRRRRRRRRRR", farmId);
            farm_name.setText(farmName);
            adapterProduct = new AdapterProduct(this,productList,farmName);
            product_rv.setHasFixedSize(true);
            product_rv.setLayoutManager(new GridLayoutManager(this,2));

            getProductResponse(farmId);
        }


    }

    public void getProductResponse(String id) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<FarmsProductResponse>> call = apiService.getFarmsProduct(id);
        call.enqueue(new Callback<List<FarmsProductResponse>>() {
            @Override
            public void onResponse(Call<List<FarmsProductResponse>> call, Response<List<FarmsProductResponse>> response) {
                productList = response.body();
                adapterProduct.setProductList(productList);
                product_rv.setAdapter(adapterProduct);
            }

            @Override
            public void onFailure(Call<List<FarmsProductResponse>> call, Throwable t) {
                Log.d("TAG=================ERROR","Response = "+t.getMessage());
            }
        });

    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

}