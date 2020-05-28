package com.example.mytesting.base;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mytesting.constants.Constants;
import com.example.mytesting.constants.Status;
import com.example.mytesting.data.model.BaseResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel<N> extends ViewModel {
    private WeakReference<N> mNavigator;
    private ObservableField<Boolean> isLoading = new ObservableField<>();
    private MutableLiveData<String> showErrorObs = new MutableLiveData<>();
    private MutableLiveData<NetworkViewAction> networkViewAction = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String mFailedMessage;
    private boolean mIsLock = false;

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public MutableLiveData<NetworkViewAction> getNetworkViewAction() {
        return networkViewAction;
    }

    void onViewCreated() {}

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    private void setIsLoading(Boolean _isLoading) {
        this.isLoading.set(_isLoading);
    }

    public void showLoading() {
        setIsLoading(true);
    }

    public void hideLoading() {
        setIsLoading(false);
    }

    public MutableLiveData<String> getShowErrorObs() {
        return showErrorObs;
    }

    public void addToDispose(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void clearDispose() {
        compositeDisposable.dispose();
    }

    protected <T> boolean handleNetworkException(BaseResponse<T> Resource) {
        return handleNetworkException(Resource, false);
    }

    private <T> boolean handleNetworkException(BaseResponse<T> resource, boolean skipError) {
        if (resource.status == Status.SUCCESS || resource.status == Status.ERROR) {
            // Request successfully, no errors to handle, will allow API business code to be executed
            return false;
        }
        if (skipError) {
            // Error happens, but skip them, not show any warning signs, won't allow API business code to be executed
            return true;
        }
        switch (resource.status) {
            case ERROR_CONNECT_EXCEPTION:
                networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_CONNECT_EXCEPTION);
                break;
            case ERROR_SERVER_ERROR:
                networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_SERVER_ERROR);
                break;
            case ERROR_HTTP_UNAUTHORIZED:
                // clear token, logout user
                networkViewAction.setValue(NetworkViewAction.LOGOUT);
                break;
        }
        // Error happens, already forwarded them to UI by NetworkViewAction, won't allow API business code to be executed
        return true;
    }

    protected void handleNetworkException(Throwable e, boolean skipError) {
        if (skipError)
            return;
        if (e instanceof HttpException) {
            mFailedMessage = "";
            try {
                JSONObject json = new JSONObject(String.valueOf(((HttpException) e).response().errorBody()));
                mFailedMessage = json.optString("error_message");
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            switch (((HttpException) e).code()) {
                case Constants.HTTP_STATUS_NOT_FOUND:
                    networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_BAD_REQUEST);
                    break;
                case Constants.HTTP_STATUS_UN_AUTHORIZE:
                    networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_UN_AUTHORIZE);
                    break;
                case Constants.HTTP_SERVICE_UNAVAILABLE:
                case Constants.HTTP_SERVER_ERROR:
                    networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_SERVER_ERROR);
                    break;
                case Constants.HTTP_STATUS_FORBIDDEN_ACCESS:
                    networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_FORBIDDEN_ACCESS);
                    break;
                default:
                    break;
            }
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_CONNECT_EXCEPTION);
        } else if (e instanceof SocketTimeoutException) {
            networkViewAction.setValue(NetworkViewAction.SHOW_ERROR_NETWORK_TIMEOUT);
        }
        mIsLock = false;
    }

    public String getFailedMessage() {
        return mFailedMessage;
    }

    public void setFailedMessage(String message) {
        mFailedMessage = message;
    }

    public boolean isLock() {
        return mIsLock;
    }

    public void setIsLock(boolean mIsLock) {
        this.mIsLock = mIsLock;
    }
}
