package com.naemys.electricalappliancestore.models;

public class Cart {
    private String id;
    private String goodsId;
    private String quantity;
    private String orderId;

    public Cart() {
    }

    public Cart(String id, String goodsId, String quantity, String orderId) {
        this.id = id;
        this.goodsId = goodsId;
        this.quantity = quantity;
        this.orderId = orderId;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
