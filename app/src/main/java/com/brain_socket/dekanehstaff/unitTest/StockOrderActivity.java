package com.brain_socket.dekanehstaff.unitTest;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class StockOrderActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, OrdersAdapter.OrderClickListener {


    @BindView(R.id.nameText)
    TextView nameText;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.guidline1)
    Guideline guidline1;
    @BindView(R.id.guidline2)
    Guideline guidline2;
    @BindView(R.id.guidline3)
    Guideline guidline3;
    @BindView(R.id.orders)
    RadioButton orders;
    @BindView(R.id.stock)
    RadioButton stock;
    @BindView(R.id.headerTab)
    RadioGroup headerTab;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.recylcerViewStock)
    RecyclerView recylcerViewStock;
    @BindView(R.id.all)
    RadioButton all;
    @BindView(R.id.pending)
    RadioButton pending;
    @BindView(R.id.packed)
    RadioButton packed;
    @BindView(R.id.tagFilter)
    RadioGroup tagFilter;
    @BindView(R.id.recylcerViewOrders)
    RecyclerView recylcerViewOrders;


    OrdersAdapter ordersAdapter;
    LinearLayoutManager lm;
    StockAdapter stockAdapter;
    LinearLayoutManager lm2;
    @BindView(R.id.stockLayout)
    ConstraintLayout stockLayout;
    @BindView(R.id.ordersLayout)
    ConstraintLayout ordersLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_stock_stuff);
        ButterKnife.bind(this);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lm2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        stockAdapter = new StockAdapter();
        ordersAdapter = new OrdersAdapter(this);

        recylcerViewOrders.setLayoutManager(lm);
        recylcerViewStock.setLayoutManager(lm2);
        recylcerViewOrders.setAdapter(ordersAdapter);
        recylcerViewStock.setAdapter(stockAdapter);

        stock.setOnCheckedChangeListener(this);
        orders.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == stock && isChecked) {
            stockLayout.setVisibility(View.VISIBLE);
            ordersLayout.setVisibility(View.GONE);
        } else if (buttonView == orders && isChecked) {
            stockLayout.setVisibility(View.GONE);
            ordersLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(int pos) {

    }
}
