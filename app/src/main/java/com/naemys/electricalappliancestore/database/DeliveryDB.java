package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Delivery;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class DeliveryDB extends Database<Delivery> {
    public DeliveryDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public DeliveryDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Delivery.URL_GET_ALL, new Delivery());
    }

    public void create(Delivery delivery) {
        super.create(Unit.Delivery.URL_CREATE, delivery);
    }

    public void update(Delivery delivery) {
        super.update(Unit.Delivery.URL_UPDATE, delivery);
    }

    public void delete(String id) {
        Delivery delivery = new Delivery();
        delivery.setId(id);

        super.delete(Unit.Delivery.URL_DELETE, delivery);
    }
}
