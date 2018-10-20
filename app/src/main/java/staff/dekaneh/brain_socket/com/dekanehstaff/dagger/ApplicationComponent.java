package staff.dekaneh.brain_socket.com.dekanehstaff.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import staff.dekaneh.brain_socket.com.dekanehstaff.application.App;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();



}
