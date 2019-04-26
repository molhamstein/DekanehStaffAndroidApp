package com.brain_socket.dekanehstaff.unitTest;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("Registered")
public class TestActivity extends AppCompatActivity {


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
    @BindView(R.id.all)
    RadioButton all;
    @BindView(R.id.pending)
    RadioButton pending;
    @BindView(R.id.packed)
    RadioButton packed;
    @BindView(R.id.tagFilter)
    RadioGroup tagFilter;
    @BindView(R.id.searchOrFilterLayout)
    ConstraintLayout searchOrFilterLayout;

    @BindView(R.id.recylcerView)
    RecyclerView recylcerView;

    OrdersAdapter adapter;
    LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_stock_stuff);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new OrdersAdapter();
        recylcerView.setLayoutManager(layoutManager);
        recylcerView.setAdapter(adapter);

    }
}
