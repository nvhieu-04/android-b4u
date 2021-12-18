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
import com.example.b4u.UpdateProfile;
import com.example.b4u.model.AllCategory;
import com.example.b4u.model.CareProduct;
import com.example.b4u.model.Category;
import com.example.b4u.category_list;
import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder> {

    Context context;
    List<AllCategory> allcategoryList;
    List<CareProduct> careProductList;
    public AllCategoryAdapter(Context context, List<AllCategory> allcategoryList) {
        this.context = context;
        this.allcategoryList = allcategoryList;
    }

    @NonNull
    @Override
    public AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_category_list, parent, false);

        return new AllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryViewHolder holder, int position) {
            holder.categoryImage.setImageResource(allcategoryList.get(position).getImageurl());
            holder.categoryName.setText(allcategoryList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,category_list.class);
                i.putExtra("carename", allcategoryList.get(position).getName());
                i.putExtra("careimage",allcategoryList.get(position).getImageurl());
                i.putExtra("careimage",allcategoryList.get(position).getImageurl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allcategoryList.size();
    }

    public static class AllCategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;
        public AllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryName = itemView.findViewById(R.id.categoryName);
        }
    }
}
