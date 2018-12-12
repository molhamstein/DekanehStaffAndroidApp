package com.brain_socket.dekanehstaff.application;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;
import com.brain_socket.dekanehstaff.dagger.ApplicationComponent;
import com.brain_socket.dekanehstaff.dagger.ApplicationModule;
import com.brain_socket.dekanehstaff.dagger.DaggerApplicationComponent;

import java.util.Locale;



public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        applicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());

        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, null);


    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
