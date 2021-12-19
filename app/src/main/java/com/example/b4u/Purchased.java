package com.example.b4u;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Purchased extends AppCompatActivity {
    Button buttonPurchased;
    TextView nameProduct,priceProduct,productQuantity,productDelivery,productTotal;
    EditText name,phone,address;
    String userID;
    String fname,fPrice;
    int fQuantity;
    int image,priceProductInt;
    int ship = 350000;
    ImageView imgProuct;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        Intent i = getIntent();
        fname = i.getStringExtra("name");
        fPrice = i.getStringExtra("price");
        fQuantity = i.getIntExtra("quantity",0);
        image = i.getIntExtra("image",R.drawable.product_1);
        buttonPurchased = findViewById(R.id.buttonPurchased);
        nameProduct = findViewById(R.id.textNameProduct);
        priceProduct = findViewById(R.id.textNamePrice);
        productQuantity = findViewById(R.id.textQuantityPurchased);
        productDelivery = findViewById(R.id.priceDelivery);
        productTotal = findViewById(R.id.purchasedPrice);
        name = findViewById(R.id.editTextTextPersonNameP);
        phone = findViewById(R.id.editTextTextPhoneP);
        address = findViewById(R.id.editTextTextAddressP);
        imgProuct = findViewById(R.id.imageProductPurchased);
        imgProuct.setImageResource(image);
        nameProduct.setText("Tên Sản Phẩm: "+fname);
        productQuantity.setText("Số Lượng: "+String.valueOf(fQuantity));
        priceProduct.setText("Đơn Gía: "+fPrice+" VNĐ");
        productDelivery.setText(String.valueOf(ship)+ " VNĐ");

        //Tính tổng tiền sản phẩm
        priceProductInt = Integer.parseInt(fPrice);
        int total = (priceProductInt * fQuantity) + ship;
        productTotal.setText(String.valueOf(total)+" VNĐ");

        //Get Information User
        DocumentReference documentReference = firebaseFirestore.collection("users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                name.setText(documentSnapshot.getString("fName"));
                phone.setText(documentSnapshot.getString("fPhone"));
                address.setText(documentSnapshot.getString("fAddress"));
            }
        });

        buttonPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}