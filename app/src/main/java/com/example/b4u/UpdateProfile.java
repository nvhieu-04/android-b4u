package com.example.b4u;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {
    String userID;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Button btnUpdate;
    EditText fName,fBirthday,fPhone,fAdd;
    ImageView btnBack;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        fName = findViewById(R.id.editTextTextPersonName2);
        fPhone = findViewById(R.id.editTextPhone2);
        fBirthday = findViewById(R.id.editTextBirth2);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnBack = findViewById(R.id.backButton);
        fAdd = findViewById(R.id.editTextAdd2);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        //
        fBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(UpdateProfile.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }

        });
        //
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateProfile.this,MainActivity.class));
            }
        });

        //
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fName.setText(documentSnapshot.getString("fName"));
                fPhone.setText(documentSnapshot.getString("fPhone"));
                fBirthday.setText(documentSnapshot.getString("fBirthDay"));
                fAdd.setText(documentSnapshot.getString("fAddress"));
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = fName.getText().toString().trim();
                String phone = fPhone.getText().toString().trim();
                String birth = fBirthday.getText().toString().trim();
                String address = fAdd.getText().toString().trim();
                DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
                documentReference
                        .update(
                                "fName",name,
                                "fPhone",phone,
                                "fBirthDay",birth,
                                "fAddress",address
                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "Cập nhật thành công thông tin!");
                                Toast.makeText(UpdateProfile.this,"Cập nhật thông tin thành công. Vui lòng khởi động lại ứng dụng",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Lỗi", e);
                            }
                        });

            }
        });
    }
}