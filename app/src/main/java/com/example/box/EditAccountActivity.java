package com.example.box;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditAccountActivity extends AppCompatActivity {

    //Declaration
    private Button buttonUpdate;
    private EditText editTextName, editTextUsn, editTextMobileNo;
    private String name, usn, mobileNo;

    // creating a variable for firebaseFirestore.
    private FirebaseFirestore db;

    private static final String TAG = "EditAccountActivity";

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

        // getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String email = preferences.getString("email","");
//                Log.e(TAG,"onClick: "+email);

                name = editTextName.getText().toString();
                usn = editTextUsn.getText().toString();
                mobileNo = editTextMobileNo.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(name)) {
                    editTextName.setError("Enter Name");
                } else if (TextUtils.isEmpty(usn)) {
                    editTextUsn.setError("Enter USN");
                } else if (TextUtils.isEmpty(mobileNo)) {
                    editTextMobileNo.setError("Enter Mobile Number");
                } else {
                    // calling a method to update user info.
                    updateCourses(email, mobileNo, name, usn);
                }
            }
        });
    }

    private void updateCourses(String email, String mobileNo, String name, String usn) {
        // inside this method we are passing our updated values
        // inside our object class and later on we
        // will pass our whole object to firebase Firestore.
        AccountModel user = new AccountModel(name, usn, mobileNo,email);

        // after passing data to object class we are
        // sending it to firebase with specific document id.
        // below line is use to get the collection of our Firebase Firestore.
        db.collection("Users").
                // below line is use to set the id of
                // document where we have to perform
                // update operation.
                        document(user.getUserEmail()).

                // after setting our document id we are
                // passing our whole object class to it.
                        set(user).

                // after passing our object class we are
                // calling a method for on success listener.
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // on successful completion of this process
                        // we are displaying the toast message.
                        Toast.makeText(EditAccountActivity.this, "Account info updated..", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            // inside on failure method we are
            // displaying a failure message.
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditAccountActivity.this, "Fail to update the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}