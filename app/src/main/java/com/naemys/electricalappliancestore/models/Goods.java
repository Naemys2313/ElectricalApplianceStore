package com.naemys.electricalappliancestore.models;

public class Goods {
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
}
