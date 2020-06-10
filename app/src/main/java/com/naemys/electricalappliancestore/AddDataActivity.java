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
import com.naemys.electricalappliancestore.database.OrderDB;
import com.naemys.electricalappliancestore.database.PaymentMethodDB;
import com.naemys.electricalappliancestore.database.ProcurementDB;
import com.naemys.electricalappliancestore.database.ReviewDB;
import com.naemys.electricalappliancestore.database.SaleDB;
import com.naemys.electricalappliancestore.database.SupplierDB;
import com.naemys.electricalappliancestore.database.TypeOfGoodsDB;
import com.naemys.electricalappliancestore.models.Cart;
import com.naemys.electricalappliancestore.models.Client;
import com.naemys.electricalappliancestore.models.Delivery;
import com.naemys.electricalappliancestore.models.Goods;
import com.naemys.electricalappliancestore.models.Model;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private String table;
    private boolean isUpdate;


    private List<Goods> goodsList;
    private List<Order> orders;
    private List<TypeOfGoods> typesOfGoods;
    private List<Client> clients;
    private List<PaymentMethod> paymentMethods;
    private List<Supplier> suppliers;

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
            isUpdate = data.getBooleanExtra(Unit.UPDATE_EXTRA, false);
        }

        goodsList = new ArrayList<>();
        orders = new ArrayList<>();
        typesOfGoods = new ArrayList<>();
        clients = new ArrayList<>();
        paymentMethods = new ArrayList<>();
        suppliers = new ArrayList<>();

        switch (table) {
            case Unit.Carts.TABLE_NAME:
                setActivityCart();
                break;

            case Unit.Clients.TABLE_NAME:
                setActivityClients();
                break;

            case Unit.Delivery.TABLE_NAME:
                setActivityDelivery();
                break;

            case Unit.Goods.TABLE_NAME:
                setActivityGoods();
                break;

            case Unit.Orders.TABLE_NAME:
                setActivityOrder();
                break;

            case Unit.PaymentMethods.TABLE_NAME:
                setActivityPaymentMethod();
                break;

            case Unit.Procurement.TABLE_NAME:
                setActivityProcurement();
                break;

            case Unit.Reviews.TABLE_NAME:
                setActivityReview();
                break;

            case Unit.Sale.TABLE_NAME:
                setActivitySale();
                break;

            case Unit.Suppliers.TABLE_NAME:
                setActivitySupplier();
                break;

            case Unit.TypesOfGoods.TABLE_NAME:
                setActivityTypeOfGoods();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isUpdate) {
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

                                if (data.getBooleanExtra(Unit.UPDATE_EXTRA, false)) {
                                    if (id.equals(m.get(Unit._ID))) {
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

    private void setActivityCart() {
        setContentView(R.layout.activity_add_cart);

        final CartDB cartDB = new CartDB(this, requestQueue);

        goodsSpinner = findViewById(R.id.goodsSpinner);
        orderSpinner = findViewById(R.id.orderSpinner);

        final TextInputEditText quantityEditText = findViewById(R.id.quantityEditText);
        addDataButton = findViewById(R.id.addDataButton);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");

        if (isUpdate) {
            invalidateOptionsMenu();

            String goodsId = m.get(Unit.Carts._GOODS_ID);
            String quantity = m.get(Unit.Carts._QUANTITY);
            String orderId = m.get(Unit.Carts._ORDER_ID);

            quantityEditText.setText(quantity);

            setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, goodsId);
            setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, orderId);
        } else {
            setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, null);
            setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, null);
        }

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsIndex = goodsSpinner.getSelectedItemPosition();
                String goodsId = goodsList.get(goodsIndex).getId();

                int orderIndex = orderSpinner.getSelectedItemPosition();
                String orderId = orders.get(orderIndex).getId();

                String quantity = quantityEditText.getText().toString().trim();

                Cart cart = new Cart(null, goodsId, quantity, orderId);
                if (isUpdate) {
                    cart.setId(m.get(Unit._ID));
                    cartDB.update(cart);
                } else {
                    cartDB.create(cart);
                }
            }
        });
    }

    private void setActivityClients() {
        setContentView(R.layout.activity_add_client);

        final ClientDB clientDB = new ClientDB(this, requestQueue);

        final TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
        final TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
        final TextInputEditText middleNameEditText = findViewById(R.id.middleNameEditText);
        final TextInputEditText discountEditText = findViewById(R.id.discountEditText);
        addDataButton = findViewById(R.id.addDataButton);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");

        if (isUpdate) {
            String firstName = m.get(Unit.Clients._FIRST_NAME);
            String lastName = m.get(Unit.Clients._LAST_NAME);
            String middleName = m.get(Unit.Clients._MIDDLE_NAME);
            String discount = m.get(Unit.Clients._DISCOUNT);

            firstNameEditText.setText(firstName);
            lastNameEditText.setText(lastName);
            middleNameEditText.setText(middleName);
            discountEditText.setText(discount);
        }

        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String middleName = middleNameEditText.getText().toString().trim();
                String discount = discountEditText.getText().toString().trim();

                Client client = new Client(null, firstName, lastName, middleName, discount);
                if (isUpdate) {
                    client.setId(m.get(Unit._ID));
                    clientDB.update(client);
                } else {
                    clientDB.create(client);
                }
            }
        });
    }

    private void setActivityDelivery() {
        setContentView(R.layout.activity_add_delivery);

        final DeliveryDB deliveryDB = new DeliveryDB(this, requestQueue);

        final TextInputEditText addressEditText = findViewById(R.id.addressEditText);
        final CheckBox deliveredCheckBox = findViewById(R.id.deliveredCheckBox);
        final TextInputEditText datetimeEditText = findViewById(R.id.dateTimeEditText);
        orderSpinner = findViewById(R.id.orderSpinner);

        if(isUpdate) {
            HashMap<String, String> m
                    = (HashMap<String, String>) data.getSerializableExtra("data");

            addressEditText.setText(m.get(Unit.Delivery._ADDRESS));

            String delivered = m.get(Unit.Delivery._DELIVERED);

            if(delivered.equals("1"))
                deliveredCheckBox.setChecked(true);
            else
                deliveredCheckBox.setChecked(false);

            datetimeEditText.setText(m.get(Unit.Delivery._DATE_TIME));

            String orderId = m.get(Unit.Delivery._ORDER_ID);

            setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, orderId);
        } else {
            setList(new Order(), orders, orderSpinner, Unit.Orders.URL_GET_ALL, null);
        }

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

                if(isUpdate) {
                    delivery.setId(data.getStringExtra(Unit._ID));
                    deliveryDB.update(delivery);
                } else {
                    deliveryDB.create(delivery);
                }
            }
        });
    }

    private void setActivityGoods() {
        setContentView(R.layout.activity_add_goods);

        final GoodsDB goodsDB = new GoodsDB(this, requestQueue);

        final TextInputEditText nameEditText = findViewById(R.id.nameEditText);
        typesOfGoodsSpinner = findViewById(R.id.typesOfGoodsSpinner);
        final TextInputEditText quantityInStockEditText
                = findViewById(R.id.quantityInStockEditText);
        final TextInputEditText descriptionEditText = findViewById(R.id.descriptionEditText);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");

        if(isUpdate) {
            nameEditText.setText(m.get(Unit.Goods._NAME));
            quantityInStockEditText.setText(m.get(Unit.Goods._QUANTITY_IN_STOCK));
            descriptionEditText.setText(m.get(Unit.Goods._DESCRIPTION));

            String typeId = m.get(Unit.Goods._TYPE_ID);
            setList(new TypeOfGoods(), typesOfGoods, typesOfGoodsSpinner, Unit.TypesOfGoods.URL_GET_ALL, typeId);
        } else {
            setList(new TypeOfGoods(), typesOfGoods, typesOfGoodsSpinner, Unit.TypesOfGoods.URL_GET_ALL, null);
        }

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

                if(isUpdate) {
                    goods.setId(m.get(Unit._ID));
                    goodsDB.update(goods);
                } else {
                    goodsDB.create(goods);
                }
            }
        });
    }

    private void setActivityOrder() {
        setContentView(R.layout.activity_add_order);

        final OrderDB orderDB = new OrderDB(this, requestQueue);

        final Spinner clientsSpinner = findViewById(R.id.clientsSpinner);
        final Spinner paymentMethodsSpinner = findViewById(R.id.paymentMethodsSpinner);
        final CheckBox paidCheckBox = findViewById(R.id.paidCheckBox);
        final TextInputEditText dateTimeEditText = findViewById(R.id.dateTimeEditText);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");
        if(isUpdate) {
            dateTimeEditText.setText(m.get(Unit.Orders._DATE_TIME));

            String paid = m.get(Unit.Orders._PAID);

            Log.d(AddDataActivity.class.getSimpleName(), "setActivityOrder: " + paid);

            if(paid.equals("1")) {
                paidCheckBox.setChecked(true);
            } else {
                paidCheckBox.setChecked(false);
            }

            String clientId = m.get(Unit.Orders._CLIENT_ID);
            String paymentMethodId = m.get(Unit.Orders._PAYMENT_METHOD_ID);

            setList(new Client(), clients, clientsSpinner, Unit.Clients.URL_GET_ALL, clientId);
            setList(new PaymentMethod(), paymentMethods, paymentMethodsSpinner, Unit.PaymentMethods.URL_GET_ALL, paymentMethodId);
        } else {
            setList(new Client(), clients, clientsSpinner, Unit.Clients.URL_GET_ALL, null);
            setList(new PaymentMethod(), paymentMethods, paymentMethodsSpinner, Unit.PaymentMethods.URL_GET_ALL, null);
        }

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clientIndex = clientsSpinner.getSelectedItemPosition();
                String clientId = clients.get(clientIndex).getId();

                int paymentMethodIndex = paymentMethodsSpinner.getSelectedItemPosition();
                String paymentMethodId = paymentMethods.get(paymentMethodIndex).getId();

                String paid = paidCheckBox.isSelected() ? "1" : "0";
                String datetime = dateTimeEditText.getText().toString().trim();

                Order order = new Order(null, clientId, paymentMethodId, paid, datetime);
                if(isUpdate) {
                    order.setId(m.get(Unit._ID));
                    orderDB.update(order);
                } else {
                    orderDB.create(order);
                }
            }
        });
    }

    private void setActivityPaymentMethod() {
        setContentView(R.layout.activity_add_payment_method);

        final PaymentMethodDB paymentMethodDB = new PaymentMethodDB(this, requestQueue);

        final TextInputEditText nameEditText = findViewById(R.id.nameEditText);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");
        if(isUpdate) {
            nameEditText.setText(m.get(Unit.PaymentMethods._NAME));
        }

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();

                PaymentMethod paymentMethod = new PaymentMethod(null, name);

                if(isUpdate) {
                    paymentMethod.setId(m.get(Unit._ID));
                    paymentMethodDB.update(paymentMethod);
                } else {
                    paymentMethodDB.create(paymentMethod);
                }
            }
        });
    }

    private void setActivityProcurement() {
        setContentView(R.layout.activity_add_procurement);

        final ProcurementDB procurementDB = new ProcurementDB(this, requestQueue);

        final Spinner goodsSpinner = findViewById(R.id.goodsSpinner);
        final Spinner suppliersSpinner = findViewById(R.id.suppliersSpinner);
        final TextInputEditText priceEditText = findViewById(R.id.priceEditText);

        final HashMap<String, String> m = (HashMap<String, String>) data.getSerializableExtra("data");

        if(isUpdate) {
            priceEditText.setText(m.get(Unit.Procurement._PRICE));

            String goodsId = m.get(Unit.Procurement._GOODS_ID);
            String supplierId = m.get(Unit.Procurement._SUPPLIER_ID);

            setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, goodsId);
            setList(new Supplier(), suppliers, suppliersSpinner, Unit.Suppliers.URL_GET_ALL, supplierId);
        } else {
            setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, null);
            setList(new Supplier(), suppliers, suppliersSpinner, Unit.Suppliers.URL_GET_ALL, null);
        }

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsIndex = goodsSpinner.getSelectedItemPosition();
                String goodsId = goodsList.get(goodsIndex).getId();

                int supplierIndex = suppliersSpinner.getSelectedItemPosition();
                String supplierId = suppliers.get(supplierIndex).getId();

                String price = priceEditText.getText().toString().trim();

                Procurement procurement = new Procurement(null, goodsId, supplierId, price);

                if(isUpdate) {
                    procurement.setId(m.get(Unit._ID));
                    procurementDB.update(procurement);
                } else {
                    procurementDB.create(procurement);
                }
            }
        });

    }

    private void setActivityReview() {
        setContentView(R.layout.activity_add_review);

        final ReviewDB reviewDB = new ReviewDB(this, requestQueue);

        final Spinner goodsSpinner = findViewById(R.id.goodsSpinner);
        final Spinner clientsSpinner = findViewById(R.id.clientsSpinner);
        final TextInputEditText reviewTextEditText = findViewById(R.id.reviewTextEditText);
        final TextInputEditText ratingEditText = findViewById(R.id.ratingEditText);

        setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, null);
        setList(new Client(), clients, clientsSpinner, Unit.Clients.URL_GET_ALL, null);

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsIndex = goodsSpinner.getSelectedItemPosition();
                String goodsId = goodsList.get(goodsIndex).getId();

                int clientIndex = clientsSpinner.getSelectedItemPosition();
                String clientId = clients.get(clientIndex).getId();

                String reviewText = reviewTextEditText.getText().toString().trim();
                String rating = ratingEditText.getText().toString().trim();

                Review review = new Review(null, goodsId, clientId, reviewText, rating);

                reviewDB.create(review);
            }
        });

    }

    private void setActivitySale() {
        setContentView(R.layout.activity_add_sale);

        final SaleDB saleDB = new SaleDB(this, requestQueue);

        final Spinner goodsSpinner = findViewById(R.id.goodsSpinner);
        final TextInputEditText priceEditText = findViewById(R.id.priceEditText);
        final TextInputEditText discountEditText = findViewById(R.id.discountEditText);

        setList(new Goods(), goodsList, goodsSpinner, Unit.Goods.URL_GET_ALL, null);

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodsIndex = goodsSpinner.getSelectedItemPosition();
                String goodsId = goodsList.get(goodsIndex).getId();

                String price = priceEditText.getText().toString().trim();
                String discount = discountEditText.getText().toString().trim();

                Sale sale = new Sale(null, goodsId, price, discount);

                saleDB.create(sale);
            }
        });
    }

    private void setActivitySupplier() {
        setContentView(R.layout.acivity_add_supplier);

        final SupplierDB supplierDB = new SupplierDB(this, requestQueue);

        final TextInputEditText firstNameEditText = findViewById(R.id.firstNameEditText);
        final TextInputEditText lastNameEditText = findViewById(R.id.lastNameEditText);
        final TextInputEditText middleNameEditText = findViewById(R.id.middleNameEditText);
        final TextInputEditText phoneEditText = findViewById(R.id.phoneEditText);

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String middleName = middleNameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                Supplier supplier = new Supplier(null, firstName, lastName, middleName, phone);

                supplierDB.create(supplier);
            }
        });

    }

    private void setActivityTypeOfGoods() {
        setContentView(R.layout.activity_add_type_of_goods);

        final TypeOfGoodsDB typeOfGoodsDB = new TypeOfGoodsDB(this, requestQueue);

        final TextInputEditText nameEditText = findViewById(R.id.nameEditText);

        addDataButton = findViewById(R.id.addDataButton);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();

                TypeOfGoods typeOfGoods = new TypeOfGoods(null, name);

                typeOfGoodsDB.create(typeOfGoods);
            }
        });
    }

    private void getCart() {

    }
}
