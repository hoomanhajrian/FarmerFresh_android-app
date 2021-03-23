package com.super7.farmerfresh;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.super7.farmerfresh.ui.main.SectionsPagerAdapter;

import java.lang.reflect.Type;

public class AuthActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    String clientID = "799813312459-3808gnajesn12qqdrqphmenifgf8bgoa.apps.googleusercontent.com";
    int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestServerAuthCode(clientID).build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        setContentView(R.layout.activity_auth);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

//TODO :click handlers for sign in buttons
    public void googleAuth(View view) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
///
    public void signUp(View v){
        TextView name = (TextView) findViewById(R.id.name);
        TextView pass = (TextView) findViewById(R.id.passwordUp);
        TextView email = (TextView) findViewById(R.id.emailUp);
        TextView errorText = (TextView) findViewById(R.id.errorText2);

        if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || name.getText().toString().isEmpty()) {
            errorText.setText("Please Fill The Form!");
        }
        else{
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://farmerfresh.ca/api/V1/user/auth/reg?name="+name.getText().toString()+"&email="+email.getText().toString()+"&pass="+pass.getText().toString();
            Intent intent = new Intent(this, this.getClass());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("registered!")){
                                errorText.setText("Successfully Registered!");
                                name.setText("");
                                email.setText("");
                                pass.setText("");
                            }else{
                                errorText.setText("Something Went Wrong Please Try Again!");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorText.setText("Something Went Wrong Please Try Again!");
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
    }

    public void signIn(View v) {
        TextView email = findViewById(R.id.email);
        TextView pass = findViewById(R.id.password);
        TextView errorText = findViewById(R.id.errorText);

        if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
            errorText.setText("Please Fill Both Email And Password!");
        }
        else{
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://farmerfresh.ca/api/V1/user/auth/approval?email="+email.getText().toString()+"&pass="+pass.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals(email.getText().toString())){
                                finish();
                                startActivity(intent);
                            }else{
                                errorText.setText("Wrong Email or Password!");
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorText.setText("Please Check Network Connection And Try Again!");
                }
            });

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            updateUI(null);
        }
    }

    public void updateUI(GoogleSignInAccount account){

        if(account != null){
            Toast.makeText(this,"Welcome " + account.getDisplayName(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }else {
            Toast.makeText(this,"Sorry, try again later!",Toast.LENGTH_LONG).show();
        }

    }


}
