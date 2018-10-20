package staff.dekaneh.brain_socket.com.dekanehstaff.activity.main;

import android.util.Log;
import android.view.View;

import com.androidnetworking.error.ANError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import staff.dekaneh.brain_socket.com.dekanehstaff.R;
import staff.dekaneh.brain_socket.com.dekanehstaff.application.SchedulerProvider;
import staff.dekaneh.brain_socket.com.dekanehstaff.base.BasePresenterImpl;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.AppApiHelper;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.CacheStore;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.Client;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.Order;
import staff.dekaneh.brain_socket.com.dekanehstaff.utils.NetworkUtils;

public class MainPresenter<T extends MainVP.View> extends BasePresenterImpl<T> implements MainVP.Presenter<T>, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private List<Order> orders;
    private Client selectedClient;


    @Inject
    MainPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        fetchOrders();
        fetchClients();
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
                AppApiHelper.getClients()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Client>>() {
                            @Override
                            public void accept(List<Client> clients) throws Exception {
                                getView().hideLoading();
                                getView().addClients(clients);
                                Log.d("ASDASD", "accept: " + clients);
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
                        order.setStatus("delivered");
                        break;
                    case 1:
                        order.setStatus("canceled");
                        break;
                }

                getView().dismissPopupMenu();
                updateOrder(order);
            }
        }, view, new PowerMenuItem("Delivered", false), new PowerMenuItem("Cancel", false));
    }

    @Override
    public void setClientSheet(Client client) {
        selectedClient = client;
        getView().updateClientDetailsSheet(client.getPhoneNumber(), client.getOwnerName(), client.getShopName());
    }

    @Override
    public void updateClient(String phoneNumber, String clientName, String shopName) {
        if (selectedClient != null) {
            selectedClient.setPhoneNumber(phoneNumber);
            selectedClient.setOwnerName(clientName);
            selectedClient.setShopName(shopName);
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
//        mMap.setMyLocationEnabled(true);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        for (Order order : orders) {
            if (marker.getTitle().equals(order.getClient().getShopName())) {
                getView().markItem(orders.indexOf(order));
            }
        }

        return false;
    }
}
