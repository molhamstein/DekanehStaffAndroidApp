package com.brain_socket.dekanehstaff.activity.login;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.base.BasePresenterImpl;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;
import com.brain_socket.dekanehstaff.network.model.LoginRequest;
import com.brain_socket.dekanehstaff.network.model.LoginResponse;
import com.brain_socket.dekanehstaff.utils.NetworkUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

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
                        handleApiError((ANError) throwable);
                        getView().showMessage(NetworkUtils.getError(throwable));
                    }
                })

        );
    }
}
