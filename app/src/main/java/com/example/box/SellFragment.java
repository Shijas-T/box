package com.example.box;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.Toast;

public class SellFragment extends Fragment {

    //Declaration
    View view;
    private Button buttonSubmit;
    private EditText editTextProductName, editTextProductPrice, editTextProductDescription;
    private AutoCompleteTextView autoCompleteTextViewCategory;
    private ImageView imageViewProduct;

    int SELECT_PICTURE = 200;



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

        imageViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an instance of the
                // intent of the type image
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
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
        imageViewProduct = view.findViewById(R.id.img_product_photo_to_sell);
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

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageViewProduct.setImageURI(selectedImageUri);
                }
            }
        }
    }
}