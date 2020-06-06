package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Model;
import com.naemys.electricalappliancestore.request.CustomJsonObjectRequest;
import com.naemys.electricalappliancestore.request.CustomJsonStringRequest;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Database<T> {
    private Activity context;
    private List list;
    private RecyclerView.Adapter adapter;
    private RequestQueue requestQueue;

    Database(Activity context, RequestQueue requestQueue) {
        this.context = context;
        this.requestQueue = requestQueue;
    }

    Database(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        this.context = context;
        this.list = list;
        this.adapter = adapter;
        this.requestQueue = requestQueue;
    }

    void getAll(String url, Model model) {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest(
                url,
                model,
                list,
                adapter
        );

        requestQueue.add(objectRequest);
    }

    void create(String url, Model<T> model) {
        CustomJsonStringRequest stringRequest = new CustomJsonStringRequest(
                context,
                url,
                model.toMap(false)
        );

        requestQueue.add(stringRequest);
    }

    void update(String url, Model<T> model) {
        CustomJsonStringRequest stringRequest = new CustomJsonStringRequest(
                context,
                url,
                model.toMap(true)
        );

        requestQueue.add(stringRequest);
    }

    void delete(String url, String id) {
        Map<String, String> m = new HashMap<>();
        m.put(Unit._ID, id);

        CustomJsonStringRequest stringRequest = new CustomJsonStringRequest(
                context,
                url,
                m
        );

        requestQueue.add(stringRequest);
    }
}
