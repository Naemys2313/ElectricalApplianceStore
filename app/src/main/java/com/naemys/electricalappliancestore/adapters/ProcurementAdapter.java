package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Procurement;

import java.util.List;

public class ProcurementAdapter extends RecyclerView.Adapter<ProcurementAdapter.ProcurementViewHolder> {

    private Context context;
    private List<Procurement> procurements;

    public ProcurementAdapter(Context context, List<Procurement> procurements) {
        this.context = context;
        this.procurements = procurements;
    }

    @NonNull
    @Override
    public ProcurementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_procurement, parent, false);
        return new ProcurementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProcurementViewHolder holder, int position) {
        Procurement procurement = procurements.get(position);

        holder.idTextView.setText(procurement.getId());
        holder.goodsIdTextView.setText(procurement.getGoodsId());
        holder.supplierIdTextView.setText(procurement.getSupplierId());
        holder.priceTextView.setText(procurement.getPrice());

    }

    @Override
    public int getItemCount() {
        return procurements.size();
    }

    public static class ProcurementViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, goodsIdTextView, supplierIdTextView, priceTextView;

        public ProcurementViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            goodsIdTextView = itemView.findViewById(R.id.goodsIdTextView);
            supplierIdTextView = itemView.findViewById(R.id.supplierIdTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);



        }
    }
}
