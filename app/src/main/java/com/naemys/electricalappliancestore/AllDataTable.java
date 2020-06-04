package com.naemys.electricalappliancestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naemys.electricalappliancestore.adapters.CartAdapter;
import com.naemys.electricalappliancestore.adapters.ClientAdapter;
import com.naemys.electricalappliancestore.adapters.DeliveryAdapter;
import com.naemys.electricalappliancestore.adapters.GoodsAdapter;
import com.naemys.electricalappliancestore.adapters.OrderAdapter;
import com.naemys.electricalappliancestore.adapters.PaymentMethodAdapter;
import com.naemys.electricalappliancestore.adapters.ProcurementAdapter;
import com.naemys.electricalappliancestore.adapters.ReviewAdapter;
import com.naemys.electricalappliancestore.adapters.SaleAdapter;
import com.naemys.electricalappliancestore.adapters.SupplierAdapter;
import com.naemys.electricalappliancestore.adapters.TypeOfGoodsAdapter;
import com.naemys.electricalappliancestore.models.Cart;
import com.naemys.electricalappliancestore.models.Client;
import com.naemys.electricalappliancestore.models.Delivery;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.models.Order;
import com.naemys.electricalappliancestore.models.PaymentMethod;
import com.naemys.electricalappliancestore.models.Procurement;
import com.naemys.electricalappliancestore.models.Review;
import com.naemys.electricalappliancestore.models.Sale;
import com.naemys.electricalappliancestore.models.Supplier;
import com.naemys.electricalappliancestore.models.TypeOfGoods;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllDataTable extends AppCompatActivity {

    private RequestQueue requestQueue;

    private RecyclerView dataRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private List list;

    private String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_data_table);

        Intent data = getIntent();

        requestQueue = Volley.newRequestQueue(this);

        list = new ArrayList();

        dataRecyclerView = findViewById(R.id.dataRecyclerView);

        if (data != null) {
            table = data.getStringExtra(Unit.TABLE_EXTRA);
        }

        if (table != null) {
            switch (table) {
                case Unit.Carts.TABLE_NAME:
                    CartAdapter cartAdapter = new CartAdapter(this, list);
                    cartAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Cart cart = (Cart) list.get(position);
                            updateData(cart);
                        }
                    });

                    attachAdapter(cartAdapter);
                    setCarts();
                    break;

                case Unit.Clients.TABLE_NAME:
                    ClientAdapter clientAdapter = new ClientAdapter(this, list);
                    attachAdapter(clientAdapter);
                    setClients();
                    break;

                case Unit.Delivery.TABLE_NAME:
                    DeliveryAdapter deliveryAdapter = new DeliveryAdapter(this, list);
                    attachAdapter(deliveryAdapter);
                    setDelivery();
                    break;

                case Unit.Goods.TABLE_NAME:
                    GoodsAdapter goodsAdapter = new GoodsAdapter(this, list);
                    attachAdapter(goodsAdapter);
                    setGoods();
                    break;

                case Unit.Orders.TABLE_NAME:
                    OrderAdapter orderAdapter = new OrderAdapter(this, list);
                    attachAdapter(orderAdapter);
                    setOrders();
                    break;

                case Unit.PaymentMethods.TABLE_NAME:
                    PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, list);
                    attachAdapter(paymentMethodAdapter);
                    setPaymentMethods();
                    break;

                case Unit.Procurement.TABLE_NAME:
                    ProcurementAdapter procurementAdapter = new ProcurementAdapter(this, list);
                    attachAdapter(procurementAdapter);
                    setProcurement();
                    break;

                case Unit.Reviews.TABLE_NAME:
                    ReviewAdapter reviewAdapter = new ReviewAdapter(this, list);
                    attachAdapter(reviewAdapter);
                    setReviews();
                    break;

                case Unit.Sale.TABLE_NAME:
                    SaleAdapter saleAdapter = new SaleAdapter(this, list);
                    attachAdapter(saleAdapter);
                    setSale();
                    break;

                case Unit.Suppliers.TABLE_NAME:
                    SupplierAdapter supplierAdapter = new SupplierAdapter(this, list);
                    attachAdapter(supplierAdapter);
                    setSupplier();
                    break;

                case Unit.TypesOfGoods.TABLE_NAME:
                    TypeOfGoodsAdapter typeOfGoodsAdapter = new TypeOfGoodsAdapter(this, list);
                    attachAdapter(typeOfGoodsAdapter);
                    setTypesOfGoods();
                    break;
            }
        }



    }

    public void addData(View view) {
        Intent intent = new Intent(AllDataTable.this, AddDataActivity.class);
        intent.putExtra(Unit.TABLE_EXTRA, table);
        startActivityForResult(intent, 0);
    }

    public void updateData(Cart cart) {
        Intent intent = new Intent(AllDataTable.this, AddDataActivity.class);
        intent.putExtra(Unit.TABLE_EXTRA, table);
        intent.putExtra(Unit.UPDATE_EXTRA, true);
        intent.putExtra(Unit._ID, cart.getId());
        intent.putExtra(Unit.Carts._ORDER_ID, cart.getOrderId());
        intent.putExtra(Unit.Carts._QUANTITY, cart.getQuantity());
        intent.putExtra(Unit.Carts._GOODS_ID, cart.getGoodsId());

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (table.equals(Unit.Carts.TABLE_NAME)) {
                list = new ArrayList();

                setCarts();

                recyclerAdapter.notifyDataSetChanged();
            } else if (table.equals(Unit.Clients.TABLE_NAME)) {
                list = new ArrayList();

                setClients();
                recyclerAdapter.notifyDataSetChanged();
            }

        }
    }

    private void setCarts() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Carts.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Carts.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject cartJsonObject = array.getJSONObject(i);

                                String id = cartJsonObject.getString(Unit._ID);
                                String goodsId = cartJsonObject.getString(Unit.Carts._GOODS_ID);
                                String quantity = cartJsonObject.getString(Unit.Carts._QUANTITY);
                                String orderId = cartJsonObject.getString(Unit.Carts._ORDER_ID);

                                Cart cart = new Cart(id, goodsId, quantity, orderId);

                                list.add(cart);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setClients() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Clients.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("client");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String firstName = jsonObject.getString(Unit.Clients._FIRST_NAME);
                                String lastName = jsonObject.getString(Unit.Clients._LAST_NAME);
                                String middleName = jsonObject.getString(Unit.Clients._MIDDLE_NAME);
                                String discount = jsonObject.getString(Unit.Clients._DISCOUNT);


                                Client client = new Client(id, firstName, lastName, middleName, discount);

                                list.add(client);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setDelivery() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Delivery.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Delivery.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String address = jsonObject.getString(Unit.Delivery._ADDRESS);
                                String delivered = jsonObject.getString(Unit.Delivery._DELIVERED);
                                String dateTime = jsonObject.getString(Unit.Delivery._DATE_TIME);
                                String orderId = jsonObject.getString(Unit.Delivery._ORDER_ID);


                                Delivery delivery = new Delivery(id, address, delivered, dateTime, orderId);

                                list.add(delivery);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setGoods() {
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

                                list.add(goods);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setOrders() {
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

                                list.add(order);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setPaymentMethods() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.PaymentMethods.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.PaymentMethods.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String name = jsonObject.getString(Unit.PaymentMethods._NAME);

                                PaymentMethod paymentMethod = new PaymentMethod(id, name);

                                list.add(paymentMethod);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setProcurement() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Procurement.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Procurement.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String goodsId = jsonObject.getString(Unit.Procurement._GOODS_ID);
                                String supplierId = jsonObject.getString(Unit.Procurement._SUPPLIER_ID);
                                String price = jsonObject.getString(Unit.Procurement._PRICE);

                                Procurement procurement = new Procurement(id, goodsId, supplierId, price);

                                list.add(procurement);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setReviews() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Reviews.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Reviews.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String goodsId = jsonObject.getString(Unit.Reviews._GOODS_ID);
                                String clientId = jsonObject.getString(Unit.Reviews._CLIENT_ID);
                                String reviewText = jsonObject.getString(Unit.Reviews._REVIEW_TEXT);
                                String rating = jsonObject.getString(Unit.Reviews._RATING);

                                Review review = new Review(id, goodsId, clientId, reviewText, rating);

                                list.add(review);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setSale() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Sale.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Sale.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String goodsId = jsonObject.getString(Unit.Sale._GOODS_ID);
                                String price = jsonObject.getString(Unit.Sale._PRICE);
                                String discount = jsonObject.getString(Unit.Sale._DISCOUNT);


                                Sale sale = new Sale(id, goodsId, price, discount);

                                list.add(sale);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setSupplier() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.Suppliers.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(Unit.Suppliers.TABLE_NAME);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String firstName = jsonObject.getString(Unit.Suppliers._FIRST_NAME);
                                String lastName = jsonObject.getString(Unit.Suppliers._LAST_NAME);
                                String middleName = jsonObject.getString(Unit.Suppliers._MIDDLE_NAME);
                                String phone = jsonObject.getString(Unit.Suppliers._PHONE);

                                Supplier supplier = new Supplier(id, firstName, lastName, middleName, phone);

                                list.add(supplier);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void setTypesOfGoods() {
        JsonObjectRequest objectRequest = new JsonObjectRequest(JsonObjectRequest.Method.GET,
                Unit.TypesOfGoods.URL_GET_ALL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("types");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                String id = jsonObject.getString(Unit._ID);
                                String name = jsonObject.getString(Unit.TypesOfGoods._NAME);

                                TypeOfGoods typeOfGoods = new TypeOfGoods(id, name);

                                list.add(typeOfGoods);
                                recyclerAdapter.notifyDataSetChanged();
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

    private void attachAdapter(RecyclerView.Adapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;

        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(recyclerAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);
    }
}
