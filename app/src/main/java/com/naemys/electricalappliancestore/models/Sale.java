package com.naemys.electricalappliancestore.models;

public class Sale {
    private String id, goodsId, price, discount;

    public Sale() {
    }

    public Sale(String id, String goodsId, String price, String discount) {
        this.id = id;
        this.goodsId = goodsId;
        this.price = price;
        this.discount = discount;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}

