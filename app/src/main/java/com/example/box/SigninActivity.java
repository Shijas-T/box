package com.example.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SigninActivity extends AppCompatActivity {

    //Declaration
    private Button buttonRegister;
    private EditText editTextName, editTextUsn, editTextEmail, editTextPassWord, editTextPassWordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Hide the action bar
        getSupportActionBar().hide();

        onMenu(); //Views in menu
        onClick();//On clickListener
    }

    //All click listeners
    private void onClick() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    //Initialization (all views are initialized in this function)
    private void onMenu() {
        buttonRegister = findViewById(R.id.button_register);
        editTextName = findViewById(R.id.editText_Name);
        editTextUsn = findViewById(R.id.editText_usn);
        editTextEmail = findViewById(R.id.edit_text_user_email);
        editTextPassWord = findViewById(R.id.edit_text_password);
        editTextPassWordConfirmation = findViewById(R.id.edit_text_password_confirmation);
    }


}