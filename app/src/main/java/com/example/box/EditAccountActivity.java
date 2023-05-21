package com.example.box;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditAccountActivity extends AppCompatActivity {

    //Declaration
    private Button buttonUpdate;
    private EditText editTextName, editTextUsn, editTextMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        getSupportActionBar().setTitle("Edit Info");

        //Initialization
        buttonUpdate = findViewById(R.id.button_update);
        editTextName = findViewById(R.id.et_name);
        editTextUsn = findViewById(R.id.et_usn);
        editTextMobileNo = findViewById(R.id.et_mobile);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}