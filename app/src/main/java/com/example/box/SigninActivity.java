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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    //Declaration
    private Button buttonRegister;
    private EditText editTextName, editTextUsn, editTextEmail, editTextPassWord, editTextPassWordConfirmation;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Hide the action bar
        getSupportActionBar().hide();


        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        onMenu(); //Views in menu
        onClick();//On clickListener
    }

    //All click listeners
    private void onClick() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
//                startActivity(intent);
                registerNewUser();

            }
        });
    }
    //Initialization (all views are initialized in this function)
    private void onMenu() {
        buttonRegister = findViewById(R.id.button_register);
//        editTextName = findViewById(R.id.editText_Name);
//        editTextUsn = findViewById(R.id.editText_usn);
        editTextEmail = findViewById(R.id.edit_text_user_email);
        editTextPassWord = findViewById(R.id.edit_text_password);
//        editTextPassWordConfirmation = findViewById(R.id.edit_text_password_confirmation);
        progressbar = findViewById(R.id.registrationPageProgressBar);

    }

    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = editTextEmail.getText().toString();
        password = editTextPassWord.getText().toString();

        // Validations for input email and password
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

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                            "Registration successful!",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(SigninActivity.this,
                                    HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                            getApplicationContext(),
                                            "Registration failed!!"
                                                    + " Please try again later",
                                            Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }


}