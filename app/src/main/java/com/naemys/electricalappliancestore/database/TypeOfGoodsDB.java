package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.TypeOfGoods;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class TypeOfGoodsDB extends Database<TypeOfGoods> {
    public TypeOfGoodsDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public TypeOfGoodsDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.TypesOfGoods.URL_GET_ALL, new TypeOfGoods());
    }

    public void create(TypeOfGoods typeOfGoods) {
        super.create(Unit.TypesOfGoods.URL_CREATE, typeOfGoods);
    }

    public void update(TypeOfGoods typeOfGoods) {
        super.update(Unit.TypesOfGoods.URL_UPDATE, typeOfGoods);
    }

    public void delete(String id) {
        super.delete(Unit.TypesOfGoods.URL_DELETE, id);
    }
}
