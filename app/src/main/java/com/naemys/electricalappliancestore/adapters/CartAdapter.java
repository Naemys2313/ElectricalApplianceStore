package com.naemys.electricalappliancestore.adapters;

import android.content.ContentUris;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Cart;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private static final String TAG = CartAdapter.class.getSimpleName();
    private OnClickListener onClickListener;
    private View view;
    private Context context;
    private ArrayList<Cart> carts;

    public interface OnClickListener {
        void onClick(int position);
    }

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);

        this.view = view;

        return new CartViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, final int position) {
        Cart cart = carts.get(position);

        holder.idTextView.setText(cart.getId());
        holder.goodsIdTextView.setText(cart.getGoodsId());
        holder.quantityTextView.setText(cart.getQuantity());
        holder.orderIdTextView.setText(cart.getOrderId());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(holder.getAdapterPosition());

                Log.d(TAG, "onClick: " + holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, goodsIdTextView, quantityTextView, orderIdTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            goodsIdTextView = itemView.findViewById(R.id.goodsIdTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
        }
    }
}

