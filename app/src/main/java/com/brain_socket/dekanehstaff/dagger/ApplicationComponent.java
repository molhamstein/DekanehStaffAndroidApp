package com.brain_socket.dekanehstaff.dagger;

import android.app.Application;
import android.content.Context;

import com.brain_socket.dekanehstaff.application.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();



}
