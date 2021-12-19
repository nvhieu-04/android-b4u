package com.example.b4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class category_list extends AppCompatActivity {
    String carename;
    ImageView careimg,careback,carecart,careimg2,careimg3,careimg4;
    int careimage,careimage2,careimage3,careimage4;
    TextView careproductname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cate_1);
        carecart = findViewById(R.id.carecart);
        careback = findViewById(R.id.careback);
        careback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(category_list.this,MainActivity.class);
                startActivity((iNewActivity));
                finish();
            }
        });
        Intent i = getIntent();
        carename = i.getStringExtra("carename");
        careimage = i.getIntExtra("careimage",R.drawable.product_1);
        careimage2 = i.getIntExtra("careimage",R.drawable.product_2);
        careproductname = findViewById(R.id.productName);
        careproductname.setText(carename);
        careimg = findViewById(R.id.careimageProduct);
        careimg2 = findViewById(R.id.careimageProduct2);
        careimg3 = findViewById(R.id.careimageProduct3);
        careimg4 = findViewById(R.id.careimageProduct4);
        careimg.setImageResource(careimage);
        careimg.setImageResource(careimage2);


    }

}