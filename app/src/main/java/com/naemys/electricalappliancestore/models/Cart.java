package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.Map;

public class Cart extends Model<Cart> {
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

    @Override
    public Cart fromMap(Map<String, String> m) {
        Cart cart = new Cart();
        cart.setId(m.get(Unit._ID));
        cart.setOrderId(m.get(Unit.Carts._ORDER_ID));
        cart.setQuantity(m.get(Unit.Carts._QUANTITY));
        cart.setGoodsId(m.get(Unit.Carts._GOODS_ID));

        return cart;
    }

    public String getTableName() {
        return Unit.Carts.TABLE_NAME;
    }
}
