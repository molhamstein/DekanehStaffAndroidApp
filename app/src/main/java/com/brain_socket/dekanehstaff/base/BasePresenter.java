package com.brain_socket.dekanehstaff.base;

import com.androidnetworking.error.ANError;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T mvpView);

    void onDetach();

    void handleApiError(ANError error);


}
