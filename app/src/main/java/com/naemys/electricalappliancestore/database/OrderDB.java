package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Order;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class OrderDB extends Database<Order> {
    public OrderDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public OrderDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Orders.URL_GET_ALL, new Order());
    }

    public void create(Order order) {
        super.create(Unit.Orders.URL_CREATE, order);
    }

    public void update(Order order) {
        super.update(Unit.Orders.URL_UPDATE, order);
    }

    public void delete(String id) {
        super.delete(Unit.Orders.URL_DELETE, id);
    }
}
