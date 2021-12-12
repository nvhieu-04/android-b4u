package com.example.b4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.R;
import com.example.b4u.model.AllCategory;
import com.example.b4u.model.Category;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder> {

    Context context;
    List<AllCategory> allcategoryList;

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
