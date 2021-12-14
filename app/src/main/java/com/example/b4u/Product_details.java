package com.example.b4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Product_details extends AppCompatActivity {
    String name,des,price,rate;
    int image;
    ImageView img,back,cart;
    TextView productname,productdes, productprice, productrate;
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

        productname = findViewById(R.id.productName);
        productdes = findViewById(R.id.productDes);
        productprice = findViewById(R.id.productPrice);
        productrate = findViewById(R.id.productRate);
        img = findViewById(R.id.imageProduct);
        back = findViewById(R.id.back);
        cart = findViewById(R.id.cart);
        productname.setText(name);
        productdes.setText(des);
        productprice.setText(price);
        productrate.setText(rate);
        img.setImageResource(image);
        View decorView = getWindow().getDecorView();


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




    }
}