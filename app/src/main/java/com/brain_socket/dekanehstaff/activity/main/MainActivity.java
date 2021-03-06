package com.brain_socket.dekanehstaff.activity.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.adapter.ClientsAdapter;
import com.brain_socket.dekanehstaff.adapter.ItemsAdapter;
import com.brain_socket.dekanehstaff.adapter.OrdersAdapter;
import com.brain_socket.dekanehstaff.base.BaseActivity;
import com.brain_socket.dekanehstaff.network.model.Client;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.OrderItem;
import com.google.android.gms.maps.SupportMapFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainVP.View {

    @Inject
    MainVP.Presenter<MainVP.View> presenter;
    @Inject
    OrdersAdapter ordersAdapter;
    @Inject
    ItemsAdapter itemsAdapter;
    @Inject
    ClientsAdapter clientsAdapter;


    @BindView(R.id.bottomSheet)
    View bottomSheet;
    @BindView(R.id.mainRV)
    RecyclerView recyclerView;
    @BindView(R.id.mainToolbar)
    Toolbar toolbar;
    @BindView(R.id.detailsBottomSheet)
    View detailsBottomSheet;
    @BindView(R.id.detailsRV)
    RecyclerView detailsRV;
    @BindView(R.id.listTitle)
    TextView listTitle;
    @BindView(R.id.clientDetailsBottomSheet)
    View clientDetailsBottomSheet;
    @BindView(R.id.clientName)
    EditText clientName;
    @BindView(R.id.clientPhoneNumber)
    EditText clientPhoneNumber;
    @BindView(R.id.clientShopName)
    EditText clientShopName;
    @BindView(R.id.clientNotesString)
    EditText clientNotesString;
    @BindView(R.id.shopName)
    TextView shopName;
    @BindView(R.id.updateClientLocationBtn)
    Button updateClientLocationBtn;
    @BindView(R.id.clientTypeSpinner)
    Spinner clientTypeSpinner;
    @BindView(R.id.clientStatusSpinner)
    Spinner clientStatusSpinner;
    @BindView(R.id.centerLocationPointer)
    View centerLocationPointer;
    @BindView(R.id.clientLocationString)
    EditText clientLocationString;
    @BindView(R.id.clientAreasSpinner)
    Spinner clientAreasSpinner;
    @BindView(R.id.emptyCartImg)
    View emptyCartImg;

    LinearLayoutManager linearLayoutManager;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior orderDetailsBottomSheetBehavior;
    BottomSheetBehavior clientDetailsBottomSheetBehavior;

    String[] areaNames;


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        presenter.onAttach(this);
        mapFragment.getMapAsync(presenter);
        linearLayoutManager = new LinearLayoutManager(this);


        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        orderDetailsBottomSheetBehavior = BottomSheetBehavior.from(detailsBottomSheet);
        orderDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        clientDetailsBottomSheetBehavior = BottomSheetBehavior.from(clientDetailsBottomSheet);
        clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(ordersAdapter);
        detailsRV.setLayoutManager(new LinearLayoutManager(this));
        detailsRV.setAdapter(itemsAdapter);
        ordersAdapter.setOnOrderClickListener(new OrdersAdapter.OnOrderClickListener() {

            @Override
            public void onLocationClick(double lat, double lng) {
                presenter.focusOn(lat, lng);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

            @Override
            public void onEditClick(Order order, View view) {
                presenter.onEditOrderStatus(MainActivity.this, order, view);
            }

            @Override
            public void onItemClick(List<OrderItem> orderItems, String shopName) {
                openOrderDetailsBottomSheet(orderItems, shopName, false);
            }

        });

        clientsAdapter.setOnClientClickListener(new ClientsAdapter.OnClientClickListener() {
            @Override
            public void onClick(Client client) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                presenter.setClientSheet(client);
            }
        });


        setSpinners();


        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.orders);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.new_clients);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.all_orders);

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
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(3).withName(R.string.logout)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        switch ((int) drawerItem.getIdentifier()) {
                            case 1:
                                listTitle.setText(R.string.orders);
                                recyclerView.setAdapter(ordersAdapter);
                                if (ordersAdapter.empty())
                                    emptyCartImg.setVisibility(View.VISIBLE);
                                presenter.addMarkers();
                                break;
                            case 2:
                                listTitle.setText(R.string.new_clients);
                                recyclerView.setAdapter(clientsAdapter);
                                presenter.clearMap();
                                emptyCartImg.setVisibility(View.GONE);
                                break;
                            case 3:
                                openOrderDetailsBottomSheet(presenter.aggregatedOrders(), getString(R.string.all_orders), true);
                                break;
                            case 4:
                                presenter.logout();
                                break;
                        }

                        return false;
                    }
                })
                .build();

    }

    private void openOrderDetailsBottomSheet(List<OrderItem> orderItems, String shopName, boolean allOrders) {
        itemsAdapter.setDockaanPrice(allOrders);
        itemsAdapter.addAllItems(orderItems);
        MainActivity.this.shopName.setText(shopName);
        orderDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    @Override
    public void addOrders(List<Order> orders) {
        ordersAdapter.addAllOrder(orders);
    }

    @Override
    public void addClients(List<Client> clients) {
        clientsAdapter.addAllClients(clients);
    }

    @Override
    public void markItem(int position) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        linearLayoutManager.scrollToPosition(position);
    }

    @Override
    public void updateClientDetailsSheet(String phoneNumber, String clientName, String shopName, Client.Type type, String location, int areaPos, Client.Status status, String notes) {
        clientPhoneNumber.setText(phoneNumber);
        clientShopName.setText(shopName);
        this.clientName.setText(clientName);
        clientTypeSpinner.setSelection(type == Client.Type.horeca ? 0 : 1);
        clientLocationString.setText(location);
        clientNotesString.setText(notes);
        int index = 0;
        switch (status) {
            case activated:
                index = 0;
                break;
            case deactivated:
                index = 1;
                break;
            case pending:
                index = 2;
        }

        clientStatusSpinner.setSelection(index);
        clientAreasSpinner.setSelection(areaPos);
    }

    @Override
    public void showUpdateLocationView() {
        clientDetailsBottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setHideable(true);
        clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        updateClientLocationBtn.animate().translationY(0).start();
        centerLocationPointer.setVisibility(View.VISIBLE);
        toolbar.animate().alpha(0).start();

    }

    @Override
    public void hideUpdateLocationView() {
        clientDetailsBottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        updateClientLocationBtn.animate().translationY(300).start();
        clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        centerLocationPointer.setVisibility(View.GONE);
        toolbar.animate().alpha(1).start();

    }

    @Override
    public void setAreaNames(String[] areaNames) {
        this.areaNames = areaNames;

        ArrayAdapter<String> areasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, areaNames);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientAreasSpinner.setAdapter(areasAdapter);

    }

    @Override
    public void moveToCurrentLocation() {
        presenter.moveToCurrentUserLocation(this);
    }

    @Override
    public void hideEmptyCartLogo() {
        emptyCartImg.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyCartLogo() {
        emptyCartImg.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.updateClientBtn)
    public void onUpdateClientBtnClicked() {
        Client.Type type = clientTypeSpinner.getSelectedItemPosition() == 0 ? Client.Type.horeca : Client.Type.wholesale;
        Client.Status status;

        switch (clientStatusSpinner.getSelectedItemPosition()) {
            case 0:
                status = Client.Status.activated;
                break;
            case 1:
                status = Client.Status.deactivated;
                break;
            case 2:
                status = Client.Status.pending;
                break;
            default:
                status = Client.Status.pending;
                break;
        }

        String selectedArea = "";
        if (areaNames.length > 0) {
            selectedArea = areaNames[clientAreasSpinner.getSelectedItemPosition()];
        }

        presenter.updateClient(clientPhoneNumber.getText().toString(),
                clientName.getText().toString(),
                clientShopName.getText().toString(),
                type,
                status,
                selectedArea,
                clientNotesString.getText().toString()
        );
    }

    @OnClick(R.id.clientEditCloseBtn)
    public void onClientEditCloseBtn() {
        clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.orderDetailsCloseBtn)
    public void onOrderDetailsCloseBtn() {
        orderDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.clientLocationPoint)
    public void onEditClientLocationClicked() {
        presenter.moveToCurrentUserLocationWithPin(this);

    }

    @OnClick(R.id.updateClientLocationBtn)
    public void onUpdateClientLocationBtnClicked() {
        presenter.setClientLocation();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.fetchClients();
                presenter.fetchOrders();
                return false;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setSpinners() {
        List<String> types = new ArrayList<>();
        types.add(getResources().getString(R.string.horeca));
        types.add(getResources().getString(R.string.whole_sale));
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientTypeSpinner.setAdapter(typesAdapter);

        List<String> statuses = new ArrayList<>();
        statuses.add(getResources().getString(R.string.activated));
        statuses.add(getResources().getString(R.string.deactivated));
        statuses.add(getResources().getString(R.string.pending));
        ArrayAdapter<String> statusesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statuses);
        statusesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clientStatusSpinner.setAdapter(statusesAdapter);

    }

    @Override
    public void onBackPressed() {
        if (toolbar.getAlpha() == 0) {
            hideUpdateLocationView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                presenter.moveToCurrentUserLocation(this);
            }
        }
    }
}
