package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

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
    public String getTableName() {
        return "types";
    }
}
