package com.naemys.electricalappliancestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naemys.electricalappliancestore.units.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        requestQueue = Volley.newRequestQueue(this);

        /*requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST,
                "https://elecronicapplicatestore.000webhostapp.com/create_client.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("json", response);
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
                m.put("name", "name");
                m.put("surname", "surname");
                m.put("middle_name", "middle_name");
                m.put("discout", "0");
                return m;
            }
        };

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);*/

    }

    public void goToTypesOfGoods(View view) {
        Intent typesOfGoodsIntent = new Intent(MainActivity.this, AllDataTable.class);
        typesOfGoodsIntent.putExtra(Unit.TABLE_EXTRA, Unit.TypesOfGoods.TABLE_NAME);

        startActivity(typesOfGoodsIntent);
    }

    public void goToPaymentMethods(View view) {
        Intent paymentMethodsIntent = new Intent(MainActivity.this, AllDataTable.class);
        paymentMethodsIntent.putExtra(Unit.TABLE_EXTRA, Unit.PaymentMethods.TABLE_NAME);

        startActivity(paymentMethodsIntent);
    }

    public void goToGoods(View view) {
        Intent goodsInten = new Intent(MainActivity.this, AllDataTable.class);
        goodsInten.putExtra(Unit.TABLE_EXTRA, Unit.Goods.TABLE_NAME);

        startActivity(goodsInten);
    }

    public void goToClients(View view) {
        Intent clientIntent = new Intent(MainActivity.this, AllDataTable.class);
        clientIntent.putExtra(Unit.TABLE_EXTRA, Unit.Clients.TABLE_NAME);

        startActivity(clientIntent);
    }

    public void goToReviews(View view) {
        Intent reviewsIntent = new Intent(MainActivity.this, AllDataTable.class);
        reviewsIntent.putExtra(Unit.TABLE_EXTRA, Unit.Reviews.TABLE_NAME);

        startActivity(reviewsIntent);
    }

    public void goToSuppliers(View view) {
        Intent suppliersIntent = new Intent(MainActivity.this, AllDataTable.class);
        suppliersIntent.putExtra(Unit.TABLE_EXTRA, Unit.Suppliers.TABLE_NAME);

        startActivity(suppliersIntent);
    }

    public void goToSale(View view) {
        Intent saleIntent = new Intent(MainActivity.this, AllDataTable.class);
        saleIntent.putExtra(Unit.TABLE_EXTRA, Unit.Sale.TABLE_NAME);

        startActivity(saleIntent);
    }

    public void goToProcurements(View view) {
        Intent procurementsIntent = new Intent(MainActivity.this, AllDataTable.class);
        procurementsIntent.putExtra(Unit.TABLE_EXTRA, Unit.Procurement.TABLE_NAME);

        startActivity(procurementsIntent);
    }

    public void goToOrders(View view) {
        Intent ordersIntent = new Intent(MainActivity.this, AllDataTable.class);
        ordersIntent.putExtra(Unit.TABLE_EXTRA, Unit.Orders.TABLE_NAME);

        startActivity(ordersIntent);
    }

    public void goToCarts(View view) {
        Intent cartsIntent = new Intent(MainActivity.this, AllDataTable.class);
        cartsIntent.putExtra(Unit.TABLE_EXTRA, Unit.Carts.TABLE_NAME);

        startActivity(cartsIntent);
    }

    public void goToDelivery(View view) {
        Intent deliveryIntent = new Intent(MainActivity.this, AllDataTable.class);
        deliveryIntent.putExtra(Unit.TABLE_EXTRA, Unit.Delivery.TABLE_NAME);

        startActivity(deliveryIntent);
    }
}
