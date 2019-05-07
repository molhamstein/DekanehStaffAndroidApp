package com.brain_socket.dekanehstaff.activity.warehouse;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.adapter.warehouse.StockCheckAdapter;
import com.brain_socket.dekanehstaff.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockCheckActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.shopOwnerNameText)
    TextView shopOwnerNameText;
    @BindView(R.id.shopOwnerName)
    TextView shopOwnerName;
    @BindView(R.id.shopNameText)
    TextView shopNameText;
    @BindView(R.id.shopName)
    TextView shopName;
    @BindView(R.id.coseText)
    TextView coseText;
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.statusText)
    TextView statusText;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.guidline1)
    Guideline guidline1;
    @BindView(R.id.recylcerView)
    RecyclerView recylcerView;
    @BindView(R.id.addButton)
    ImageView addButton;


    LinearLayoutManager layoutManager;

    @Inject
    StockCheckAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_to_right);


        setContentView(R.layout.activity_stock_check);
        ButterKnife.bind(this);


        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        recylcerView.setLayoutManager(layoutManager);
        recylcerView.setAdapter(adapter);
        addButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left);
    }

    @Override
    public void onClick(View v) {
        if(v == addButton){
            Intent intent = new Intent(this,ScanBarcodeActivity.class) ;
            startActivity(intent);
        }
    }
}
