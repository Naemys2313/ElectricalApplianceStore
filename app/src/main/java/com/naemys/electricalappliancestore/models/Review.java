package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Review extends Model<Review> {

    private String id, goodsId, clientId, reviewText, rating;

    public Review() {
    }

    public Review(String id, String goodsId, String clientId, String reviewText, String rating) {
        this.id = id;
        this.goodsId = goodsId;
        this.clientId = clientId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public Review fromMap(Map<String, String> m) {
        Review review = new Review();
        review.setId(m.get(Unit._ID));
        review.setClientId(m.get(Unit.Reviews._CLIENT_ID));
        review.setGoodsId(m.get(Unit.Reviews._GOODS_ID));
        review.setReviewText(m.get(Unit.Reviews._REVIEW_TEXT));
        review.setRating(m.get(Unit.Reviews._RATING));

        return review;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        if(getClientId() != null)
            m.put(Unit.Reviews._CLIENT_ID, getClientId());
        if(getGoodsId() != null)
            m.put(Unit.Reviews._GOODS_ID, getGoodsId());
        if(getReviewText() != null)
            m.put(Unit.Reviews._REVIEW_TEXT, getReviewText());
        if(getRating() != null)
            m.put(Unit.Reviews._RATING, getRating());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.Reviews.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getReviewText();
    }
}
