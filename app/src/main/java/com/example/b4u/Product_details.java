package com.example.b4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Product_details extends AppCompatActivity {
    String name,des,price,rate,description,userID;
    int image;
    ImageView img,back,cart,minus,plus;
    TextView productname,productdes, productprice, productrate,productdescription,quantity;
    Button btnBuyNow,btnAddCart;
    int totalQuantity = 1;
    int totalPrice;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();
        name = i.getStringExtra("name");
        des = i.getStringExtra("description");
        price = i.getStringExtra("price");
        rate = i.getStringExtra("rate");
        image = i.getIntExtra("image",R.drawable.product_1);
        description = i.getStringExtra("pricebf");
        productname = findViewById(R.id.productName);
        productdes = findViewById(R.id.productDes);
        productprice = findViewById(R.id.productPrice);
        productrate = findViewById(R.id.productRate);
        productdescription= findViewById(R.id.textViewDescription);
        img = findViewById(R.id.imageProduct);
        back = findViewById(R.id.back);
        cart = findViewById(R.id.cart);
        plus = findViewById(R.id.image_plus);
        minus = findViewById(R.id.img_minus);
        quantity = findViewById(R.id.textQuantity);
        btnBuyNow = findViewById(R.id.buttonBuyNow);
        btnAddCart = findViewById(R.id.buttonAddCart);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        productname.setText(name);
        productdes.setText(des);
        productprice.setText(price);
        productrate.setText(rate);
        img.setImageResource(image);
        productdescription.setText(description);
        View decorView = getWindow().getDecorView();


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
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Product_details.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_details.this,cart_acitivity.class);
                startActivity(intent);
            }
        });
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_details.this,Purchased.class);
                intent.putExtra("name",name);
                intent.putExtra("price",price);
                intent.putExtra("image",image);
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
                totalPrice = (Integer.parseInt(price)*totalQuantity);
                data.put("NameProduct",name);
                data.put("PriceProduct",price);
                data.put("QuantityProduct",""+totalQuantity);
                data.put("TotalPrice",""+ totalPrice);
                reference.child(firebaseAuth.getUid()).child("Cart").child(name).setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Product_details.this,"Đã Thêm Vào Giỏ Hàng",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Product_details.this,"Thất Bại",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }
}