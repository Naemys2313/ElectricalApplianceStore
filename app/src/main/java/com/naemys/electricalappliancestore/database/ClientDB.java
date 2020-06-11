package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Client;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class ClientDB extends Database<Client> {
    public ClientDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public ClientDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Clients.URL_GET_ALL, new Client());
    }

    public void create(Client model) {
        super.create(Unit.Clients.URL_CREATE, model);
    }

    public void update(Client model) {
        super.update(Unit.Clients.URL_UPDATE, model);
    }

    public void delete(String id) {
        Client client = new Client();
        client.setId(id);

        super.delete(Unit.Clients.URL_DELETE, client);
    }
}
