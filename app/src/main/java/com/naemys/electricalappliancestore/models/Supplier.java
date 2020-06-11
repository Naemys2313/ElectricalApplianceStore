package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class Supplier extends Model<Supplier> {

    private String id, firstName, lastName, middleName, phone;

    public Supplier() {
    }

    public Supplier(String id, String firstName, String lastName, String middleName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Supplier fromMap(Map<String, String> m) {
        Supplier supplier = new Supplier();
        supplier.setId(m.get(Unit._ID));
        supplier.setFirstName(m.get(Unit.Suppliers._FIRST_NAME));
        supplier.setLastName(m.get(Unit.Suppliers._LAST_NAME));
        supplier.setMiddleName(m.get(Unit.Suppliers._MIDDLE_NAME));
        supplier.setPhone(m.get(Unit.Suppliers._PHONE));

        return supplier;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        if(getFirstName() != null)
            m.put(Unit.Suppliers._FIRST_NAME, getFirstName());
        if(getLastName() != null)
            m.put(Unit.Suppliers._LAST_NAME, getLastName());
        if(getMiddleName() != null)
            m.put(Unit.Suppliers._MIDDLE_NAME, getMiddleName());
        if(getPhone() != null)
            m.put(Unit.Suppliers._PHONE, getPhone());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.Suppliers.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - [" + getLastName() + ", " + getFirstName() + "]";
    }
}
