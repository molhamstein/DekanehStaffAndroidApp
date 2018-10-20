package staff.dekaneh.brain_socket.com.dekanehstaff.dagger;


import dagger.Component;
import staff.dekaneh.brain_socket.com.dekanehstaff.activity.login.LoginActivity;
import staff.dekaneh.brain_socket.com.dekanehstaff.activity.main.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(LoginActivity activity);

}
