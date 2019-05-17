package com.brain_socket.dekanehstaff.activity.warehouse.mvp;

import android.support.v7.app.AlertDialog;

import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.Barcode;
import com.brain_socket.dekanehstaff.network.model.OrderProduct;

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


    @Override
    public Integer searchForProduct(String productId) {
        List<OrderProduct> products = getView().getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductId().equals(productId))
                return i ;
        }
        return -1;
    }

    @Override
    public void onAttach(OrderDetailsVP.View view) {
        super.onAttach(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void handleApiError(ANError error) {

    }
}
