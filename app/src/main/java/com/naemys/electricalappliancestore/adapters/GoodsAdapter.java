package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Goods;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {

    private Context context;
    private List<Goods> goods;

    public GoodsAdapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        Goods goodsItem = goods.get(position);

        holder.idTextView.setText(goodsItem.getId());
        holder.nameTextView.setText(goodsItem.getName());
        holder.typeIdTextView.setText(goodsItem.getTypeId());
        holder.quantityInStockTextView.setText(goodsItem.getQuantityInStock());
        holder.descriptionTextView.setText(goodsItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public static class GoodsViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, nameTextView, typeIdTextView, quantityInStockTextView, descriptionTextView;

        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeIdTextView = itemView.findViewById(R.id.typeIdTextView);
            quantityInStockTextView = itemView.findViewById(R.id.quantityInStockTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);

        }
    }
}
