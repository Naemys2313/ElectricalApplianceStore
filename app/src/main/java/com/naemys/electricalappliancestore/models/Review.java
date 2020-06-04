package com.naemys.electricalappliancestore.models;

public class Review {

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
}
