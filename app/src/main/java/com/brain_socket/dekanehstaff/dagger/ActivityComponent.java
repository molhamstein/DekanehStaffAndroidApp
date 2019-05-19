package com.brain_socket.dekanehstaff.dagger;


import com.brain_socket.dekanehstaff.activity.login.LoginActivity;
import com.brain_socket.dekanehstaff.activity.main.MainActivity;
import com.brain_socket.dekanehstaff.activity.warehouse.OrderDetailsActivity;
import com.brain_socket.dekanehstaff.activity.warehouse.WarehouseMainActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(LoginActivity activity);
    void inject(WarehouseMainActivity activity) ;
    void inject(OrderDetailsActivity activity);

}
