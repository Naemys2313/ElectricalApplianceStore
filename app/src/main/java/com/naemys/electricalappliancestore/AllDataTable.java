package com.naemys.electricalappliancestore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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
import com.naemys.electricalappliancestore.units.Unit;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllDataTable extends AppCompatActivity {

    private RequestQueue requestQueue;

    private RecyclerView dataRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;
    private List list;

    private String table;

    private int position;

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
                            updateData(cart, position);
                        }
                    });

                    attachAdapter(cartAdapter);

                    CartDB cartDB = new CartDB(this, list, recyclerAdapter, requestQueue);
                    cartDB.getAll();

                    break;

                case Unit.Clients.TABLE_NAME:
                    ClientAdapter clientAdapter = new ClientAdapter(this, list);
                    clientAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Client client = (Client) list.get(position);

                            updateData(client, position);
                        }
                    });

                    attachAdapter(clientAdapter);

                    ClientDB clientDB =
                            new ClientDB(this, list, recyclerAdapter, requestQueue);
                    clientDB.getAll();

                    break;

                case Unit.Delivery.TABLE_NAME:
                    DeliveryAdapter deliveryAdapter = new DeliveryAdapter(this, list);
                    deliveryAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Delivery delivery = (Delivery) list.get(position);

                            updateData(delivery, position);
                        }
                    });
                    attachAdapter(deliveryAdapter);

                    DeliveryDB deliveryDB =
                            new DeliveryDB(this, list, recyclerAdapter, requestQueue);
                    deliveryDB.getAll();

                    break;

                case Unit.Goods.TABLE_NAME:
                    GoodsAdapter goodsAdapter = new GoodsAdapter(this, list);
                    goodsAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Goods goods = (Goods) list.get(position);

                            updateData(goods, position);
                        }
                    });
                    attachAdapter(goodsAdapter);

                    GoodsDB goodsDB =
                            new GoodsDB(this, list, recyclerAdapter, requestQueue);
                    goodsDB.getAll();

                    break;

                case Unit.Orders.TABLE_NAME:
                    OrderAdapter orderAdapter = new OrderAdapter(this, list);
                    orderAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Order order = (Order) list.get(position);

                            updateData(order, position);
                        }
                    });
                    attachAdapter(orderAdapter);

                    OrderDB orderDB =
                            new OrderDB(this, list, recyclerAdapter, requestQueue);
                    orderDB.getAll();

                    break;

                case Unit.PaymentMethods.TABLE_NAME:
                    PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, list);
                    paymentMethodAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            PaymentMethod paymentMethod = (PaymentMethod) list.get(position);

                            updateData(paymentMethod, position);
                        }
                    });
                    attachAdapter(paymentMethodAdapter);

                    PaymentMethodDB paymentMethodDB =
                            new PaymentMethodDB(this, list, recyclerAdapter, requestQueue);
                    paymentMethodDB.getAll();

                    break;

                case Unit.Procurement.TABLE_NAME:
                    ProcurementAdapter procurementAdapter = new ProcurementAdapter(this, list);
                    procurementAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Procurement procurement = (Procurement) list.get(position);

                            updateData(procurement, position);
                        }
                    });
                    attachAdapter(procurementAdapter);

                    ProcurementDB procurementDB =
                            new ProcurementDB(this, list, recyclerAdapter, requestQueue);
                    procurementDB.getAll();

                    break;

                case Unit.Reviews.TABLE_NAME:
                    ReviewAdapter reviewAdapter = new ReviewAdapter(this, list);
                    reviewAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Review review = (Review) list.get(position);

                            updateData(review, position);
                        }
                    });
                    attachAdapter(reviewAdapter);

                    ReviewDB reviewDB =
                            new ReviewDB(this, list, recyclerAdapter, requestQueue);
                    reviewDB.getAll();

                    break;

                case Unit.Sale.TABLE_NAME:
                    SaleAdapter saleAdapter = new SaleAdapter(this, list);
                    saleAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Sale sale = (Sale) list.get(position);

                            updateData(sale, position);
                        }
                    });
                    attachAdapter(saleAdapter);

                    SaleDB saleDB = new SaleDB(this, list, recyclerAdapter, requestQueue);
                    saleDB.getAll();

                    break;

                case Unit.Suppliers.TABLE_NAME:
                    SupplierAdapter supplierAdapter = new SupplierAdapter(this, list);
                    supplierAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            Supplier supplier = (Supplier) list.get(position);

                            updateData(supplier, position);
                        }
                    });
                    attachAdapter(supplierAdapter);

                    SupplierDB supplierDB =
                            new SupplierDB(this, list, recyclerAdapter, requestQueue);
                    supplierDB.getAll();

                    break;

                case Unit.TypesOfGoods.TABLE_NAME:
                    TypeOfGoodsAdapter typeOfGoodsAdapter = new TypeOfGoodsAdapter(this, list);
                    typeOfGoodsAdapter.setOnClickListener(new CartAdapter.OnClickListener() {
                        @Override
                        public void onClick(int position) {
                            TypeOfGoods typeOfGoods = (TypeOfGoods) list.get(position);

                            updateData(typeOfGoods, position);
                        }
                    });
                    attachAdapter(typeOfGoodsAdapter);

                    TypeOfGoodsDB typeOfGoodsDB =
                            new TypeOfGoodsDB(this, list, recyclerAdapter, requestQueue);
                    typeOfGoodsDB.getAll();

                    break;
            }
        }
    }

    public void addData(View view) {
        Intent intent = new Intent(AllDataTable.this, AddDataActivity.class);
        intent.putExtra(Unit.TABLE_EXTRA, table);
        startActivityForResult(intent, Unit.ADD_CODE_REQUEST);
    }

    private void updateData(Model model, int position) {
        Intent intent = new Intent(AllDataTable.this, AddDataActivity.class);
        intent.putExtra(Unit.TABLE_EXTRA, table);
        intent.putExtra(Unit.UPDATE_EXTRA, true);

        this.position = position;

        HashMap<String, String> m = (HashMap) model.toMap(true);
        intent.putExtra("data", m);

        startActivityForResult(intent, Unit.UPDATE_CODE_REQUEST);
    }

    private void attachAdapter(RecyclerView.Adapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;

        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setLayoutManager(layoutManager);
        dataRecyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        HashMap<String, Model> m = (HashMap<String, Model>) data.getSerializableExtra(Unit.DATA_EXTRA);

        Model model = m.get(Unit.DATA_EXTRA);

        switch (requestCode) {
            case Unit.ADD_CODE_REQUEST:
                model.setId(data.getStringExtra(Unit._ID));

                list.add(model);

                recyclerAdapter.notifyDataSetChanged();
                break;

            case Unit.UPDATE_CODE_REQUEST:

                if(data.getBooleanExtra(Unit.UPDATE_EXTRA, true))
                    list.set(position, model);
                else
                    list.remove(position);

                recyclerAdapter.notifyDataSetChanged();

                break;
        }
    }
}
