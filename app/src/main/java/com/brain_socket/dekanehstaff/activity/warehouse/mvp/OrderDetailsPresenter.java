package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import android.support.v7.app.AlertDialog;

import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.Barcode;
import com.brain_socket.dekanehstaff.network.model.Message;
import com.brain_socket.dekanehstaff.network.model.OrderProduct;
import com.brain_socket.dekanehstaff.network.model.Report;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class OrderDetailsPresenter extends BasePresenterImpl<OrderDetailsVP.View> implements OrderDetailsVP.Presenter<OrderDetailsVP.View> {


    @Inject
    public OrderDetailsPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void checkBarcode(String Barcode) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.checkBarcode(Barcode)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<List<Barcode>>() {
                            @Override
                            public void accept(List<Barcode> barcodes) throws Exception {
                                getView().hideLoading();
                                if (barcodes.size() > 0)
                                    getView().openDialog(barcodes.get(0).getProduct());
                                else {
                                    new AlertDialog.Builder(getView().getActivity())
                                            .setMessage("لا يوجد أي منتج موافق للرمز")
                                            .setNeutralButton("حسنا", null).show();

                                }


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


//    @Override
//    public Integer searchForProduct(String productId) {
//        List<OrderProduct> products = getView().getAllProducts();
//        for (int i = 0; i < products.size(); i++) {
//            if(products.get(i).getProductId().equals(productId))
//                return i ;
//        }
//        return -1;
//    }


    @Override
    public void setProductChecked(String productId,String orderId) {
        getCacheStore().getSession().setProductChecked(productId+orderId);
    }

    private Boolean orderIsPacked() {
        List<OrderProduct> products = getView().getAllProducts();
        for (OrderProduct product : products) {
            if (!getCacheStore().getSession().getProductChecked(product.getProductId()))
                return false;
        }

        return true;
    }

    @Override
    public void assignPack(String orderId) {
        if (orderIsPacked()) {
            getView().showLoading();
            getCompositeDisposable().add(
                    AppApiHelper.assignPack(orderId, getCacheStore().getSession().getAccessToken())
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(new Consumer<Message>() {
                                @Override
                                public void accept(Message message) throws Exception {
                                    getView().hideLoading();

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
    }

    @Override
    public void report(Report report) {
        report.setUserId(getCacheStore().getSession().getUserId());
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.report(report, getCacheStore().getSession().getAccessToken())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(new Consumer<JsonObject>() {
                            @Override
                            public void accept(JsonObject jsonObject) throws Exception {
                                getView().hideLoading();

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
}
