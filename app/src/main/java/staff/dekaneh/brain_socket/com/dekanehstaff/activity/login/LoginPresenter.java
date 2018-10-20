package staff.dekaneh.brain_socket.com.dekanehstaff.activity.login;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import staff.dekaneh.brain_socket.com.dekanehstaff.application.SchedulerProvider;
import staff.dekaneh.brain_socket.com.dekanehstaff.base.BasePresenterImpl;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.AppApiHelper;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.CacheStore;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.LoginRequest;
import staff.dekaneh.brain_socket.com.dekanehstaff.network.model.LoginResponse;
import staff.dekaneh.brain_socket.com.dekanehstaff.utils.NetworkUtils;

public class LoginPresenter<T extends LoginVP.View> extends BasePresenterImpl<T> implements LoginVP.Presenter<T> {


    @Inject
    public LoginPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {
        super(schedulerProvider, compositeDisposable, cacheStore);
    }

    @Override
    public void onAttach(T mvpView) {
        super.onAttach(mvpView);
        if (getCacheStore().getSession().isLoggedOn()){
            getView().startMainActivity();
            getView().finish();
        }
    }

    @Override
    public void login(String email, String password) {
        getView().showLoading();
        getCompositeDisposable().add(
                AppApiHelper.login(new LoginRequest(email, password))
                        .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        getCacheStore().getSession().setUser(loginResponse.getUser(), loginResponse.getId());
                        getView().startMainActivity();
                        getView().hideLoading();
                        getView().finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().hideLoading();
                        getView().showMessage(NetworkUtils.getError(throwable));
                        Log.e("ERRRR", "accept: " + NetworkUtils.getError(throwable), throwable);
                    }
                })

        );
    }
}
