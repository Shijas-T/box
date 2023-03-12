package com.example.box;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity {

    //Declaration
    private RecyclerView recyclerViewListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        recyclerViewListItem = findViewById(R.id.recyclerview_all_items);

        //Dummy Data
        ArrayList<ProductModel> arrayListAllItem = new ArrayList<>();
        arrayListAllItem.add(new ProductModel("dfsf","ThinkPad X1 Nano","40,000","for rent. In good quality"));
        arrayListAllItem.add(new ProductModel("dfjsf","MacBook Air","28,000","for rent"));
        arrayListAllItem.add(new ProductModel("dfsf","Zephyrus G15","48,000","best gaming laptop"));
        arrayListAllItem.add(new ProductModel("dfsf","Dell XPS 13","35,999","ideal for office use"));
        arrayListAllItem.add(new ProductModel("dfsf","MacBook Pro","38,600","apple macbook pro for rent"));

        //recycler view initializing and seting
        BrowseItemAdapter adapter = new BrowseItemAdapter(this);
        adapter.setArrayListAllItem(arrayListAllItem);
        recyclerViewListItem.setAdapter(adapter);
        recyclerViewListItem.setLayoutManager(new LinearLayoutManager(this));

    }
}