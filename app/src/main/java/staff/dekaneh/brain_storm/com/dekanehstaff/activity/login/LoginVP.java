package staff.dekaneh.brain_storm.com.dekanehstaff.activity.login;

import staff.dekaneh.brain_storm.com.dekanehstaff.base.BasePresenter;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BaseView;

public class LoginVP {

    public interface View extends BaseView {
        void startMainActivity();
        void finish();
    }

    public interface Presenter<T extends BaseView> extends BasePresenter<T> {

        void login(String email, String password);

    }
}
