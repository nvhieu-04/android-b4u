package com.example.b4u;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CareProduct_details extends AppCompatActivity {
    String carename,caredes,careprice,carerate,caredescription;
    int careimage;
    ImageView careimg,careback,carecart,plus,minus;
    TextView careproductname,careproductdes, careproductprice, careproductrate, carediscription,quantity;
    int totalQuantity = 1;
    Button btnBuyNow,btnAddCart;
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


    }
}
