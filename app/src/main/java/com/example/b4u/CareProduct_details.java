package com.example.b4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class CareProduct_details extends AppCompatActivity {
    String carename,caredes,careprice,carerate,caredescription,userID;
    int careimage;
    ImageView careimg,careback,carecart,plus,minus;
    TextView careproductname,careproductdes, careproductprice, careproductrate, carediscription,quantity;
    int totalQuantity = 1;
    int totalPrice;
    Button btnBuyNow,btnAddCart;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproduct_details);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        Intent i = getIntent();
        carename = i.getStringExtra("carename");
        caredes = i.getStringExtra("caredescription");
        careprice = i.getStringExtra("careprice");
        carerate = i.getStringExtra("carerate");
        careimage = i.getIntExtra("careimage",R.drawable.product_1);
        caredescription = i.getStringExtra("carepricebf");
        careproductname = findViewById(R.id.careproductName);
        careproductdes = findViewById(R.id.careproductDes);
        careproductprice = findViewById(R.id.careproductPrice);
        careproductrate = findViewById(R.id.careproductRate);
        carediscription = findViewById(R.id.textViewcareDescription);
        careimg = findViewById(R.id.careimageProduct);
        careback = findViewById(R.id.careback);
        plus = findViewById(R.id.imageViewPlus);
        minus = findViewById(R.id.imageViewMinus);
        quantity = findViewById(R.id.textViewQuantity);
        btnBuyNow = findViewById(R.id.buttonCareBuyNow);
        btnAddCart = findViewById(R.id.buttonCareAddCart);
        carediscription.setText(caredescription);
        careproductname.setText(carename);
        careproductdes.setText(caredes);
        careproductprice.setText(careprice);
        careproductrate.setText(carerate);
        careimg.setImageResource(careimage);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 1)
                {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));

                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalQuantity++;
                quantity.setText(String.valueOf(totalQuantity));
            }
        });

        careback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CareProduct_details.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        View decorView = getWindow().getDecorView();
        carecart = findViewById(R.id.carecart);
        carecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareProduct_details.this, cart_acitivity.class);
                startActivity(intent);
            }
        });
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareProduct_details.this,Purchased.class);
                intent.putExtra("name",carename);
                intent.putExtra("price",careprice);
                intent.putExtra("image",careimage);
                intent.putExtra("quantity",totalQuantity);
                startActivity(intent);
            }
        });
        String timestamps = ""+System.currentTimeMillis();
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> data = new HashMap<>();
                totalPrice = (Integer.parseInt(careprice)*totalQuantity);
                data.put("NameProduct",carename);
                data.put("PriceProduct",careprice);
                data.put("QuantityProduct",""+totalQuantity);
                data.put("TotalPrice",""+ totalPrice);
                reference.child(firebaseAuth.getUid()).child("Cart").child(carename).setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CareProduct_details.this,"Đã Thêm Vào Giỏ Hàng",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CareProduct_details.this,"Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
