package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Goods extends Model<Goods> {
    private String id, name, typeId, quantityInStock, description;

    public Goods() {
    }

    public Goods(String id, String name, String typeId, String quantityInStock, String description) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.quantityInStock = quantityInStock;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(String quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goods fromMap(Map<String, String> m) {
        Goods goods = new Goods();
        goods.setId(m.get(Unit._ID));
        goods.setName(m.get(Unit.Goods._NAME));
        goods.setTypeId(m.get(Unit.Goods._TYPE_ID));
        goods.setQuantityInStock(m.get(Unit.Goods._QUANTITY_IN_STOCK));
        goods.setDescription(m.get(Unit.Goods._DESCRIPTION));

        return goods;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        if(getName() != null)
            m.put(Unit.Goods._NAME, getName());
        if(getTypeId() != null)
            m.put(Unit.Goods._TYPE_ID, getTypeId());
        if(getQuantityInStock() != null)
            m.put(Unit.Goods._QUANTITY_IN_STOCK, getQuantityInStock());
        if(getDescription() != null)
            m.put(Unit.Goods._DESCRIPTION, getDescription());

        return m;
    }

    public String getTableName() {

        return Unit.Goods.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
