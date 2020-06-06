package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Procurement;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class ProcurementDB extends Database<Procurement> {
    public ProcurementDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public ProcurementDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Procurement.URL_GET_ALL, new Procurement());
    }

    public void create(Procurement procurement) {
        super.create(Unit.Procurement.URL_CREATE, procurement);
    }

    public void update(Procurement procurement) {
        super.update(Unit.Procurement.URL_UPDATE, procurement);
    }

    public void delete(String id) {
        super.delete(Unit.Procurement.URL_DELETE, id);
    }
}
