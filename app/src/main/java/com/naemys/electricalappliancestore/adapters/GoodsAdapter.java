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
    private View view;
    private CartAdapter.OnClickListener onClickListener;

    public GoodsAdapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    public void setOnClickListener(CartAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, parent, false);

        this.view = view;

        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, final int position) {
        Goods goodsItem = goods.get(position);

        holder.idTextView.setText(goodsItem.getId());
        holder.nameTextView.setText(goodsItem.getName());
        holder.typeIdTextView.setText(goodsItem.getTypeId());
        holder.quantityInStockTextView.setText(goodsItem.getQuantityInStock());
        holder.descriptionTextView.setText(goodsItem.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
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
