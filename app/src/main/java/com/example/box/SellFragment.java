package com.example.box;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class SellFragment extends Fragment {

    //Declaration
    View view;
    private Button buttonSubmit;
    private EditText editTextProductName, editTextProductPrice, editTextProductDescription;
    private AutoCompleteTextView autoCompleteTextViewCategory;
    private ImageView imageViewProduct;
    private ProgressBar mProgressBar;

    private String name, category, price, description, url;

    int SELECT_PICTURE = 200;
    Uri selectedImageUri;

    private StorageReference mStorageRef;
    private FirebaseFirestore db;

    private StorageTask mUploadTask;

    transient private static final String TAG = "SellFragment";

    public SellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sell, container, false);

        mStorageRef = FirebaseStorage.getInstance().getReference("Product Image");
        db = FirebaseFirestore.getInstance();

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

                name = editTextProductName.getText().toString();
                category = autoCompleteTextViewCategory.getText().toString();
                price = editTextProductPrice.getText().toString();
                description = editTextProductDescription.getText().toString();

                if (mUploadTask != null && mUploadTask.isInProgress()) {
                    Toast.makeText(getContext(), "Upload in progress", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name)) {
                    editTextProductName.setError("Enter name");
                } else if (TextUtils.isEmpty(category)) {
                    Toast.makeText(getContext(), "Select a category", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(price)) {
                    editTextProductPrice.setError("Enter price");
                } else if (TextUtils.isEmpty(description)) {
                    editTextProductDescription.setError("Enter description");
                }else if (selectedImageUri == null) {
                    Toast.makeText(getContext(), "Select an image", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();
                }
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
        mProgressBar = view.findViewById(R.id.progress_bar);
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
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageViewProduct.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (selectedImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(selectedImageUri));

            //upload image
            mUploadTask = fileReference.putFile(selectedImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(getContext(), "Image uploaded", Toast.LENGTH_LONG).show();
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
//                                    Log.e(TAG, "onSuccess: " + uri.toString());
                                    url = uri.toString();
                                }
                            });
                            ProductModel product = new ProductModel( name, category, price, description, url);

                            //add product details to fireStore

                            String uniqueID = UUID.randomUUID().toString();

                            CollectionReference dbProducts = db.collection("Products");
                            dbProducts.document(category + uniqueID).set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void avoid) {
                                    Toast.makeText(getContext(), "Successfully added", Toast.LENGTH_SHORT).show();

                                    editTextProductName.setText("");
                                    editTextProductPrice.setText("");
                                    editTextProductDescription.setText("");
                                    autoCompleteTextViewCategory.setText("");
                                    imageViewProduct.setImageResource(R.mipmap.select_image);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "failed to add" + e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        } else {
            Toast.makeText(getContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}