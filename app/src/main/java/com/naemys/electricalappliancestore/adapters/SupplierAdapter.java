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

import java.util.List;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> {

    private Context context;
    private List<Supplier> suppliers;

    public SupplierAdapter(Context context, List<Supplier> suppliers) {
        this.context = context;
        this.suppliers = suppliers;
    }

    @NonNull
    @Override
    public SupplierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_supplier, parent, false);
        return new SupplierViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierViewHolder holder, int position) {
        Supplier sale = suppliers.get(position);

        holder.idTextView.setText(sale.getId());
        holder.firstNameTextView.setText(sale.getFirstName());
        holder.lastNameTextView.setText(sale.getLastName());
        holder.middleNameTextView.setText(sale.getMiddleName());
        holder.phoneTextView.setText(sale.getPhone());

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public static class SupplierViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, firstNameTextView, lastNameTextView, middleNameTextView, phoneTextView;

        public SupplierViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
            middleNameTextView = itemView.findViewById(R.id.middleNameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
        }
    }
}
