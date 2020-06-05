package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

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
    public String getTableName() {
        return Unit.Sale.TABLE_NAME;
    }
}

