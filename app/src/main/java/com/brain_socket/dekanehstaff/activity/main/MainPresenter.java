package com.brain_socket.dekanehstaff.activity.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;

import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.Area;
import com.brain_socket.dekanehstaff.network.model.Client;
import com.brain_socket.dekanehstaff.network.model.LocationPoint;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.utils.NetworkUtils;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenuItem;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MainPresenter<T extends MainVP.View> extends BasePresenterImpl<T> implements MainVP.Presenter<T>, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private List<Order> orders;
    private Client selectedClient;
    private List<Area> areas;


    @Inject
    MainPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        fetchOrders();
        fetchClients();
        getAreas();
        Log.i("token", "onAttach: " + getCacheStore().getSession().getAccessToken());
        Log.i("userId", "onAttach: " + getCacheStore().getSession().getUserId());
    }

    @Override
    public void fetchOrders() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getOrders()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Order>>() {
                            @Override
                            public void accept(List<Order> orders) throws Exception {

                                MainPresenter.this.orders = orders;
                                if (orders.isEmpty()) getView().showEmptyCartLogo();
                                else getView().hideEmptyCartLogo();
                                getView().hideLoading();
                                getView().addOrders(orders);
                                if (mMap != null)
                                    addMarkers();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                Log.e("ASD", "accept: ", throwable);
                            }
                        })
        );
    }

    @Override
    public void fetchClients() {
        getView().showLoading();

        getCompositeDisposable().add(
                AppApiHelper.getClients(getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Client>>() {
                            @Override
                            public void accept(List<Client> clients) throws Exception {
                                getView().hideLoading();
                                getView().addClients(clients);
                                getView().requestPermission(Manifest.permission.ACCESS_FINE_LOCATION);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                Log.e("ASD", "accept: " + NetworkUtils.getError(throwable), throwable);

                            }
                        })
        );

    }

    @Override
    public void focusOn(double lat, double lng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lng)));
    }

    @Override
    public void clearMap() {
        mMap.clear();
    }

    @Override
    public void addMarkers() {
        for (Order order : orders) {
            if (order.getClient().getLocationPoint() != null) {
                LatLng location = new LatLng(order.getClient().getLocationPoint().getLat(), order.getClient().getLocationPoint().getLng());
                MarkerOptions marker = new MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_showroom)).title(order.getClient().getShopName());
                mMap.addMarker(marker);
            }
        }
    }

    @Override
    public void onEditOrderStatus(final Order order, View view) {
        getView().showPopupMenu(new OnMenuItemClickListener() {
            @Override
            public void onItemClick(int position, Object item) {
                switch (position) {
                    case 0:
                        Log.d("ASDASD", "accept: " + " order id = " + order.getId() + " user id = " + getCacheStore().getSession().getUserId());
                        order.setStatus("delivered");
                        getView().showLoading();
                        getCompositeDisposable().add(AppApiHelper.deliver(getCacheStore().getSession().getAccessToken(), order)
                                .subscribeOn(getSchedulerProvider().io())
                                .observeOn(getSchedulerProvider().ui())
                                .subscribe(new Consumer<JsonObject>() {
                                    @Override
                                    public void accept(JsonObject jsonObject) throws Exception {
                                        getView().hideLoading();
                                        fetchOrders();
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Log.d("ASDASD", "accept: " + NetworkUtils.getError(throwable) + " id = " + order.getId());
                                    }
                                })
                        );
                        break;
                    case 1:
                        order.setStatus("canceled");
                        break;
                }

                getView().dismissPopupMenu();
//                updateOrder(order);
            }
        }, view, new PowerMenuItem("Delivered", false), new PowerMenuItem("Cancel", false));

    }

    @Override
    public void setClientSheet(final Client client) {
        selectedClient = client;
        getView().updateClientDetailsSheet(client.getPhoneNumber(), client.getOwnerName(), client.getShopName(), client.getClientType(), client.getLocation(), getAreaPositionFromId(client.getAreaId()), client.getStatus());
    }

    @Override
    public void updateClient(String phoneNumber, String clientName, String shopName, Client.Type type, Client.Status status, String areaName) {
        if (selectedClient != null) {
            selectedClient.setPhoneNumber(phoneNumber);
            selectedClient.setOwnerName(clientName);
            selectedClient.setShopName(shopName);
            selectedClient.setClientType(type);
            selectedClient.setStatus(status);
            for (int i = 0; i < areas.size(); i++) {
                if (areaName.equals(this.areas.get(i).getNameAr()))
                    selectedClient.setAreaId(this.areas.get(i).getId());
            }
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.patchClient(getCacheStore().getSession().getAccessToken(), selectedClient)
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<Client>() {
                                @Override
                                public void accept(Client client) throws Exception {
                                    getView().hideLoading();
                                    getView().showMessage("UPDATED!");
                                    fetchClients();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.e("patch client", "accept: ", throwable);
                                    ANError error = (ANError) throwable;
                                    getView().hideLoading();
                                    Log.e("patch client", "accept: " + error.getErrorBody(), throwable);
                                }
                            })
            );
        }
    }

    @Override
    public ProfileDrawerItem getProfileItem() {
        return new ProfileDrawerItem().withName(getCacheStore().getSession().getUserName()).withEmail(getCacheStore().getSession().getEmail());

    }

    @Override
    public void logout() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.logout(getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                getView().hideLoading();
                                getCacheStore().getSession().logout();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("ERRRR", "accept: " + NetworkUtils.getError(throwable), throwable);
                            }
                        })
        );
    }

    @Override
    public void moveToCurrentUserLocationWithPin(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        getView().showUpdateLocationView();
        LocationServices.getFusedLocationProviderClient(context).getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude()), 16.0f));
                selectedClient.setLocationPoint(new LocationPoint(task.getResult().getLatitude(), task.getResult().getLongitude()));
            }
        });
    }

    @Override
    public void moveToCurrentUserLocation(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions((MainActivity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        LocationServices.getFusedLocationProviderClient(context).getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.getResult() != null)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(task.getResult().getLatitude(), task.getResult().getLongitude()), 12.0f));
            }
        });
    }

    @Override
    public void setClientLocation() {
        selectedClient.setLocationPoint(new LocationPoint(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude));
        getView().hideUpdateLocationView();
    }

    @Override
    public void getAreas() {
        getView().hideLoading();
        getCompositeDisposable().add(
                AppApiHelper.getAreas().subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Area>>() {
                            @Override
                            public void accept(List<Area> areas) throws Exception {
                                getView().hideLoading();
                                MainPresenter.this.areas = areas;
                                getView().setAreaNames(getAreaNamesArray(areas));
                            }
                        })
        );
    }


    private void updateOrder(Order order) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.patchOrder(getCacheStore().getSession().getAccessToken(), order)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<Order>() {
                            @Override
                            public void accept(Order order) throws Exception {
                                getView().hideLoading();
                                fetchOrders();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
                                Log.e("PATCH", "accept: ", throwable);
                            }
                        })

        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        getView().moveToCurrentLocation();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        for (Order order : orders) {
            if (marker.getTitle() != null && marker.getTitle().equals(order.getClient().getShopName())) {
                getView().markItem(orders.indexOf(order));
            }
        }

        return false;
    }

    private String[] getAreaNamesArray(List<Area> areas) {
        String[] names = new String[areas.size()];

        for (int i = 0; i < areas.size(); i++) {
            names[i] = areas.get(i).getNameAr();
        }

        return names;
    }

    private int getAreaPositionFromId(String id) {
        if (areas != null)
            for (int i = 0; i < areas.size(); i++) {
                if (areas.get(i).getId().equals(id)) {
                    return i;
                }
            }
        return -1;
    }
}