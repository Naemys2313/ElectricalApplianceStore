package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.PaymentMethod;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class PaymentMethodDB extends Database<PaymentMethod> {
    public PaymentMethodDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public PaymentMethodDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.PaymentMethods.URL_GET_ALL, new PaymentMethod());
    }

    public void create(PaymentMethod paymentMethod) {
        super.create(Unit.PaymentMethods.URL_CREATE, paymentMethod);
    }

    public void update(PaymentMethod paymentMethod) {
        super.update(Unit.PaymentMethods.URL_UPDATE, paymentMethod);
    }

    public void delete(String id) {
        super.delete(Unit.PaymentMethods.URL_DELETE, id);
    }
}
