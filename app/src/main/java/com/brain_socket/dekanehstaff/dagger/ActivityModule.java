package com.brain_socket.dekanehstaff.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.brain_socket.dekanehstaff.activity.login.LoginPresenter;
import com.brain_socket.dekanehstaff.activity.login.LoginVP;
import com.brain_socket.dekanehstaff.activity.main.MainPresenter;
import com.brain_socket.dekanehstaff.activity.main.MainVP;
import com.brain_socket.dekanehstaff.adapter.ClientsAdapter;
import com.brain_socket.dekanehstaff.adapter.ItemsAdapter;
import com.brain_socket.dekanehstaff.adapter.OrdersAdapter;
import com.brain_socket.dekanehstaff.application.AppSchedulerProvider;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.Session;
import com.brain_socket.dekanehstaff.adapter.warehouse.StockAdapter;
import com.brain_socket.dekanehstaff.adapter.warehouse.WarehouseOrdersAdapter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

    @Provides
    Session provideSession(AppCompatActivity context) {
        return new Session(context);
    }

    @Provides
    CacheStore provideCacheStore(AppCompatActivity context, Session session) {
        return new CacheStore(context, session);
    }

    @Provides
    AppCompatActivity providesActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    MainVP.Presenter<MainVP.View> provideMainPresenter(MainPresenter<MainVP.View> presenter) {
        return presenter;
    }

    @Provides
    LoginVP.Presenter<LoginVP.View> provideLoginPresenter(LoginPresenter<LoginVP.View> presenter) {
        return presenter;
    }

    @Provides
    OrdersAdapter provideOrdersAdapter() {
        return new OrdersAdapter();
    }

    @Provides
    ItemsAdapter provideItemsAdapter() {
        return new ItemsAdapter();
    }

    @Provides
    ClientsAdapter providesClientsAdapter() {
        return new ClientsAdapter();
    }


    @Provides
    WarehouseOrdersAdapter.OrderClickListener providesClickListener(AppCompatActivity activity) {
        return (WarehouseOrdersAdapter.OrderClickListener)activity;
    }

    @Provides
    WarehouseOrdersAdapter providesWarehouseOrdersAdapter() {
        return new WarehouseOrdersAdapter(providesClickListener(activity));
    }

    @Provides
    StockAdapter providesStockAdapter() {
        return new StockAdapter();
    }








}
