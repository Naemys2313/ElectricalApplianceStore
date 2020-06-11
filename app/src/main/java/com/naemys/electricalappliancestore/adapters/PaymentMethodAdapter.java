package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.PaymentMethod;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    private Context context;
    private List<PaymentMethod> paymentMethods;

    private View view;
    private CartAdapter.OnClickListener onClickListener;

    public PaymentMethodAdapter(Context context, List<PaymentMethod> paymentMethods) {
        this.context = context;
        this.paymentMethods = paymentMethods;
    }

    public void setOnClickListener(CartAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_method, parent, false);

        this.view = view;

        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, final int position) {
        PaymentMethod paymentMethod = paymentMethods.get(position);

        holder.idTextView.setText(paymentMethod.getId());
        holder.nameTextView.setText(paymentMethod.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    public static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, nameTextView;

        public PaymentMethodViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);


        }
    }
}
