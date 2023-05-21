package com.example.box;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountFragment extends Fragment {
    //Declaration
    View view;
    private TextView textViewEdit, textViewName, textViewUsn, textViewEmail, textViewMobileNo, textViewGreenName, textViewGreenUsn;
    String email;
    private static final String TAG = "AccountFragment";

    private FirebaseFirestore db;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);

        // initializing our variable for firebase
        // FireStore and getting its instance.
        db = FirebaseFirestore.getInstance();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        email = preferences.getString("email","");

        onFragment();//Views in fragment
        onClick();//On click listener

        //get user details
        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        AccountModel user = task.getResult().toObject(AccountModel.class);

                        textViewName.setText(user.getUserName());
                        textViewUsn.setText(user.getUserUsn());
                        textViewMobileNo.setText(user.getUserMobile());
                        textViewEmail.setText(user.getUserEmail());
                        textViewGreenName.setText(user.getUserName());
                        textViewGreenUsn.setText(user.getUserUsn());

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        return view;
    }

    // Click listeners
    private void onClick() {
        textViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    //Initialization (all views are initialized in this function)
    private void onFragment() {
        textViewEdit = view.findViewById(R.id.text_view_edit);
        textViewName = view.findViewById(R.id.tv_use_name);
        textViewUsn = view.findViewById(R.id.tv_user_usn);
        textViewEmail = view.findViewById(R.id.tv_user_email);
        textViewMobileNo = view.findViewById(R.id.tv_user_mobile_no);
        textViewGreenName = view.findViewById(R.id.tv_green_name);
        textViewGreenUsn = view.findViewById(R.id.tv_green_usn);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("Users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        AccountModel user = task.getResult().toObject(AccountModel.class);

                        textViewName.setText(user.getUserName());
                        textViewUsn.setText(user.getUserUsn());
                        textViewMobileNo.setText(user.getUserMobile());
                        textViewEmail.setText(user.getUserEmail());
                        textViewGreenName.setText(user.getUserName());
                        textViewGreenUsn.setText(user.getUserUsn());

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}