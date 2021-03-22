package com.super7.farmerfresh.ui.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.super7.farmerfresh.MainActivity;
import com.super7.farmerfresh.R;

public class ActivityConfirmationOrder extends AppCompatActivity {

    Button continueShopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);
        continueShopping=findViewById(R.id.continueShopping);

        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityConfirmationOrder.this, MainActivity.class));
            }
        });
    }

}