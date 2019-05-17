package com.brain_socket.dekanehstaff.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.activity.main.MainActivity;
import com.brain_socket.dekanehstaff.activity.warehouse.StockOrderActivity;
import com.brain_socket.dekanehstaff.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginVP.View {

    @Inject
    LoginVP.Presenter<LoginVP.View> presenter;
    @BindView(R.id.loginEmail)
    EditText emailEditText;
    @BindView(R.id.loginPassword)
    EditText passwordEditText;

    @Override
    protected void onStart() {

        super.onStart();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (getActivityComponent() != null) {
            getActivityComponent().inject(this);
        }
        presenter.onAttach(this);

    }

    @OnClick(R.id.loginBtn)
    public void onLoginBtnClicked() {
        presenter.login(emailEditText.getText().toString(), passwordEditText.getText().toString());
//        presenter.login("ahmad.3taya@gmail.com", "123456");
    }

    @Override
    public void startMainActivity() {
        MainActivity.start(this);
    }

    @Override
    public void startWarehouseOrdersStockActivity() {
        Intent intent = new Intent(this, StockOrderActivity.class) ;
        startActivity(intent);
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}
