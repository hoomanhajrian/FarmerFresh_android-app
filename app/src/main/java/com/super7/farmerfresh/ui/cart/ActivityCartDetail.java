package com.super7.farmerfresh.ui.cart;

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

import com.bumptech.glide.Glide;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.AddCartResponse;
import com.super7.farmerfresh.network.model.FarmListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCartDetail extends AppCompatActivity{

    TextView productName,productPrice,productQnty,productTotalPrice,productTotal;
    ImageView productImg;
    String name,quantity,price,image;
    int totalPrice;
    Button pickup_schedule;
    int minteger = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_detail);
        productName=findViewById(R.id.pDetails_pname);
        productPrice=findViewById(R.id.pDetails_price);
        productQnty=findViewById(R.id.pDetails_quantity);
        productTotalPrice=findViewById(R.id.pDetails_totalPrice);
        productTotal=findViewById(R.id.pDetails_total_Tax);
        productImg=findViewById(R.id.pDetails_pimg);
        pickup_schedule=findViewById(R.id.pickup_schedule);
        getIntentValues();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(intent!=null) {
            name = intent.getStringExtra("pd_name");
            price = intent.getStringExtra("pd_price");
            quantity = intent.getStringExtra("pd_quantity");
            image = intent.getStringExtra("pd_Img");
            //totalPrice = Integer.parseInt(price + "2.30");

            productName.setText(name);
            productPrice.setText("$"+price);
            productQnty.setText(quantity);
            productTotalPrice.setText("$"+price);
            productTotal.setText("$"+price);

            minteger= Integer.parseInt(quantity);
            if(image!=null){
                // Load farm image
                Glide.with(this).load(image).into(productImg);
            }
        }
    }

    public void decreaseProduct(View view) {
        if(minteger!=1){
            minteger = minteger - 1;
        }
        display(minteger);
    }

    public void increaseProduct(View view) {
        minteger = minteger + 1;
        display(minteger);
    }
    private void display(int number) {
        productQnty.setText("" + number);
    }

    public void pick_schedule(View view) {
        dialogBox();
    }


    private void dialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the message show for the Alert time
        builder.setMessage("Are you sure with this schedule?");
        // Set Alert Title
        builder.setTitle("Confirmation");
        builder.setCancelable(false);
        builder.setPositiveButton(
                "OK",
                new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        startActivity(new Intent(ActivityCartDetail.this,ActivityConfirmationOrder.class));
                        //finish()
                    }
                });
        builder
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                finish();
                            }
                        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();

    }
}