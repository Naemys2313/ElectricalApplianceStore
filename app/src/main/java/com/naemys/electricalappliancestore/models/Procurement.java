package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.Map;

public class Procurement extends Model<Procurement> {

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

    @Override
    public Procurement fromMap(Map<String, String> m) {
        Procurement procurement = new Procurement();
        procurement.setId(m.get(Unit._ID));
        procurement.setGoodsId(m.get(Unit.Procurement._GOODS_ID));
        procurement.setSupplierId(m.get(Unit.Procurement._SUPPLIER_ID));
        procurement.setPrice(m.get(Unit.Procurement._PRICE));

        return procurement;
    }

    @Override
    public String getTableName() {
        return Unit.Procurement.TABLE_NAME;
    }
}
