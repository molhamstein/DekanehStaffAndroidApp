package com.brain_socket.dekanehstaff.base;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenuItem;


public interface BaseView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();

    Intent getIntent();

    void showPopupMenu(OnMenuItemClickListener onMenuItemClickListener, View view, PowerMenuItem... s);

    void dismissPopupMenu();

    void requestPermission(String permission);

}
