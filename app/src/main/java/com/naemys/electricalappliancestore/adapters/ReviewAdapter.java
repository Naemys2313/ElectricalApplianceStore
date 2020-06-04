package com.naemys.electricalappliancestore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naemys.electricalappliancestore.R;
import com.naemys.electricalappliancestore.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.idTextView.setText(review.getId());
        holder.goodsIdTextView.setText(review.getGoodsId());
        holder.clientIdTextView.setText(review.getClientId());
        holder.reviewTextTextView.setText(review.getReviewText());
        holder.ratingTextView.setText(review.getRating());


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView idTextView, goodsIdTextView, clientIdTextView, reviewTextTextView, ratingTextView;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            idTextView = itemView.findViewById(R.id.idTextView);
            goodsIdTextView = itemView.findViewById(R.id.goodsIdTextView);
            clientIdTextView = itemView.findViewById(R.id.clientIdTextView);
            reviewTextTextView = itemView.findViewById(R.id.reviewTextTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);




        }
    }
}
