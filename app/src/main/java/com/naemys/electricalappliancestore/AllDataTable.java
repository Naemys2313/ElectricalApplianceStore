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
import com.naemys.electricalappliancestore.request.CustomJsonObjectRequest;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        startActivity(intent);
    }

    public void updateData(Cart cart) {
        Intent intent = new Intent(AllDataTable.this, AddDataActivity.class);
        intent.putExtra(Unit.TABLE_EXTRA, table);
        intent.putExtra(Unit.UPDATE_EXTRA, true);
        intent.putExtra(Unit._ID, cart.getId());
        intent.putExtra(Unit.Carts._ORDER_ID, cart.getOrderId());
        intent.putExtra(Unit.Carts._QUANTITY, cart.getQuantity());
        intent.putExtra(Unit.Carts._GOODS_ID, cart.getGoodsId());

        startActivity(intent);
    }

    private void setCarts() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Carts.URL_GET_ALL,
                new Cart(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setClients() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Clients.URL_GET_ALL,
                new Client(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setDelivery() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Delivery.URL_GET_ALL,
                new Delivery(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setGoods() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Goods.URL_GET_ALL,
                new Goods(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setOrders() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Orders.URL_GET_ALL,
                new Order(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setPaymentMethods() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.PaymentMethods.URL_GET_ALL,
                new PaymentMethod(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setProcurement() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Procurement.URL_GET_ALL,
                new Procurement(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setReviews() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Reviews.URL_GET_ALL,
                new Review(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setSale() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Sale.URL_GET_ALL,
                new Sale(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setSupplier() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.Suppliers.URL_GET_ALL,
                new Supplier(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void setTypesOfGoods() {
        CustomJsonObjectRequest objectRequest = new CustomJsonObjectRequest (
                Unit.TypesOfGoods.URL_GET_ALL,
                new TypeOfGoods(),
                list,
                recyclerAdapter
        );

        requestQueue.add(objectRequest);
    }

    private void attachAdapter(RecyclerView.Adapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;

        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(recyclerAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);
    }
}
