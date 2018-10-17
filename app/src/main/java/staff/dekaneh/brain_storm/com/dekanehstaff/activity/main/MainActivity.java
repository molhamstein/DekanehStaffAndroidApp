package staff.dekaneh.brain_storm.com.dekanehstaff.activity.main;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import staff.dekaneh.brain_storm.com.dekanehstaff.R;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.ClientsAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.ItemsAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.OrdersAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BaseActivity;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Client;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.Order;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.model.OrderItem;

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


    LinearLayoutManager linearLayoutManager;
    BottomSheetBehavior bottomSheetBehavior;
    BottomSheetBehavior orderDetailsBottomSheetBehavior;
    BottomSheetBehavior clientDetailsBottomSheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getActivityComponent() != null)
            getActivityComponent().inject(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(presenter);
        presenter.onAttach(this);
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
                presenter.onEditOrderStatus(order, view);
            }

            @Override
            public void onItemClick(List<OrderItem> orderItems, String shopName) {
                itemsAdapter.addAllItems(orderItems);
                orderDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Orders");
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Clients");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        item2,
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Logout")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch (position) {
                            case 0:
                                listTitle.setText("Orders");
                                recyclerView.setAdapter(ordersAdapter);
                                presenter.addMarkers();
                                break;
                            case 1:
                                listTitle.setText("Clients");
                                recyclerView.setAdapter(clientsAdapter);
                                presenter.clearMap();
                        }

                        return false;
                    }
                })
                .build();
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
    public void updateClientDetailsSheet(String phoneNumber, String clientName, String shopName) {
        clientPhoneNumber.setText(phoneNumber);
        clientShopName.setText(shopName);
        this.clientName.setText(clientName);
    }

    @OnClick(R.id.updateClientBtn)
    public void onUpdateClientBtnClicked() {
        presenter.updateClient(clientPhoneNumber.getText().toString(),
                clientName.getText().toString(),
                clientShopName.getText().toString());
    }

    @OnClick(R.id.clientEditCloseBtn)
    public void onClientEditCloseBtn() {
        clientDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @OnClick(R.id.orderDetailsCloseBtn)
    public void onOrderDetailsCloseBtn() {
        orderDetailsBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
