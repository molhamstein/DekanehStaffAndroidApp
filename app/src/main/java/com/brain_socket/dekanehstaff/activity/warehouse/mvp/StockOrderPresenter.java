package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.WareHouseProduct;
import com.brain_socket.dekanehstaff.network.model.WarehouseOrder;
import com.brain_socket.dekanehstaff.utils.WarehouseStatuses;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class StockOrderPresenter extends BasePresenterImpl<StockOrderVP.View> implements StockOrderVP.Presenter<StockOrderVP.View> {


    @Inject
    public StockOrderPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
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
    public void filterOrders(WarehouseStatuses status) {
        getView().getFilteredOrders(status);
    }


    @Override
    public void onAttach(StockOrderVP.View mvpView) {
        super.onAttach(mvpView);

        getView().setupOrdersLayout();
        getView().setupStockLayout();

        getOrders();
    }


}
