package com.example.b4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.R;
import com.example.b4u.model.PurchasedProduct;

import java.util.List;

public class PurchasedAdapter extends RecyclerView.Adapter<PurchasedAdapter.PurchasedApdapterViewHolder> {
    Context context;
    private List<PurchasedProduct> productList;

    public PurchasedAdapter(List<PurchasedProduct> productList,Context context) {

        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public PurchasedApdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_product_bought,parent,false);
        return new PurchasedApdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedApdapterViewHolder holder, int position) {
        PurchasedProduct purchasedProduct = productList.get(position);
        holder.nameProduct.setText("Tên sản phẩm: " + purchasedProduct.getNameProduct());

        holder.priceProduct.setText("Tổng giá trị đơn hàng: " + purchasedProduct.getTotalPrice() +" VNĐ");
        holder.ID.setText("Mã đơn hàng: " + purchasedProduct.getTime());
        holder.nameUser.setText("Tên người đặt hàng: " + purchasedProduct.getName() );
        holder.phoneUser.setText("Số điện thoại: "+ purchasedProduct.getPhone());
        holder.addUser.setText("Địa chỉ giao hàng: " +purchasedProduct.getAddress());
        holder.quantityProduct.setText("Số lượng: "+ purchasedProduct.getQuantityProduct());
        holder.price.setText("Giá sản phẩm: "+purchasedProduct.getPriceProduct() +" VNĐ");
    }

    @Override
    public int getItemCount() {
        if(productList != null)
        {
            return productList.size();
        }
        else
        {
            return 0;
        }

    }

    public class PurchasedApdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView nameProduct;
        private TextView priceProduct;
        private TextView ID;
        private TextView nameUser;
        private TextView phoneUser;
        private TextView addUser;
        private TextView quantityProduct;
        private TextView price;

        public PurchasedApdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct = itemView.findViewById(R.id.boughtProduct);
            priceProduct = itemView.findViewById(R.id.boughtPrice);
            ID = itemView.findViewById(R.id.boughtID);
            nameUser = itemView.findViewById(R.id.boughtUserName);
            phoneUser = itemView.findViewById(R.id.boughtPhone);
            addUser = itemView.findViewById(R.id.boughtAddress);
            quantityProduct = itemView.findViewById(R.id.boughtQuantity);
            price = itemView.findViewById(R.id.boughtPriceProduct);
        }
    }
}
