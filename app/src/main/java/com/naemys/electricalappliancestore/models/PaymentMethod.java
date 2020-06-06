package com.naemys.electricalappliancestore.models;

import androidx.annotation.NonNull;

import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.Map;

public class PaymentMethod extends Model<PaymentMethod> {

    private String id, name;

    public PaymentMethod() {
    }

    public PaymentMethod(String id, String name) {
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
    public PaymentMethod fromMap(Map<String, String> m) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(m.get(Unit._ID));
        paymentMethod.setName(m.get(Unit.PaymentMethods._NAME));

        return paymentMethod;
    }

    @Override
    public Map<String, String> toMap(Boolean withId) {
        Map<String, String> m = new HashMap<>();
        if(withId)
            m.put(Unit._ID, getId());
        m.put(Unit.PaymentMethods._NAME, getName());

        return m;
    }

    @Override
    public String getTableName() {
        return Unit.PaymentMethods.TABLE_NAME;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
