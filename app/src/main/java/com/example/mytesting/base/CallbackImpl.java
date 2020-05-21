package com.example.mytesting.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mytesting.data.model.BaseResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class CallbackImpl<T> implements Callback<T> {
    private MutableLiveData<BaseResponse<T>> liveData;

    public CallbackImpl(MutableLiveData<BaseResponse<T>> liveData) {
        this.liveData = liveData;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        T body = response.body();

        switch (response.code()) {
            case HTTP_OK:
                assert body != null;
                liveData.setValue(BaseResponse.success(body));
                break;
            case HTTP_UNAUTHORIZED:
                liveData.setValue(BaseResponse.errorHttpUnAuthorized());
                break;
            case HTTP_BAD_REQUEST:
                try {
                    liveData.setValue(BaseResponse.error());
                } catch (Exception e) {
                    e.printStackTrace();
                    liveData.setValue(BaseResponse.error());
                }
                break;
            default:
                liveData.setValue(BaseResponse.error());
                break;
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {
        if (throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
            // Handle mobile network not available
            liveData.setValue(BaseResponse.errorConnection());
        } else {
            // Handle server error
            liveData.setValue(BaseResponse.errorServerError());
        }
    }
}
