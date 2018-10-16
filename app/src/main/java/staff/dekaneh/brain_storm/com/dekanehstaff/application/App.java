package staff.dekaneh.brain_storm.com.dekanehstaff.application;

import android.app.Application;
import android.content.res.Configuration;

import com.androidnetworking.AndroidNetworking;

import java.util.Locale;

import staff.dekaneh.brain_storm.com.dekanehstaff.dagger.ApplicationComponent;
import staff.dekaneh.brain_storm.com.dekanehstaff.dagger.ApplicationModule;
import staff.dekaneh.brain_storm.com.dekanehstaff.dagger.DaggerApplicationComponent;


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
