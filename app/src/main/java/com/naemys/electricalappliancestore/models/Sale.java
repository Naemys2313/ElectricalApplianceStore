package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Sale extends Model<Sale> {
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

    @Override
    public Sale fromMap(Map<String, String> m) {
        Sale sale = new Sale();
        sale.setId(m.get(Unit._ID));
        sale.setGoodsId(m.get(Unit.Sale._GOODS_ID));
        sale.setPrice(m.get(Unit.Sale._PRICE));
        sale.setDiscount(m.get(Unit.Sale._DISCOUNT));

        return sale;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        if(getGoodsId() != null)
            m.put(Unit.Sale._GOODS_ID, getGoodsId());
        if(getPrice() != null)
            m.put(Unit.Sale._PRICE, getPrice());
        if(getDiscount() != null)
            m.put(Unit.Sale._DISCOUNT, getDiscount());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.Sale.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId();
    }
}

