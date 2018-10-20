package staff.dekaneh.brain_socket.com.dekanehstaff.base;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import java.util.Arrays;

import staff.dekaneh.brain_socket.com.dekanehstaff.R;
import staff.dekaneh.brain_socket.com.dekanehstaff.application.App;
import staff.dekaneh.brain_socket.com.dekanehstaff.dagger.ActivityComponent;
import staff.dekaneh.brain_socket.com.dekanehstaff.dagger.ActivityModule;
import staff.dekaneh.brain_socket.com.dekanehstaff.dagger.DaggerActivityComponent;
import staff.dekaneh.brain_socket.com.dekanehstaff.utils.LocaleUtils;
import staff.dekaneh.brain_socket.com.dekanehstaff.utils.NetworkUtils;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements LocaleUtils.LanguageListener, BaseView {

    private View vLoading;
    ProgressDialog mProgressDialog;
    private PowerMenu powerMenu;
    private ActivityComponent activityComponent;

    public BaseActivity() {
        LocaleUtils.updateConfig(this);
    }

    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        hideProgressDialog();
    }

    @Override
    public void onError(int resId) {
        displayCustomToast(resId);
    }

    @Override
    public void onError(String message) {
        displayCustomToast(message);
    }

    @Override
    public void showMessage(String message) {
        displayCustomToast(message);
    }

    @Override
    public void showMessage(int resId) {
        displayCustomToast(resId);
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(this);
    }

    @Override
    public void hideKeyboard() {
        closeKeyBoard();
    }

    @Override
    public void showPopupMenu(OnMenuItemClickListener onMenuItemClickListener, View view, PowerMenuItem... s) {

        powerMenu.clearItems();
        powerMenu.addItemList(Arrays.asList(s));
        powerMenu.setAnimation(MenuAnimation.SHOWUP_TOP_LEFT);
        powerMenu.setMenuRadius(10f);
        powerMenu.setMenuShadow(10f);
        powerMenu.setOnMenuItemClickListener(onMenuItemClickListener);
        powerMenu.showAsAnchorCenter(view);
    }

    @Override
    public void dismissPopupMenu() {
        if (powerMenu.isShowing()) {
            powerMenu.dismiss();
        }
    }

    @Override
    public void onLanguageChange() {
        this.recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clearNotifications();
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .build();
        mProgressDialog = new ProgressDialog(this);
        powerMenu = new PowerMenu.Builder(this).build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.anim_nothing, R.anim.anim_slide_out_to_right);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearNotifications();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    void clearNotifications() {
        try {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getSystemService(ns);
            nMgr.cancelAll();

        } catch (Exception ignored) {
        }
    }

    /////////////////////////
    //--- Loading
    /////////////////////////

    protected void showLoading(boolean show) {
        try {
            if (vLoading == null)
                ///vLoading = findViewById(R.id.vLoading);
                if (show) {
                    vLoading.setVisibility(View.VISIBLE);
                } else {
                    vLoading.setVisibility(View.GONE);
                }
        } catch (Exception ignored) {

        }
    }

    /////////////////////////
    //--- Alerts & toasts
    /////////////////////////

    private void displayCustomToast(String txt) {
        try {
            Toast toast = Toast.makeText(this, txt, Toast.LENGTH_LONG);
            toast.show();

        } catch (Exception ignored) {
        }
    }

    private void displayCustomToast(@StringRes int strRes) {
        if (strRes != 0) {
            displayCustomToast(getString(strRes));
        }
    }

    private static void displaySnackBar(@StringRes int txtRes) {
        final Snackbar bar = Snackbar.make(null, txtRes, Snackbar.LENGTH_SHORT);
        bar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.dismiss();
            }
        });
        bar.show();
    }


    private static void displaySnackBar(String txt) {
        final Snackbar bar = Snackbar.make(null, txt, Snackbar.LENGTH_SHORT);
        bar.setAction("DISMISS", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bar.dismiss();
            }
        });
        bar.show();
    }

    /////////////////////////
    //--- Keyboard
    /////////////////////////

    private void closeKeyBoard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void hideKeyboardWhenTouchOut(View parentView) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(parentView instanceof EditText)) {
            parentView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    closeKeyBoard();
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (parentView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) parentView).getChildCount(); i++) {
                View innerView = ((ViewGroup) parentView).getChildAt(i);
                hideKeyboardWhenTouchOut(innerView);
            }
        }
    }

    private void showProgressDialog() {
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

    }

    private void hideProgressDialog() {
        mProgressDialog.dismiss();
    }

}

