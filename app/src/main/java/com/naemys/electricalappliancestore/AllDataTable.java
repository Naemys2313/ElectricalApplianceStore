package com.naemys.electricalappliancestore;

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
import com.naemys.electricalappliancestore.units.Unit;

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

                    CartDB cartDB = new CartDB(this, list, recyclerAdapter, requestQueue);
                    cartDB.getAll();

                    break;

                case Unit.Clients.TABLE_NAME:
                    ClientAdapter clientAdapter = new ClientAdapter(this, list);
                    attachAdapter(clientAdapter);

                    ClientDB clientDB =
                            new ClientDB(this, list, recyclerAdapter, requestQueue);
                    clientDB.getAll();

                    break;

                case Unit.Delivery.TABLE_NAME:
                    DeliveryAdapter deliveryAdapter = new DeliveryAdapter(this, list);
                    attachAdapter(deliveryAdapter);

                    DeliveryDB deliveryDB =
                            new DeliveryDB(this, list, recyclerAdapter, requestQueue);
                    deliveryDB.getAll();

                    break;

                case Unit.Goods.TABLE_NAME:
                    GoodsAdapter goodsAdapter = new GoodsAdapter(this, list);
                    attachAdapter(goodsAdapter);

                    GoodsDB goodsDB =
                            new GoodsDB(this, list, recyclerAdapter, requestQueue);
                    goodsDB.getAll();

                    break;

                case Unit.Orders.TABLE_NAME:
                    OrderAdapter orderAdapter = new OrderAdapter(this, list);
                    attachAdapter(orderAdapter);

                    OrderDB orderDB =
                            new OrderDB(this, list, recyclerAdapter, requestQueue);
                    orderDB.getAll();

                    break;

                case Unit.PaymentMethods.TABLE_NAME:
                    PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(this, list);
                    attachAdapter(paymentMethodAdapter);

                    PaymentMethodDB paymentMethodDB =
                            new PaymentMethodDB(this, list, recyclerAdapter, requestQueue);
                    paymentMethodDB.getAll();

                    break;

                case Unit.Procurement.TABLE_NAME:
                    ProcurementAdapter procurementAdapter = new ProcurementAdapter(this, list);
                    attachAdapter(procurementAdapter);

                    ProcurementDB procurementDB =
                            new ProcurementDB(this, list, recyclerAdapter, requestQueue);
                    procurementDB.getAll();

                    break;

                case Unit.Reviews.TABLE_NAME:
                    ReviewAdapter reviewAdapter = new ReviewAdapter(this, list);
                    attachAdapter(reviewAdapter);

                    ReviewDB reviewDB =
                            new ReviewDB(this, list, recyclerAdapter, requestQueue);
                    reviewDB.getAll();

                    break;

                case Unit.Sale.TABLE_NAME:
                    SaleAdapter saleAdapter = new SaleAdapter(this, list);
                    attachAdapter(saleAdapter);

                    SaleDB saleDB = new SaleDB(this, list, recyclerAdapter, requestQueue);
                    saleDB.getAll();

                    break;

                case Unit.Suppliers.TABLE_NAME:
                    SupplierAdapter supplierAdapter = new SupplierAdapter(this, list);
                    attachAdapter(supplierAdapter);

                    SupplierDB supplierDB =
                            new SupplierDB(this, list, recyclerAdapter, requestQueue);
                    supplierDB.getAll();

                    break;

                case Unit.TypesOfGoods.TABLE_NAME:
                    TypeOfGoodsAdapter typeOfGoodsAdapter = new TypeOfGoodsAdapter(this, list);
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

    private void attachAdapter(RecyclerView.Adapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;

        layoutManager = new LinearLayoutManager(this);

        dataRecyclerView.setAdapter(recyclerAdapter);
        dataRecyclerView.setLayoutManager(layoutManager);
    }
}
