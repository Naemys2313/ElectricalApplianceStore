package com.naemys.electricalappliancestore.models;

public class Procurement {

    private String id, goodsId, supplierId, price;

    public Procurement() {
    }

    public Procurement(String id, String goodsId, String supplierId, String price) {
        this.id = id;
        this.goodsId = goodsId;
        this.supplierId = supplierId;
        this.price = price;
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
