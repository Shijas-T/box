package com.example.box;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Declaration
    private TextView textViewAdminLogin, textViewNewUserRegistration, textViewForgetPassWord;
    private EditText editTextLoginEmail, editTextLoginPassWord;
    private Button buttonLogin;
    private ProgressBar progressbar;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide the action bar
        getSupportActionBar().hide();

        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        onMenu(); //Views in menu
        onClick();//On clickListener
    }

    //All click listeners
    private void onClick() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent);
                loginUserAccount();
            }
        });

//        textViewForgetPassWord.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, RecentActivity.class);
//                startActivity(intent);
//            }
//        });

        textViewAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        textViewNewUserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    //Initialization (all views are initialized in this function)
    private void onMenu() {
        buttonLogin = findViewById(R.id.Button_login);
//        textViewForgetPassWord = findViewById(R.id.TextView_forgotPassword);
        textViewAdminLogin = findViewById(R.id.TextView_newSellerRegistration);
        textViewNewUserRegistration = findViewById(R.id.TextView_newUserRegistration);
        progressbar = findViewById(R.id.loginPageProgressBar);
        editTextLoginEmail = findViewById(R.id.EditText_usernameLogin);
        editTextLoginPassWord = findViewById(R.id.EditText_passwordLogin);

    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = editTextLoginEmail.getText().toString();
        password = editTextLoginPassWord.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(LoginActivity.this,
                                            HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
}