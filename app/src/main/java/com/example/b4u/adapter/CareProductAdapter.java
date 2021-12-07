package com.example.b4u.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.Product_details;
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
    public CareProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_care,parent,false);
        return new CareProductAdapter.CareProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(careProductList.get(position).getName());
        holder.description.setText(careProductList.get(position).getDescription());
        holder.price.setText(careProductList.get(position).getPrice());
        holder.pricebf.setText(careProductList.get(position).getPrice_before());
        holder.rate.setText(careProductList.get(position).getRate());
        holder.img.setImageResource(careProductList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Product_details.class);

                i.putExtra("name", careProductList.get(position).getName());
                i.putExtra("description", careProductList.get(position).getDescription());
                i.putExtra("price", careProductList.get(position).getPrice());
                i.putExtra("pricebf", careProductList.get(position).getPrice_before());
                i.putExtra("rate", careProductList.get(position).getRate());
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
            name = itemView.findViewById(R.id.product_name);
            description = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.price);
            pricebf = itemView.findViewById(R.id.price_before);
            rate = itemView.findViewById(R.id.rate);
            img = itemView.findViewById(R.id.img);

        }
    }
}
