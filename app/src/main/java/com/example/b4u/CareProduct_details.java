package com.example.b4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CareProduct_details extends AppCompatActivity {
    String carename,caredes,careprice,carerate;
    int careimage;
    ImageView careimg,careback,carecart;
    TextView careproductname,careproductdes, careproductprice, careproductrate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.careproduct_details);

        Intent i = getIntent();
        carename = i.getStringExtra("carename");
        caredes = i.getStringExtra("caredescription");
        careprice = i.getStringExtra("careprice");
        carerate = i.getStringExtra("carerate");
        careimage = i.getIntExtra("careimage",R.drawable.product_1);

        careproductname = findViewById(R.id.careproductName);
        careproductdes = findViewById(R.id.careproductDes);
        careproductprice = findViewById(R.id.careproductPrice);
        careproductrate = findViewById(R.id.careproductRate);
        careimg = findViewById(R.id.careimageProduct);
        careback = findViewById(R.id.careback);

        careproductname.setText(carename);
        careproductdes.setText(caredes);
        careproductprice.setText(careprice);
        careproductrate.setText(carerate);
        careimg.setImageResource(careimage);
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
    }
}
