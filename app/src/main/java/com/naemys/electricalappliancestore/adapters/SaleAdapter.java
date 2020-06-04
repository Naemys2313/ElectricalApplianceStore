package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Sale;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleViewHolder> {

    private Context context;
    private List<Sale> sales;

    public SaleAdapter(Context context, List<Sale> sales) {
        this.context = context;
        this.sales = sales;
    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sale, parent, false);
        return new SaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder holder, int position) {
        Sale sale = sales.get(position);

        holder.idTextView.setText(sale.getId());
        holder.goodsIdTextView.setText(sale.getGoodsId());
        holder.priceTextView.setText(sale.getPrice());
        holder.discountTextView.setText(sale.getDiscount());
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public static class SaleViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, goodsIdTextView, priceTextView, discountTextView;

        public SaleViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            goodsIdTextView = itemView.findViewById(R.id.goodsIdTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            discountTextView = itemView.findViewById(R.id.discountTextView);
        }
    }
}
