package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class GoodsDB extends Database<Goods>  {
    public GoodsDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public GoodsDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Goods.URL_GET_ALL, new Goods());
    }

    public void create(Goods goods) {
        super.create(Unit.Goods.URL_CREATE, goods);
    }

    public void update(Goods goods) {
        super.update(Unit.Goods.URL_UPDATE, goods);
    }

    public void delete(String id) {
        super.delete(Unit.Goods.URL_DELETE, id);
    }
}
