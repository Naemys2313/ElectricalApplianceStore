package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.Map;

public class Client extends Model<Client> {
    private String id, firstName, lastName, middleName, discount;

    public Client() {
    }

    public Client(String id, String firstName, String lastName, String middleName, String discount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public Client fromMap(Map<String, String> m) {
        Client client = new Client();
        client.setId(m.get(Unit._ID));
        client.setFirstName(m.get(Unit.Clients._FIRST_NAME));
        client.setLastName(m.get(Unit.Clients._LAST_NAME));
        client.setMiddleName(m.get(Unit.Clients._MIDDLE_NAME));
        client.setDiscount(m.get(Unit.Clients._DISCOUNT));

        return client;
    }

    @Override
    public String getTableName() {
        return "client";
    }
}
