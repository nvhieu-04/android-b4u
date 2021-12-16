package com.example.b4u.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.CareProduct_details;
import com.example.b4u.R;
import com.example.b4u.model.CareProduct;

import java.util.List;

public class CareProductAdapter extends RecyclerView.Adapter<CareProductAdapter.CareProductViewHolder> {

    Context context;
    List<CareProduct> careProductList;

    public CareProductAdapter(Context context, List<CareProduct> careProductList) {
        this.context = context;
        this.careProductList = careProductList;
    }


    @NonNull
    @Override
    public CareProductAdapter.CareProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_care,parent,false);
        return new CareProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareProductAdapter.CareProductViewHolder holder, int position) {
        holder.name.setText(careProductList.get(position).getName());
        holder.description.setText(careProductList.get(position).getDescription());
        holder.price.setText(careProductList.get(position).getPrice());
        holder.pricebf.setText(careProductList.get(position).getPrice_before());
        holder.rate.setText(careProductList.get(position).getRate());
        holder.img.setImageResource(careProductList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, CareProduct_details.class);

                i.putExtra("carename", careProductList.get(position).getName());
                i.putExtra("caredescription", careProductList.get(position).getDescription());
                i.putExtra("careprice", careProductList.get(position).getPrice());
                i.putExtra("carepricebf", careProductList.get(position).getPrice_before());
                i.putExtra("carerate", careProductList.get(position).getRate());
                i.putExtra("careimage",careProductList.get(position).getBigimageurl());
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return careProductList.size();
    }
    public static class CareProductViewHolder extends RecyclerView.ViewHolder{

        TextView name,description, price, pricebf, rate;
        ImageView img;
        public CareProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.careproduct_name);
            description = itemView.findViewById(R.id.careproduct_description);
            price = itemView.findViewById(R.id.careprice);
            pricebf = itemView.findViewById(R.id.careprice_before);
            rate = itemView.findViewById(R.id.carerate);
            img = itemView.findViewById(R.id.product_img);

        }
    }
}
