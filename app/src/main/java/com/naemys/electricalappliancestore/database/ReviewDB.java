package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Review;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class ReviewDB extends Database<Review> {
    public ReviewDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public ReviewDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Reviews.URL_GET_ALL, new Review());
    }

    public void create(Review review) {
        super.create(Unit.Reviews.URL_CREATE, review);
    }

    public void update(Review review) {
        super.update(Unit.Reviews.URL_UPDATE, review);
    }

    public void delete(String id) {
        Review review = new Review();
        review.setId(id);

        super.delete(Unit.Reviews.URL_DELETE, review);
    }
}
