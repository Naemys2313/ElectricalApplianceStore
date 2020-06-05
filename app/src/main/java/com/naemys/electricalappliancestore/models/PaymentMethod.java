package com.naemys.electricalappliancestore.models;

import com.naemys.electricalappliancestore.units.Unit;

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
    public String getTableName() {
        return Unit.PaymentMethods.TABLE_NAME;
    }
}
