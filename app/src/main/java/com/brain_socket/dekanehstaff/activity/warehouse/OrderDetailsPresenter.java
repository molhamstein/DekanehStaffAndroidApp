package com.brain_socket.dekanehstaff.activity.warehouse;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BaseActivity;
import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.base.BaseView;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.Barcode;
import com.brain_socket.dekanehstaff.network.model.Order;
import com.brain_socket.dekanehstaff.network.model.Product;

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
