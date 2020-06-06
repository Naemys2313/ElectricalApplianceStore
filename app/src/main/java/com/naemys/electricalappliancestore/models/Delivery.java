package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Delivery extends Model<Delivery> {

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

    @Override
    public Delivery fromMap(Map<String, String> m) {
        Delivery delivery = new Delivery();

        delivery.setId(m.get(Unit._ID));
        delivery.setAddress(m.get(Unit.Delivery._ADDRESS));
        delivery.setDelivered(m.get(Unit.Delivery._DELIVERED));
        delivery.setDateTime(m.get(Unit.Delivery._DATE_TIME));
        delivery.setOrderId(m.get(Unit.Delivery._ORDER_ID));

        return delivery;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        m.put(Unit.Delivery._ADDRESS, getAddress());
        m.put(Unit.Delivery._DELIVERED, getDelivered());
        m.put(Unit.Delivery._DATE_TIME, getDateTime());
        m.put(Unit.Delivery._ORDER_ID, getOrderId());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.Delivery.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getAddress();
    }
}
