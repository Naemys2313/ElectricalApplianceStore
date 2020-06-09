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
import android.widget.CheckBox;
import android.widget.Spinner;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.naemys.electricalappliancestore.database.CartDB;
import com.naemys.electricalappliancestore.database.ClientDB;
import com.naemys.electricalappliancestore.database.DeliveryDB;
import com.naemys.electricalappliancestore.database.GoodsDB;
import com.naemys.electricalappliancestore.models.Cart;
import com.naemys.electricalappliancestore.models.Client;
import com.naemys.electricalappliancestore.models.Delivery;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.models.Model;
import com.naemys.electricalappliancestore.models.Order;
import com.naemys.electricalappliancestore.models.TypeOfGoods;
import com.naemys.electricalappliancestore.request.CustomJsonObjectRequest;
import com.naemys.electricalappliancestore.request.CustomJsonStringRequest;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private String table;

    private List<Goods> goodsList;
    private List<Order> orders;
    private List<TypeOfGoods> typesOfGoods;

    private ArrayAdapter<String> goodsArrayAdapter, ordersArrayAdapter, typesOfGoodsArrayAdapter;

    private Spinner goodsSpinner, orderSpinner, typesOfGoodsSpinner;

    private Intent data;

    private Button addDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        requestQueue = Volley.newRequestQueue(this);

        data = getIntent();
        if (data != null) {
            table = data.getStringExtra(Unit.TABLE_EXTRA);
        }

        goodsList = new ArrayList<>();
        orders = new ArrayList<>();
        typesOfGoods = new ArrayList<>();

        switch (table) {
            case Unit.Carts.TABLE_NAME:
                setContentView(R.layout.activity_add_cart);

                final CartDB cartDB = new CartDB(this, requestQueue);

                goodsSpinner = findViewById(R.id.goodsSpinner);
                orderSpinner = findViewById(R.id.orderSpinner);

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

                            cartDB.update(cart);
                        }
                    });

                    setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, goodsId);
                    setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, orderId);
                } else {
                    setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, null);
                    setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, null);

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

                            cartDB.create(cart);
                        }
                    });
                }

                break;

            case Unit.Clients.TABLE_NAME:
                setContentView(R.layout.activity_add_client);

                final ClientDB clientDB = new ClientDB(this, requestQueue);

                final TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
                final TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
                final TextInputEditText middleNameEditText = findViewById(R.id.middleNameEditText);
                final TextInputEditText discountEditText = findViewById(R.id.discountEditText);
                addDataButton = findViewById(R.id.addDataButton);

                addDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = data.getStringExtra(Unit._ID);
                        String firstName = firstNameEditText.getText().toString().trim();
                        String lastName = lastNameEditText.getText().toString().trim();
                        String middleName = middleNameEditText.getText().toString().trim();
                        String discount = discountEditText.getText().toString().trim();

                        Client client = new Client();
                        client.setId(id);
                        client.setFirstName(firstName);
                        client.setLastName(lastName);
                        client.setMiddleName(middleName);
                        client.setDiscount(discount);

                        clientDB.create(client);
                    }
                });

                break;

            case Unit.Delivery.TABLE_NAME:
                setContentView(R.layout.activity_add_delivery);

                final DeliveryDB deliveryDB = new DeliveryDB(this, requestQueue);

                final TextInputEditText addressEditText = findViewById(R.id.addressEditText);
                final CheckBox deliveredCheckBox = findViewById(R.id.deliveredCheckBox);
                final TextInputEditText datetimeEditText = findViewById(R.id.dateTimeEditText);
                orderSpinner = findViewById(R.id.orderSpinner);

                setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, null);

                addDataButton = findViewById(R.id.addDataButton);
                addDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String address = addressEditText.getText().toString().trim();
                        String delivered = deliveredCheckBox.isChecked() ? "1" : "0";
                        String datetime = datetimeEditText.getText().toString().trim();

                        int orderIndex = orderSpinner.getSelectedItemPosition();
                        String orderId = orders.get(orderIndex).getId();

                        Delivery delivery =
                                new Delivery(null, address, delivered, datetime, orderId);

                        deliveryDB.create(delivery);
                    }
                });

                break;

            case Unit.Goods.TABLE_NAME:
                setContentView(R.layout.activity_add_goods);

                final GoodsDB goodsDB = new GoodsDB(this, requestQueue);

                final TextInputEditText nameEditText = findViewById(R.id.nameEditText);
                typesOfGoodsSpinner = findViewById(R.id.typesOfGoodsSpinner);
                final TextInputEditText quantityInStockEditText
                        = findViewById(R.id.quantityInStockEditText);
                final TextInputEditText descriptionEditText = findViewById(R.id.descriptionEditText);

                setList(new TypeOfGoods(), typesOfGoods, typesOfGoodsSpinner, Unit.TypesOfGoods.URL_GET_ALL, null);

                addDataButton = findViewById(R.id.addDataButton);
                addDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = nameEditText.getText().toString().trim();

                        int typeIndex = typesOfGoodsSpinner.getSelectedItemPosition();
                        String typeId = typesOfGoods.get(typeIndex).getId();

                        String quantityInStock = quantityInStockEditText.getText().toString()
                                .trim();
                        String description = descriptionEditText.getText().toString().trim();

                        Goods goods
                                = new Goods(null, name, typeId, quantityInStock, description);

                        goodsDB.create(goods);
                    }
                });

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
            getMenuInflater().inflate(R.menu.update_menu, menu);
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteItemMenu) {
            CartDB cartDB = new CartDB(this, requestQueue);
            cartDB.delete(data.getStringExtra(Unit._ID));
        }
        return super.onOptionsItemSelected(item);
    }

    private void setList(final Model model,
                         final List models,
                         final Spinner spinner,
                         String url,
                         final String id) {

        final List<String> modelsStringList = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                modelsStringList
        );

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                JsonObjectRequest.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(model.getTableName());

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                Map<String, String> m = CustomJsonObjectRequest.toMap(jsonObject);
                                Model mod = (Model) model.fromMap(m);

                                models.add(mod);
                                modelsStringList.add(mod.toString());

                                arrayAdapter.notifyDataSetChanged();

                                if(data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
                                    if(id.equals(m.get(Unit._ID))) {
                                        spinner.setSelection(i);
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
                }
        );

        requestQueue.add(objectRequest);
    }
}
