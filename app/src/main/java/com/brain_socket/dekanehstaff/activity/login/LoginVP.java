package com.brain_socket.dekanehstaff.activity.login;


import com.brain_socket.dekanehstaff.base.BasePresenter;
import com.brain_socket.dekanehstaff.base.BaseView;

public class LoginVP {

    public interface View extends BaseView {
        void startMainActivity();
        void finish();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void login(String email, String password);

    }
}
