package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.activity.warehouse.WarehouseMainActivity;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.brain_socket.dekanehstaff.network.model.Warehouse;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.Enums;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class WarehouseMainPresenter extends BasePresenterImpl<WarehouseMainVP.View> implements WarehouseMainVP.Presenter<WarehouseMainVP.View> {


    @Inject
    public WarehouseMainPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public ProfileDrawerItem getProfileItem() {
        return new ProfileDrawerItem().withName(getCacheStore().getSession().getUserName()).withEmail(getCacheStore().getSession().getEmail());

    }

    @Override
    public void onAttach(WarehouseMainVP.View mvpView) {
        super.onAttach(mvpView);

        getView().setupOrdersLayout();
        getView().setupStockLayout();
        getView().setupMainView(getCacheStore().getSession().getUserName());

        getOrders();
    }

    @Override
    public void getOrders() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getWarehouseOrders(getCacheStore().getSession().getUserId())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<WarehouseOrder>>() {
                            @Override
                            public void accept(List<WarehouseOrder> orders) throws Exception {

                                if (orders.isEmpty())
                                    getView().showEmptyOrdersIcon();
                                else
                                    getView().hideEmptyOrdersIcon();
                                getView().hideLoading();

                                getView().addOrders(orders);

                                getView().stopOrdersRefreshing();


                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getView().hideLoading();
//                                Log.e("ASD", "accept: ", throwable);
                            }
                        })
        );
    }

    @Override
    public void getStock(Integer limit, Integer skip) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getWarehouseStock(limit, skip)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<WareHouseProduct>>() {
                                       @Override
                                       public void accept(List<WareHouseProduct> wareHouseProducts) throws Exception {

                                           if (wareHouseProducts.isEmpty())
                                               getView().showEmptyStockIcon();
                                           else
                                               getView().hideEmptyStockIcon();

                                           getView().hideLoading();
                                           getView().stopStockRefreshing();
                                           getView().addWareHouseProducts(wareHouseProducts);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           getView().hideLoading();
                                       }
                                   }
                        ));
    }

    @Override
    public void searchStock(String keyword,Integer limit, Integer skip) {
        getView().showSearchLoader();
        getCompositeDisposable().add(
                AppApiHelper.searchWarehouseStock(keyword,limit,skip)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<WareHouseProduct>>() {
                                       @Override
                                       public void accept(List<WareHouseProduct> wareHouseProducts) throws Exception {

                                           if (wareHouseProducts.isEmpty())
                                               getView().showEmptyStockIcon();
                                           else
                                               getView().hideEmptyStockIcon();

                                           getView().hideSearchLoader();
                                           getView().stopStockRefreshing();
                                           getView().addWareHouseProducts(wareHouseProducts);
                                       }
                                   }, new Consumer<Throwable>() {
                                       @Override
                                       public void accept(Throwable throwable) throws Exception {
                                           getView().hideSearchLoader();
                                       }
                                   }
                        ));
    }

    @Override
    public void filterOrders(Enums.WarehouseStatuses status) {
        getView().getFilteredOrders(status);
    }


    @Override
    public void logout() {
        final Context context = (WarehouseMainActivity) getView();
        final AlertDialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.are_you_sure)).setPositiveButton(context.getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
//                                Log.e("ERRRR", "accept: " + NetworkUtils.getError(throwable), throwable);
                                    }
                                })
                );

            }
        })
                .setNegativeButton(context.getString(R.string.cancel), null).create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

            }
        });
        dialog.show();

        TextView textView = (TextView) dialog.getWindow().findViewById(android.R.id.message);
        textView.setTextColor(ContextCompat.getColor(context, R.color.brown));


    }


}
