package com.naemys.electricalappliancestore.models;

public class Order {
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
}
