package com.super7.farmerfresh.ui.cart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.super7.farmerfresh.R;
import com.super7.farmerfresh.network.ApiClient;
import com.super7.farmerfresh.network.ApiInterface;
import com.super7.farmerfresh.network.model.OrderCheckoutResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCartDetail extends AppCompatActivity{

    TextView productName,productPrice,productQnty,productTotalPrice,productTotal,pickupDate;
    ImageView productImg;
    String name,quantity,price,image,farmName;
    DatePickerDialog picker;
    Button pickup_schedule,order_checkout;
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
        pickupDate=findViewById(R.id.pickupDate);
        getIntentValues();
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if(intent!=null) {
            name = intent.getStringExtra("pd_name");
            price = intent.getStringExtra("pd_price");
            quantity = intent.getStringExtra("pd_quantity");
            image = intent.getStringExtra("pd_Img");
            farmName=intent.getStringExtra("pd_farm");
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
        //dialogBox();
        //orderCompleted();
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(ActivityCartDetail.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        pickupDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year,month,day);

        picker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        Log.d("PICK", String.valueOf(picker));
        Log.d("PICK", String.valueOf(pickupDate));
        picker.show();

    }

    public void orderCompleted(View view) {
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
                        orderCompletedResponse();

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

    private void orderCompletedResponse() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<OrderCheckoutResponse> call = apiService.orderCompleted("5-4-2021", pickupDate.getText().toString(),"1",quantity,farmName,price);
        call.enqueue(new Callback<OrderCheckoutResponse>() {
            @Override
            public void onResponse(Call<OrderCheckoutResponse> call, Response<OrderCheckoutResponse> response) {
                Log.d("RESPONSEEE", String.valueOf(response.body()));
                startActivity(new Intent(ActivityCartDetail.this,ActivityConfirmationOrder.class)); dialogBox();
            }

            @Override
            public void onFailure(Call<OrderCheckoutResponse> call, Throwable t) {
                Toast.makeText(ActivityCartDetail.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}