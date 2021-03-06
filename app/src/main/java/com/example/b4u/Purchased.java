package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b4u.model.Cart;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
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
    int image,priceProductInt, total;
    int ship = 35000;
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
        nameProduct.setText("T??n S???n Ph???m: "+fname);
        productQuantity.setText("S??? L?????ng: "+String.valueOf(fQuantity));
        priceProduct.setText("????n Gi??: "+fPrice+" VN??");

        //T??nh t???ng ti???n s???n ph???m
        priceProductInt = Integer.parseInt(fPrice);
        total = (priceProductInt * fQuantity);

        if(total > 400000)
        {
            productTotal.setText(String.valueOf(total)+" VN??");
            productDelivery.setText("Mi???n ph?? giao h??ng.");
        }
        else {
            total = total + ship;
            productTotal.setText(String.valueOf(total)+" VN??");
            productDelivery.setText(String.valueOf(ship)+ " VN??");
        }


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

        String timestamps = ""+System.currentTimeMillis();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        buttonPurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = name.getText().toString().trim();
                String getPhone = phone.getText().toString().trim();
                String getAdd = address.getText().toString().trim();
                HashMap<String,Object> data = new HashMap<>();
                if(TextUtils.isEmpty(getName))
                {
                    name.setError("T??n kh??ng ???????c ????? tr???ng.");
                    return;
                }
                if(TextUtils.isEmpty(getPhone))
                {
                    phone.setError("S??? ??i???n tho???i kh??ng ???????c ????? tr???ng.");
                    return;
                } if(TextUtils.isEmpty(getAdd))
                {
                    address.setError("?????a ch??? ???????c ????? tr???ng.");
                    return;
                }
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
                                Toast.makeText(Purchased.this,"???? ?????t H??ng Th??nh C??ng",Toast.LENGTH_SHORT).show();
                                reference.child(userID).child("Cart").removeValue(new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        Toast.makeText(Purchased.this,"Complete",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                startActivity(new Intent(Purchased.this,MainActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Purchased.this,"Th???t B???i",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}