package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.b4u.adapter.PurchasedAdapter;
import com.example.b4u.model.PurchasedProduct;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bought extends AppCompatActivity {
    RecyclerView recyclerView;
    PurchasedAdapter purchasedAdapter;
    List<PurchasedProduct> productList;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference reference;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought);
        recyclerView = findViewById(R.id.boughtRecycleView);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        productList = new ArrayList<>();
        purchasedAdapter = new PurchasedAdapter(productList);
        recyclerView.setAdapter(purchasedAdapter);
        getlistProduct();
    }

    private void getlistProduct(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference  = database.getReference("users").child(userID).child("Purchased");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    PurchasedProduct purchasedProduct = dataSnapshot.getValue(PurchasedProduct.class);
                    productList.add(purchasedProduct);
                }
                purchasedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Bought.this,"Thất bại",Toast.LENGTH_SHORT).show();
            }
        });

    }
}