package com.super7.farmerfresh.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.super7.farmerfresh.R;

public class ActivityProductDetail extends AppCompatActivity {

    ImageView product_image;
    TextView product_name,product_cat,product_price,product_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        product_name= findViewById(R.id.pd_name);
        product_cat= findViewById(R.id.pd_category);
        product_price= findViewById(R.id.pd_price);
        product_image= findViewById(R.id.pd_image);
        product_desc= findViewById(R.id.pd_decr);

        getIntentData();
    }

    public void getIntentData() {

        Intent intent = getIntent();

        if(intent!=null){

            String img = intent.getStringExtra("product_img");
            String name = intent.getStringExtra("product_name");
            String cat = intent.getStringExtra("product_cat");
            String price = intent.getStringExtra("product_price");
            String desc = intent.getStringExtra("product_desc");

            if(img!=null){
                // Load farm image
                Picasso.with(this).load(img).into(product_image);
            }

            product_name.setText(name);
            product_cat.setText(cat);
            product_price.setText("$"+price);
            product_desc.setText(desc);

        }
    }
}