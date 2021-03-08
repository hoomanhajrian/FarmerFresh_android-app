package com.super7.farmerfresh.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.super7.farmerfresh.R;
import com.super7.farmerfresh.ui.home.AdapterHome;

public class ActivityProductList extends AppCompatActivity {
    RecyclerView product_rv;
    AdapterProduct adapterHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        product_rv=findViewById(R.id.product_rv);
        initView();
    }

    private void initView(){
        adapterHome = new AdapterProduct(this);
        product_rv.setHasFixedSize(true);
        product_rv.setLayoutManager(new GridLayoutManager(this,2));
        product_rv.setAdapter(adapterHome);
    }
}