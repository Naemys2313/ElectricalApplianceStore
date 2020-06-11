package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Client;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private Context context;
    private List<Client> clients;
    private View view;

    private CartAdapter.OnClickListener onClickListener;

    public ClientAdapter(Context context, List<Client> clients) {
        this.context = context;
        this.clients = clients;
    }

    public void setOnClickListener(CartAdapter.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);

        this.view = view;

        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, final int position) {
        Client client = clients.get(position);

        holder.idTextView.setText(client.getId());
        holder.firstNameTextView.setText(client.getFirstName());
        holder.lastNameTextView.setText(client.getLastName());
        holder.middleNameTextView.setText(client.getMiddleName());
        holder.discountTextView.setText(client.getDiscount());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView, firstNameTextView, lastNameTextView, middleNameTextView, discountTextView;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            firstNameTextView = itemView.findViewById(R.id.firstNameTextView);
            lastNameTextView = itemView.findViewById(R.id.lastNameTextView);
            middleNameTextView = itemView.findViewById(R.id.middleNameTextView);
            discountTextView = itemView.findViewById(R.id.discountTextView);

        }
    }
}

