package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Delivery;

import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private Context context;
    private List<Delivery> deliveries;

    private View view;
    private CartAdapter.OnClickListener onClickListener;

    public DeliveryAdapter(Context context, List<Delivery> deliveries) {
        this.context = context;
        this.deliveries = deliveries;
    }

    public void setOnClickListener(CartAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_delivery, parent, false);

        this.view = view;

        return new DeliveryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, final int position) {
        Delivery delivery = deliveries.get(position);

        holder.idTextView.setText(delivery.getId());
        holder.addressTextView.setText(delivery.getAddress());
        holder.deliveredTextView.setText(delivery.getDelivered());
        holder.dateTimeTextView.setText(delivery.getDateTime());
        holder.orderIdTextView.setText(delivery.getOrderId());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveries.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class DeliveryViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, addressTextView, deliveredTextView, dateTimeTextView, orderIdTextView;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            deliveredTextView = itemView.findViewById(R.id.deliveredTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
        }
    }
}
