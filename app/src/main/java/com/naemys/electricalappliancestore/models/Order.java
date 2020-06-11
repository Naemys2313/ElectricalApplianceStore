package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Order extends Model<Order> {
    private String id, clientId, paymentMethodId, paid, dateTime;

    public Order() {
    }

    public Order(String id, String clientId, String paymentMethodId, String paid, String dateTime) {
        this.id = id;
        this.clientId = clientId;
        this.paymentMethodId = paymentMethodId;
        this.paid = paid;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public Order fromMap(Map<String, String> m) {
        Order order = new Order();
        order.setId(m.get(Unit._ID));
        order.setClientId(m.get(Unit.Orders._CLIENT_ID));
        order.setPaymentMethodId(m.get(Unit.Orders._PAYMENT_METHOD_ID));
        order.setPaid(m.get(Unit.Orders._PAID));
        order.setDateTime(m.get(Unit.Orders._DATE_TIME));

        return order;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        if(getClientId() != null)
            m.put(Unit.Orders._CLIENT_ID, getClientId());
        if(getPaymentMethodId() != null)
            m.put(Unit.Orders._PAYMENT_METHOD_ID, getPaymentMethodId());
        if(getPaid() != null)
            m.put(Unit.Orders._PAID, getPaid());
        if(getDateTime() != null)
            m.put(Unit.Orders._DATE_TIME, getDateTime());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.Orders.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId();
    }
}
