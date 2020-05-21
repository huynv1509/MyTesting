package com.example.mytesting.data.remote.interceptor;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    private Context mContext;

    public AuthenticationInterceptor(Context context) {
        this.mContext = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request original = chain.request();
        Request request = null;
        //TODO: add header request api: login or authentication
        if (!original.url().encodedPath().contains("/login")) {
        }
        return chain.proceed(original);
    }
}
