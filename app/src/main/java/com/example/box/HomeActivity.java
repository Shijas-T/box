package com.example.box;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //Declaration
    BottomNavigationView bottomNavigationView;

    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_item_home);
    }

    //Instance of fragment
    HomeFragment homeFragment = new HomeFragment();
    SellFragment sellFragment = new SellFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;

            case R.id.menu_item_sell:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, sellFragment).commit();
                return true;

            case R.id.menu_item_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, accountFragment).commit();
                return true;
        }
        return false;
    }

    // For exiting the application
    @Override
    public void onBackPressed() {
        if (exit && bottomNavigationView.getSelectedItemId() == R.id.menu_item_home) {
            finish();
        }

        if ( bottomNavigationView.getSelectedItemId() != R.id.menu_item_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
            bottomNavigationView.setSelectedItemId(R.id.menu_item_home);
        } else {
            Snackbar.make(findViewById(R.id.activity_home), "Press back again to exit", Snackbar.LENGTH_SHORT)
                    .setAnchorView(bottomNavigationView)
                    .show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
        }
    }
}