package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Supplier;
import com.naemys.electricalappliancestore.models.TypeOfGoods;

import java.util.List;

public class TypeOfGoodsAdapter extends RecyclerView.Adapter<TypeOfGoodsAdapter.TypeOfGoodsViewHolder> {

    private Context context;
    private List<TypeOfGoods> typesOfGoods;

    private View view;
    private CartAdapter.OnClickListener onClickListener;

    public TypeOfGoodsAdapter(Context context, List<TypeOfGoods> typesOfGoods) {
        this.context = context;
        this.typesOfGoods = typesOfGoods;
    }

    public void setOnClickListener(CartAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TypeOfGoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type_of_goods, parent, false);

        this.view = view;

        return new TypeOfGoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeOfGoodsViewHolder holder, final int position) {
        TypeOfGoods typeOfGoods = typesOfGoods.get(position);

        holder.idTextView.setText(typeOfGoods.getId());
        holder.nameTextView.setText(typeOfGoods.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return typesOfGoods.size();
    }

    public static class TypeOfGoodsViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, nameTextView;

        public TypeOfGoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);

        }
    }
}
