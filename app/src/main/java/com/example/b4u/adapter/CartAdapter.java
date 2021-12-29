package com.example.b4u.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b4u.R;
import com.example.b4u.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    List<Cart> cartList;
    Context context;
    private ClickListener clickListener;
    public interface ClickListener{
        void onClickAddItems(Cart cart);
        void onClickMinusItems(Cart cart);
        void onClickDeleteItems(Cart cart);

    }

    public CartAdapter(List<Cart> cartList, Context context,ClickListener listener) {
        this.cartList = cartList;
        this.context = context;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_check,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.nameProduct.setText("Tên Sản Phẩm: "+ cart.getNameProduct());
        holder.priceProduct.setText("Giá Sản Phẩm: "+cart.getPriceProduct()+" VNĐ");
        holder.quantityProduct.setText(cart.getQuantityProduct());
        holder.totalPrice.setText("Tổng: " + cart.getTotalPrice()+" VNĐ");

        //
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickMinusItems(cart);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickAddItems(cart);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClickDeleteItems(cart);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder{

        TextView nameProduct;
        TextView priceProduct;
        TextView quantityProduct;
        TextView totalPrice;
        ImageView plus,minus;
        Button delete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct = itemView.findViewById(R.id.cartName);
            priceProduct = itemView.findViewById(R.id.cartPrice);
            quantityProduct = itemView.findViewById(R.id.cartQuantity);
            totalPrice = itemView.findViewById(R.id.cartTotal);
            delete = itemView.findViewById(R.id.cartDelete);
            plus = itemView.findViewById(R.id.cartPlus);
            minus = itemView.findViewById(R.id.cartMinus);

        }
    }
}
