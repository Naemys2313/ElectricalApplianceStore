package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.idTextView.setText(order.getId());
        holder.paymentMethodIdTextView.setText(order.getPaymentMethodId());
        holder.paidTextView.setText(order.getPaid());
        holder.dateTimeTextView.setText(order.getDateTime());
        holder.clientIdTextView.setText(order.getClientId());

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, clientIdTextView, paymentMethodIdTextView, paidTextView, dateTimeTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            paymentMethodIdTextView = itemView.findViewById(R.id.paymentMethodIdTextView);
            paidTextView = itemView.findViewById(R.id.paidTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
            clientIdTextView = itemView.findViewById(R.id.clientIdTextView);


        }
    }
}
