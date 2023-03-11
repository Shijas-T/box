package com.example.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminLoginActivity extends AppCompatActivity {

    //Declaration
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().setTitle("Admin Login");

        onMenu(); //Views in menu
        onClick();//On clickListener
    }

    //All click listeners
    private void onClick() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminLoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    //Initialization (all views are initialized in this function)
    private void onMenu() {
        buttonLogin = findViewById(R.id.button_admin_login);
        editTextEmail = findViewById(R.id.edit_text_admin_email);
        editTextPassWord = findViewById(R.id.edit_text_admin_password);
    }
}