package com.naemys.electricalappliancestore.models;

public class Delivery {
    private String id, address, delivered, dateTime, orderId;

    public Delivery() {
    }

    public Delivery(String id, String address, String delivered, String dateTime, String orderId) {
        this.id = id;
        this.address = address;
        this.delivered = delivered;
        this.dateTime = dateTime;
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
