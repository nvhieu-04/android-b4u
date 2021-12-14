package com.example.b4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CareProduct_details extends AppCompatActivity {
    String name,des,price,rate;
    int image;
    ImageView img,back,cart;
    TextView productname,productdes, productprice, productrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careproduct_details);

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

        productname.setText(name);
        productdes.setText(des);
        productprice.setText(price);
        productrate.setText(rate);
        img.setImageResource(image);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CareProduct_details.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        View decorView = getWindow().getDecorView();
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareProduct_details.this, cart_acitivity.class);
                startActivity(intent);
            }
        });
}
}
