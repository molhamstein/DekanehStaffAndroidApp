package staff.dekaneh.brain_storm.com.dekanehstaff.activity.login;

import android.os.Bundle;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import staff.dekaneh.brain_storm.com.dekanehstaff.R;
import staff.dekaneh.brain_storm.com.dekanehstaff.base.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginVP.View {

    @Inject
    LoginVP.Presenter<LoginVP.View> presenter;
    @BindView(R.id.loginPhoneNumber)
    EditText phoneNumberEditText;
    @BindView(R.id.loginPassword)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (getActivityComponent() != null)
            getActivityComponent().inject(this);

    }

    @OnClick(R.id.loginBtn)
    public void onLoginBtnClicked() {
//        presenter.login(phoneNumberEditText.getText().toString(), passwordEditText.getText().toString());
    }
}
