package com.naemys.electricalappliancestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.naemys.electricalappliancestore.models.Cart;
import com.naemys.electricalappliancestore.models.Client;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.models.Order;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private String table;

    private List<Goods> goodsList;
    private List<Order> orders;

    private List<String> goodsStringList;
    private List<String> ordersStringList;

    private ArrayAdapter goodsArrayAdapter, ordersArrayAdapter;

    private Spinner goodsSpinner, orderSpinner;

    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        requestQueue = Volley.newRequestQueue(this);

        data = getIntent();
        if (data != null) {
            table = data.getStringExtra(Unit.TABLE_EXTRA);
        }


        Button addDataButton;


        switch (table) {
            case Unit.Carts.TABLE_NAME:
                setContentView(R.layout.activity_add_cart);

                goodsList = new ArrayList<>();
                orders = new ArrayList<>();
                goodsStringList = new ArrayList<>();
                ordersStringList = new ArrayList<>();

                goodsArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, goodsStringList);
                goodsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                ordersArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, ordersStringList);
                ordersArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                goodsSpinner = findViewById(R.id.goodsSpinner);
                goodsSpinner.setAdapter(goodsArrayAdapter);

                orderSpinner = findViewById(R.id.orderSpinner);
                orderSpinner.setAdapter(ordersArrayAdapter);

                final TextInputEditText quantityEditText = findViewById(R.id.quantityEditText);

                addDataButton = findViewById(R.id.addDataButton);

                if (data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
                    invalidateOptionsMenu();

                    String goodsId = data.getStringExtra(Unit.Carts._GOODS_ID);
                    String quantity = data.getStringExtra(Unit.Carts._QUANTITY);
                    String orderId = data.getStringExtra(Unit.Carts._ORDER_ID);


                    quantityEditText.setText(quantity);

                    addDataButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int goodsIndex = goodsSpinner.getSelectedItemPosition();
                            String goodsId = goodsList.get(goodsIndex).getId();

                            int orderIndex = orderSpinner.getSelectedItemPosition();
                            String orderId = orders.get(orderIndex).getId();

                            String quantity = quantityEditText.getText().toString().trim();

                            Cart cart = new Cart(data.getStringExtra(Unit._ID), goodsId, quantity, orderId);

                            updateCart(cart);
                        }
                    });

                    setGoods(goodsId);
                    setOrders(orderId);


                } else {
                    setGoods(null);
                    setOrders(null);

                    addDataButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int goodsIndex = goodsSpinner.getSelectedItemPosition();
                            String goodsId = goodsList.get(goodsIndex).getId();

                            int orderIndex = orderSpinner.getSelectedItemPosition();
                            String orderId = orders.get(orderIndex).getId();

                            String quantity = quantityEditText.getText().toString().trim();

                            Cart cart = new Cart();
                            cart.setGoodsId(goodsId);
                            cart.setOrderId(orderId);
                            cart.setQuantity(quantity);

                            createCart(cart);
                        }
                    });
                }

                break;

            case Unit.Clients.TABLE_NAME:
                setContentView(R.layout.activity_add_client);

                final TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
                final TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
                final TextInputEditText middleNameEditText = findViewById(R.id.middleNameEditText);
                final TextInputEditText discountEditText = findViewById(R.id.discountEditText);

                addDataButton = findViewById(R.id.addDataButton);
                addDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String firstName = firstNameEditText.getText().toString().trim();
                        String lastName = lastNameEditText.getText().toString().trim();
                        String middleName = middleNameEditText.getText().toString().trim();
                        String discount = discountEditText.getText().toString().trim();


                        Log.d("asdasd", lastName + " " + firstName);
                        Client client = new Client();
                        client.setFirstName(firstName);
                        client.setLastName(lastName);
                        client.setMiddleName(middleName);
                        client.setDiscount(discount);

                        createClient(client);
                    }
                });

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
            getMenuInflater().inflate(R.menu.update_menu, menu);
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.deleteItemMenu) {
            deleteCart();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setGoods(final String goodsId) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Goods.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Goods.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String name = jsonObject.getString(Unit.Goods._NAME);
                                String typeId = jsonObject.getString(Unit.Goods._TYPE_ID);
                                String quantityInStock = jsonObject.getString(Unit.Goods._QUANTITY_IN_STOCK);
                                String description = jsonObject.getString(Unit.Goods._DESCRIPTION);


                                Goods goods = new Goods(id, name, typeId, quantityInStock, description);

                                goodsList.add(goods);

                                goodsStringList.add(goods.getId() + " - " + goods.getName());

                                goodsArrayAdapter.notifyDataSetChanged();
                            }

                            if (data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
                                for (int i = 0; i < goodsList.size(); i++) {
                                    if (goodsList.get(i).getId().equals(goodsId)) {
                                        goodsSpinner.setSelection(i);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(objectRequest);
    }

    private void setOrders(final String orderId) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Orders.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Orders.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String clientId = jsonObject.getString(Unit.Orders._CLIENT_ID);
                                String paymentMethodId = jsonObject.getString(Unit.Orders._PAYMENT_METHOD_ID);
                                String paid = jsonObject.getString(Unit.Orders._PAID);
                                String dateTime = jsonObject.getString(Unit.Orders._DATE_TIME);


                                Order order = new Order(id, clientId, paymentMethodId, paid, dateTime);

                                orders.add(order);

                                ordersStringList.add(order.getId());

                                ordersArrayAdapter.notifyDataSetChanged();
                            }

                            if (data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
                                for (int i = 0; i < orders.size(); i++) {
                                    if (orders.get(i).getId().equals(orderId)) {
                                        orderSpinner.setSelection(i);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(objectRequest);
    }

    private void createCart(final Cart cart) {

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.Carts.URL_CREATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(AddDataActivity.class.getSimpleName(), "onResponse: " + response);
                        if (response.equals("{\"success\":1,\"message\":\"Cart successfully created.\"}")) {
                            Toast.makeText(AddDataActivity.this, "Cart successfully created.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddDataActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put(Unit.Carts._GOODS_ID, cart.getGoodsId());
                m.put(Unit.Carts._QUANTITY, cart.getQuantity());
                m.put(Unit.Carts._ORDER_ID, cart.getOrderId());
                return m;
            }
        };

        requestQueue.add(stringRequest);

    }

    void updateCart(final Cart cart) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.Carts.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(AddDataActivity.class.getSimpleName(), "onResponse: " + response);
                        if (response.equals("{\"success\":1,\"message\":\"Updated success.\"}")) {
                            Toast.makeText(AddDataActivity.this, "Cart successfully updated.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddDataActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put(Unit._ID, cart.getId());
                m.put(Unit.Carts._GOODS_ID, cart.getGoodsId());
                m.put(Unit.Carts._QUANTITY, cart.getQuantity());
                m.put(Unit.Carts._ORDER_ID, cart.getOrderId());
                return m;
            }
        };

        requestQueue.add(stringRequest);
    }

    void deleteCart() {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.Carts.URL_DELETE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(AddDataActivity.class.getSimpleName(), "onResponse: " + response);
                        if (response.equals("{\"success\":1,\"message\":\"Deleted success.\"}")) {
                            Toast.makeText(AddDataActivity.this, "Cart successfully deleted.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddDataActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put(Unit._ID, data.getStringExtra(Unit._ID));
                return m;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void createClient(final Client client) {

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST,
                Unit.Clients.URL_CREATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(AddDataActivity.class.getSimpleName(), "onResponse: " + response);
                        if (response.equals("{\"success\":1,\"message\":\"Client successfully created.\"}")) {
                            Toast.makeText(AddDataActivity.this, "Client successfully created.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddDataActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> m = new HashMap<>();
                m.put(Unit.Clients._FIRST_NAME, client.getFirstName());
                m.put(Unit.Clients._LAST_NAME, client.getLastName());
                m.put(Unit.Clients._MIDDLE_NAME, client.getMiddleName());
                m.put(Unit.Clients._DISCOUNT, client.getDiscount());
                return m;
            }
        };

        requestQueue.add(stringRequest);

    }
}
