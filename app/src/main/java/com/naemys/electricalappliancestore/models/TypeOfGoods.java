package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class TypeOfGoods extends Model<TypeOfGoods> {
    private String id, name;

    public TypeOfGoods() {
    }

    public TypeOfGoods(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public TypeOfGoods fromMap(Map<String, String> m) {
        TypeOfGoods typeOfGoods = new TypeOfGoods();
        typeOfGoods.setId(m.get(Unit._ID));
        typeOfGoods.setName(m.get(Unit.TypesOfGoods._NAME));

        return typeOfGoods;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        m.put(Unit.TypesOfGoods._NAME, getName());

        return m;
    }

    @Override
    public String getTableName() {
        return "types";
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
