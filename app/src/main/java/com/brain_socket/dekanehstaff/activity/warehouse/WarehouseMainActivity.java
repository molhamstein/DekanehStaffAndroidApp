package com.brain_socket.dekanehstaff.activity.warehouse;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.activity.warehouse.mvp.WarehouseMainPresenter;
import com.brain_socket.dekanehstaff.activity.warehouse.mvp.WarehouseMainVP;
import com.brain_socket.dekanehstaff.adapter.warehouse.StockAdapter;
import com.brain_socket.dekanehstaff.adapter.warehouse.WarehouseOrdersAdapter;
import com.brain_socket.dekanehstaff.base.BaseActivity;
import com.brain_socket.dekanehstaff.network.model.User;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.Enums;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WarehouseMainActivity extends BaseActivity implements
        CompoundButton.OnCheckedChangeListener,
        WarehouseMainVP.View {


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
    @BindView(R.id.search)
    AppCompatEditText search;
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

    @BindView(R.id.stockLayout)
    ConstraintLayout stockLayout;
    @BindView(R.id.ordersLayout)
    ConstraintLayout ordersLayout;

    @BindView(R.id.refreshStock)
    SwipeRefreshLayout refreshStock;
    @BindView(R.id.refreshOrders)
    SwipeRefreshLayout refreshOrders;

    @BindView(R.id.noResultOrders)
    ConstraintLayout noResultOrders;

    @BindView(R.id.noResultStock)
    ConstraintLayout noResultStock;


    @Inject
    WarehouseOrdersAdapter ordersAdapter;
    @Inject
    StockAdapter stockAdapter;


    LinearLayoutManager lm;
    LinearLayoutManager lm2;

    @Inject
    WarehouseMainPresenter presenter;

    final private Integer limit = 10;
    @BindView(R.id.pendingDelivery)
    RadioButton pendingDelivery;
    @BindView(R.id.emptyCartImg)
    ImageView emptyCartImg;
    @BindView(R.id.noResultText)
    TextView noResultText;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    private Integer pageId = 0;


    private RadioButton.OnCheckedChangeListener onCheckedChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse_main);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);




        presenter.onAttach(this);


    }

    @Override
    public void setupMainView(String userName) {

        name.setText(userName);
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorAccent)
                .addProfiles(
                        presenter.getProfileItem()
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mainToolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(4).withName(R.string.logout)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        switch ((int) drawerItem.getIdentifier()) {

                            case 4:

                                presenter.logout();
                                break;
                        }

                        return false;
                    }
                })
                .build();
    }

    @Override
    public void setupOrdersLayout() {
        ordersLayout.setVisibility(View.VISIBLE);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recylcerViewOrders.setLayoutManager(lm);
        recylcerViewOrders.setAdapter(ordersAdapter);
        orders.setOnCheckedChangeListener(this);

        refreshOrders.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ordersAdapter.reset();
                presenter.getOrders();
            }
        });

        onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Enums.WarehouseStatuses status;
                if (all.isChecked())
                    status = Enums.WarehouseStatuses.all;
                else if (pending.isChecked())
                    status = Enums.WarehouseStatuses.inWarehouse;
                else if (pendingDelivery.isChecked())
                    status = Enums.WarehouseStatuses.pendingDelivery;
                else
                    status = Enums.WarehouseStatuses.packed;

                presenter.filterOrders(status);
            }
        };

        all.setOnCheckedChangeListener(onCheckedChangeListener);
        pending.setOnCheckedChangeListener(onCheckedChangeListener);
        pendingDelivery.setOnCheckedChangeListener(onCheckedChangeListener);
        packed.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    @Override
    public void setupStockLayout() {
        stockLayout.setVisibility(View.GONE);
        lm2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recylcerViewStock.setLayoutManager(lm2);
        recylcerViewStock.setAdapter(stockAdapter);
        recylcerViewStock.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = lm2.getChildCount();
                int totalItemCount = lm2.getItemCount();
                int firstVisibleItemPosition = lm2.findFirstVisibleItemPosition();
                if (!isLoading())
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= limit) {
                        pageId++;
                        presenter.getStock(limit, pageId * limit);
                    }
            }

        });

        stock.setOnCheckedChangeListener(this);

        refreshStock.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageId = 0;
                stockAdapter.reset();
                presenter.getStock(limit, pageId * limit);
            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == stock && isChecked) {
            stockLayout.setVisibility(View.VISIBLE);
            ordersLayout.setVisibility(View.GONE);
            stockAdapter.reset();
            presenter.getStock(limit, pageId * limit);
        } else if (buttonView == orders && isChecked) {
            stockLayout.setVisibility(View.GONE);
            ordersLayout.setVisibility(View.VISIBLE);
            ordersAdapter.reset();
            presenter.getOrders();
        }
    }

    @Override
    public void onOrderClicked(WarehouseOrder order) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("Order", order);
        startActivity(intent);
    }

    @Override
    public void addOrders(List<WarehouseOrder> orders) {
        ordersAdapter.addAllOrders(orders);
    }

    @Override
    public void addWareHouseProducts(List<WareHouseProduct> wareHouseProducts) {
        stockAdapter.addAll(wareHouseProducts);
    }

    @Override
    public void hideEmptyOrdersIcon() {
        noResultOrders.setVisibility(View.GONE);
        recylcerViewOrders.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyOrdersIcon() {
        noResultOrders.setVisibility(View.VISIBLE);
        recylcerViewOrders.setVisibility(View.GONE);

    }

    @Override
    public void hideEmptyStockIcon() {
        noResultStock.setVisibility(View.GONE);
        recylcerViewStock.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyStockIcon() {
        noResultStock.setVisibility(View.VISIBLE);
        recylcerViewStock.setVisibility(View.GONE);
        search.setVisibility(View.GONE);
    }

    @Override
    public void stopOrdersRefreshing() {
        refreshOrders.setRefreshing(false);
    }

    @Override
    public void stopStockRefreshing() {
        refreshStock.setRefreshing(false);
    }

    @Override
    public void getFilteredOrders(Enums.WarehouseStatuses status) {
        ordersAdapter.notifyDataSetChanged();
        ordersAdapter.getFilter().filter(status.toString());
    }
}
