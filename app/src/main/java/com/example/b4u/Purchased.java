package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

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
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
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
        String getName = name.getText().toString().trim();
        String getPhone = phone.getText().toString().trim();
        String getAdd = address.getText().toString().trim();
        String timestamps = ""+System.currentTimeMillis();
        buttonPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,Object> data = new HashMap<>();
                data.put("NameProduct",fname);
                data.put("PriceProduct",""+fPrice);
                data.put("QuantityProduct",""+fQuantity);
                data.put("TotalPrice",""+total);
                data.put("Name", getName);
                data.put("Phone",getPhone);
                data.put("Address",getAdd);
                data.put("Time",timestamps);

                reference.child(firebaseAuth.getUid()).child("Purchased").child(timestamps).setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Purchased.this,"Đã Đặt Hàng Thành Công",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Purchased.this,MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Purchased.this,"Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}