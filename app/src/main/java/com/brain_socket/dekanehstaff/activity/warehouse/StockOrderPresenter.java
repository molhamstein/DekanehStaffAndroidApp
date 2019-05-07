package com.brain_socket.dekanehstaff.activity.warehouse;

import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class StockOrderPresenter<T extends StockOrderVP.View> extends BasePresenterImpl<T> implements StockOrderVP.Presenter<T> {
    private List<Order> orders;
    private List<WareHouseProduct> wareHouseProducts;

    @Inject
    public StockOrderPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void getOrders() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getWarehouseOrders()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Order>>() {
                            @Override
                            public void accept(List<Order> orders) throws Exception {

                                StockOrderPresenter.this.orders = orders;
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
    public void getStock() {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.getWarehouseStock()
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<WareHouseProduct>>() {
                                       @Override
                                       public void accept(List<WareHouseProduct> wareHouseProducts) throws Exception {
                                           StockOrderPresenter.this.wareHouseProducts = wareHouseProducts;
                                           if (wareHouseProducts.isEmpty())
                                               getView().showEmptyStockIcon();
                                           else
                                               getView().hideEmptyStockIcon();
                                           getView().hideLoading();
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
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        getOrders();
    }


}
