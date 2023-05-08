package com.example.box;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductInDetailActivity extends AppCompatActivity {

    //Declaration
    private Button buttonCallSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_in_detail);

        //Initialization
        buttonCallSeller = findViewById(R.id.btn_call_seller);

        buttonCallSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting instance of Intent with action as ACTION_CALL
                Intent phone_intent = new Intent(Intent.ACTION_DIAL);
                // Set data of Intent through Uri by parsing phone number
                phone_intent.setData(Uri.parse("tel:" + "8281652366"));
                startActivity(phone_intent);
            }
        });
    }
}