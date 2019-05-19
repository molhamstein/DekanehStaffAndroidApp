package com.brain_socket.dekanehstaff.activity.warehouse;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.activity.warehouse.mvp.OrderDetailsPresenter;
import com.brain_socket.dekanehstaff.activity.warehouse.mvp.OrderDetailsVP;
import com.brain_socket.dekanehstaff.adapter.warehouse.StockCheckAdapter;
import com.brain_socket.dekanehstaff.base.BaseActivity;
import com.brain_socket.dekanehstaff.network.model.OrderProduct;
import com.brain_socket.dekanehstaff.network.model.Product;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsVP.View, View.OnClickListener {

    @BindView(R.id.shopOwnerNameText)
    TextView shopOwnerNameText;
    @BindView(R.id.shopOwnerName)
    TextView shopOwnerName;
    @BindView(R.id.shopNameText)
    TextView shopNameText;
    @BindView(R.id.shopName)
    TextView shopName;
    @BindView(R.id.costText)
    TextView costText;
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

    @Inject
    OrderDetailsPresenter presenter;

    private WarehouseOrder order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_to_right);


        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);


        presenter.onAttach(this);

        order = (WarehouseOrder) getIntent().getSerializableExtra("Order");

        shopName.setText(order.getWarehouse().getNameAr());
        shopOwnerName.setText(order.getWarehouseKeeper().getUsername());

        cost.setText(order.getTotalPrice().toString());
        status.setText(order.getStatus());

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recylcerView.setLayoutManager(layoutManager);
        recylcerView.setAdapter(adapter);

        adapter.addAll(order.getOrderProducts());
        addButton.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_from_right, R.anim.anim_slide_out_to_left);
    }

    @Override
    public void onClick(View v) {
        if (v == addButton) {
            Intent intent = new Intent(this, ScanBarcodeActivity.class);
            startActivityForResult(intent, 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                presenter.checkBarcode(data.getStringExtra("Code"));
            }
        }
    }

    @Override
    public void openDialog(Product product) {

        ConfirmProductDialogFragment confirmProductDialogFragment = new ConfirmProductDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Product", product);
        confirmProductDialogFragment.setArguments(bundle);
//        confirmProductDialogFragment.setTargetFragment(this,2);
        confirmProductDialogFragment.show(getSupportFragmentManager(), "Dialog");


    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void updateProduct(String productId) {
        presenter.setProductChecked(productId);
        adapter.notifyDataSetChanged();
        presenter.assignPack(order.getId());

    }


    @Override
    public List<OrderProduct> getAllProducts() {
        return order.getOrderProducts();
    }
}
