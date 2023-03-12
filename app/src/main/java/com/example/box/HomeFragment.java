package com.example.box;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    //Declaration
    View view;
    private MaterialCardView cardViewLaptop, cardViewBook, cardViewCalculator, cardViewStationary, cardViewHostelItem,
            cardViewVehicle, cardViewLabCoat, cardViewBag;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        onFragment();//Views in fragment
        onClickFragment();//On click listener

        return view;
    }
    // Click listeners
    private void onClickFragment() {
        cardViewLaptop.setOnClickListener(this);
        cardViewBook.setOnClickListener(this);
        cardViewCalculator.setOnClickListener(this);
        cardViewStationary.setOnClickListener(this);
        cardViewHostelItem.setOnClickListener(this);
        cardViewVehicle.setOnClickListener(this);
        cardViewLabCoat.setOnClickListener(this);
        cardViewBag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), BrowseActivity.class);
        startActivity(intent);
        switch (v.getId()) {
            case R.id.card_laptop:
                break;
            case R.id.card_book:
                break;
            case R.id.card_calculator:
                break;
            case R.id.card_stationary:
                break;
            case R.id.card_hostel_item:
                break;
            case R.id.card_Vehicle:
                break;
            case R.id.card_lab_coat:
                break;
            case R.id.card_bag:
                break;
        }
    }
    //Initialization (all views are initialized in this function)
    private void onFragment() {
        cardViewLaptop = view.findViewById(R.id.card_laptop);
        cardViewBook = view.findViewById(R.id.card_book);
        cardViewCalculator = view.findViewById(R.id.card_calculator);
        cardViewStationary = view.findViewById(R.id.card_stationary);
        cardViewHostelItem = view.findViewById(R.id.card_hostel_item);
        cardViewVehicle = view.findViewById(R.id.card_Vehicle);
        cardViewLabCoat = view.findViewById(R.id.card_lab_coat);
        cardViewBag = view.findViewById(R.id.card_bag);
    }
}