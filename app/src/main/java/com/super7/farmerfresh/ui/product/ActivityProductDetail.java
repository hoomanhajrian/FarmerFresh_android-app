package com.super7.farmerfresh.ui.product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.super7.farmerfresh.MainActivity;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.AddCartResponse;
import com.super7.farmerfresh.ui.cart.CartFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityProductDetail extends AppCompatActivity implements View.OnClickListener {

    ImageView product_image;
    TextView product_name, product_cat, product_price, product_desc,product_quantity;
    ImageView back_btn;
    Button add_cart_btn;
    String farm,img,name,cat,price,desc;
    int minteger = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        add_cart_btn = findViewById(R.id.add_cart_btn);
        product_name = findViewById(R.id.pd_name);
        product_cat = findViewById(R.id.pd_category);
        product_price = findViewById(R.id.pd_price);
        product_image = findViewById(R.id.pd_image);
        product_desc = findViewById(R.id.pd_decr);
        product_quantity = findViewById(R.id.pd_quantity);

        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);
        add_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBox();
            }
        });
        getIntentData();
    }

    private void addIntoCart() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Log.d("FARMDETAILSSSSS====",farm+""+name);
        Call<AddCartResponse> call = apiService.addtocart(farm,name,img,cat,price,desc, String.valueOf(minteger));
        call.enqueue(new Callback<AddCartResponse>() {
            @Override
            public void onResponse(Call<AddCartResponse> call, Response<AddCartResponse> response) {
                Log.d("RESPONSEEEEEEEEE", String.valueOf(response.body()));
                Intent i = new Intent(ActivityProductDetail.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(Call<AddCartResponse> call, Throwable t) {
                Toast.makeText(ActivityProductDetail.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getIntentData() {

        Intent intent = getIntent();

        if(intent!=null){

            farm= intent.getStringExtra("product_farm");
            img = intent.getStringExtra("product_img");
            name = intent.getStringExtra("product_name");
            cat = intent.getStringExtra("product_cat");
            price = intent.getStringExtra("product_price");
            desc = intent.getStringExtra("product_desc");

            if(img!=null){
                // Load farm image
                Glide.with(this).load(img).into(product_image);
            }

            product_name.setText(name);
            product_cat.setText(cat);
            product_price.setText("$"+price);
            product_desc.setText(desc);

        }
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    private void dialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the message show for the Alert time
        builder.setMessage("Your item has been successfully added to the cart!");
        // Set Alert Title
        builder.setTitle("Add to Cart");
        builder.setCancelable(false);
        builder.setPositiveButton(
                "Yes",
                new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        addIntoCart();
                    }
                });
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.cancel();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }

    public void decreaseItem(View view) {
        if(minteger!=1){
            minteger = minteger - 1;
        }
        display(minteger);
    }

    public void increaseItem(View view) {
        minteger = minteger + 1;
        display(minteger);
    }
    private void display(int number) {
        product_quantity.setText("" + number);
    }
}