package com.brain_socket.dekanehstaff.base;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.brain_socket.dekanehstaff.R;
import com.brain_socket.dekanehstaff.application.SchedulerProvider;
import com.brain_socket.dekanehstaff.network.AppApiHelper;
import com.brain_socket.dekanehstaff.network.CacheStore;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private final SchedulerProvider schedulerProvider;
    private final CompositeDisposable compositeDisposable;
    private final CacheStore cacheStore;
    private T view;


    @Inject
    public BasePresenterImpl(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, CacheStore cacheStore) {

        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.cacheStore = cacheStore;
    }

    @Override
    public void onAttach(T mvpView) {
        this.view = mvpView;
    }

    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        this.view = null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public CacheStore getCacheStore() {
        return cacheStore;
    }

    public T getView() {
        return view;
    }

    @Override
    public void handleApiError(ANError error) {

        if (error == null || error.getErrorBody() == null) {
            getView().onError(R.string.api_default_error);
        } else {
            getView().onError(R.string.user_not_activated_error);
        }
//
//        else if (error.getErrorCode() == AppApiHelper.API_STATUS_CODE_LOCAL_ERROR
//                && error.getErrorDetail().equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
//            getView().onError(R.string.api_retry_error);
//        }

        //TODO add custom error handling from server
//        final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
//        final Gson gson = builder.create();
//
//            ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);

    }


    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
