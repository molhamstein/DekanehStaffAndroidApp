package staff.dekaneh.brain_storm.com.dekanehstaff.activity.login;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import staff.dekaneh.brain_storm.com.dekanehstaff.application.SchedulerProvider;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BasePresenterImpl;
import staff.dekaneh.brain_storm.com.dekanehstaff.network.CacheStore;

public class LoginPresenter<T extends LoginVP.View> extends BasePresenterImpl<T> implements LoginVP.Presenter<T> {


    @Inject
    public LoginPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void login(String email, String password) {

    }
}
