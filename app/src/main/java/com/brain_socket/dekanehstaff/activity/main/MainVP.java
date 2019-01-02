package com.brain_socket.dekanehstaff.activity.main;


import android.content.Context;

import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.model.Client;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import java.util.List;


public class MainVP {

    public interface View extends BaseView {
        void addOrders(List<Order> orders);
        void addClients(List<Client> clients);
        void markItem(int position);
        void updateClientDetailsSheet(String phoneNumber, String clientName, String shopName, Client.Type type, String location, int areaPos, Client.Status status);
        void showUpdateLocationView();
        void hideUpdateLocationView();
        void setAreaNames(String[] areaNames);
        void moveToCurrentLocation();
        void hideEmptyCartLogo();
        void showEmptyCartLogo();

    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T>, OnMapReadyCallback {
        void fetchOrders();
        void fetchClients();
        void focusOn(double lat, double lng);
        void clearMap();
        void addMarkers();
        void onEditOrderStatus(Context context, Order order, android.view.View view);
        void setClientSheet(Client client);
        void updateClient(String phoneNumber, String clientName, String shopName, Client.Type type, Client.Status status, String areaName);
        ProfileDrawerItem getProfileItem();
        void logout();
        void moveToCurrentUserLocationWithPin(Context context);
        void moveToCurrentUserLocation(Context context);
        void setClientLocation();
        void getAreas();

    }

}
