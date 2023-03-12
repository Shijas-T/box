package com.example.box;

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
        switch (v.getId()) {
            case R.id.card_laptop:
                Toast.makeText(getContext(), "1" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_book:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_calculator:
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_stationary:
                Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_hostel_item:
                Toast.makeText(getContext(), "5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_Vehicle:
                Toast.makeText(getContext(), "6", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_lab_coat:
                Toast.makeText(getContext(), "7", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_bag:
                Toast.makeText(getContext(), "it's working", Toast.LENGTH_SHORT).show();
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