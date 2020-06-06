package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Supplier;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class SupplierDB extends Database<Supplier> {
    public SupplierDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public SupplierDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Suppliers.URL_GET_ALL, new Supplier());
    }

    public void create(Supplier supplier) {
        super.create(Unit.Suppliers.URL_CREATE, supplier);
    }

    public void update(Supplier supplier) {
        super.update(Unit.Suppliers.URL_UPDATE, supplier);
    }

    public void delete(String id) {
        super.delete(Unit.Suppliers.URL_DELETE, id);
    }
}
