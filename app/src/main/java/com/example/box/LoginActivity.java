package com.example.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    //Declaration
    private TextView textViewNewSellerRegistration, textViewNewUserRegistration, textViewForgetPassWord;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide the action bar
        getSupportActionBar().hide();

        onMenu(); //Views in menu
        onClick();//On clickListener
    }

    //All click listeners
    private void onClick() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

//        textViewForgetPassWord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RecentActivity.class);
//                startActivity(intent);
//            }
//        });

//        textViewNewSellerRegistration.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RecentActivity.class);
//                startActivity(intent);
//            }
//        });

        textViewNewUserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });


    }

    //Initialization (all views are initialized in this function)
    private void onMenu() {
        buttonLogin = findViewById(R.id.Button_login);
        textViewForgetPassWord = findViewById(R.id.TextView_forgotPassword);
        textViewNewSellerRegistration = findViewById(R.id.TextView_newSellerRegistration);
        textViewNewUserRegistration = findViewById(R.id.TextView_newUserRegistration);
    }
}