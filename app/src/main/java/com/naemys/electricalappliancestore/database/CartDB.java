package com.naemys.electricalappliancestore.database;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.naemys.electricalappliancestore.models.Cart;
import com.naemys.electricalappliancestore.units.Unit;

import java.util.List;

public class CartDB extends Database<Cart> {
    public CartDB(Activity context, RequestQueue requestQueue) {
        super(context, requestQueue);
    }

    public CartDB(Activity context, List list, RecyclerView.Adapter adapter, RequestQueue requestQueue) {
        super(context, list, adapter, requestQueue);
    }

    public void getAll() {
        super.getAll(Unit.Carts.URL_GET_ALL, new Cart());
    }

    public void create(Cart cart) {
        super.create(Unit.Carts.URL_CREATE, cart);
    }

    public void update(Cart cart) {
        super.update(Unit.Carts.URL_UPDATE, cart);
    }

    public void delete(String id) {
        Cart cart = new Cart();
        cart.setId(id);

        super.delete(Unit.Carts.URL_DELETE, cart);
    }
}
