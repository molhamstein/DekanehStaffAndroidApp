package staff.dekaneh.brain_storm.com.dekanehstaff.dagger;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import staff.dekaneh.brain_storm.com.dekanehstaff.activity.login.LoginPresenter;
import staff.dekaneh.brain_storm.com.dekanehstaff.activity.login.LoginVP;
import staff.dekaneh.brain_storm.com.dekanehstaff.activity.main.MainPresenter;
import staff.dekaneh.brain_storm.com.dekanehstaff.activity.main.MainVP;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.ClientsAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.ItemsAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.adapter.OrdersAdapter;
import staff.dekaneh.brain_storm.com.dekanehstaff.application.AppSchedulerProvider;
import staff.dekaneh.brain_storm.com.dekanehstaff.application.SchedulerProvider;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.CacheStore;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.Session;

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
    AppCompatActivity providesActivity(){
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
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
}
