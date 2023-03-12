package com.example.box;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SellFragment extends Fragment {

    //Declaration
    View view;
    private Button buttonSubmit;
    private EditText editTextProductName, editTextProductPrice, editTextProductDescription;
    private AutoCompleteTextView autoCompleteTextViewCategory;

    public SellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sell, container, false);

        onMenu(); //Views in menu
        onClick();//On clickListener

        //Strings for DropDown
        String[] category = getResources().getStringArray(R.array.product_category);
        //Setting Adapter
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,category);
        autoCompleteTextViewCategory.setAdapter(categoryAdapter);

        return view;
    }

    //All click listeners
    private void onClick() {

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), autoCompleteTextViewCategory.getText().toString(), Toast.LENGTH_SHORT).show();
//                  Toast.makeText(getContext(), editTextProductDescription.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Initialization (all views are initialized in this function)
    private void onMenu() {
        editTextProductName = view.findViewById(R.id.edit_text_product_name);
        editTextProductPrice = view.findViewById(R.id.edit_text_product_price);
        editTextProductDescription = view.findViewById(R.id.edit_text_product_description);
        buttonSubmit = view.findViewById(R.id.btn_addProductSubmit);
        autoCompleteTextViewCategory = view.findViewById(R.id.auto_complete_tv_category);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Enter Product Details");
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Box");
    }
}