package com.super7.farmerfresh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.super7.farmerfresh.ui.login.LoginActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);
        Button next1 = (Button) findViewById(R.id.nextButton1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_onboarding2);
                Button next2 = (Button) findViewById(R.id.nextButton2);
                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_onboarding3);
                        Button startBtn = (Button) findViewById(R.id.nextButton3);
                        startBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                Intent intent=new Intent(OnboardingActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });



    }
}