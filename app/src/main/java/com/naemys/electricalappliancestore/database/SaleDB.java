package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Sale;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class SaleDB extends Database<Sale> {
    public SaleDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public SaleDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Sale.URL_GET_ALL, new Sale());
    }

    public void create(Sale sale) {
        super.create(Unit.Sale.URL_CREATE, sale);
    }

    public void update(Sale sale) {
        super.update(Unit.Sale.URL_UPDATE, sale);
    }

    public void delete(String id) {
        super.delete(Unit.Sale.URL_DELETE, id);
    }
}
