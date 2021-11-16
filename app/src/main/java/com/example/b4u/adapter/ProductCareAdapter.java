package com.example.b4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.R;
import com.example.b4u.model.ProductCare;

import java.util.List;

public class ProductCareAdapter extends RecyclerView.Adapter<ProductCareAdapter.ProductCareView> {

    Context context;
    List<ProductCare> productCareList;

    @NonNull
    @Override
    public ProductCareView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_care_list,parent, false);

        return new ProductCareView(view);
    }
    public ProductCareAdapter(Context context, List<ProductCare> discountedProductsList) {
        this.context = context;
        this.productCareList = discountedProductsList;
    }
    @Override
    public void onBindViewHolder(@NonNull ProductCareView holder, int position) {
        holder.productCaresImage.setImageResource(productCareList.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return productCareList.size();
    }

    public static class ProductCareView extends RecyclerView.ViewHolder{

        ImageView productCaresImage;

        public ProductCareView(@NonNull View itemView) {
            super(itemView);

            productCaresImage = itemView.findViewById(R.id.categoryImage);
        }

    }

}
