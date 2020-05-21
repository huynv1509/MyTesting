package com.example.mytesting.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytesting.constants.Status;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class BaseResponse<T> {
    @SerializedName("status")
    public final Status status;

    @SerializedName("message")
    public final String message;

    public T data;

    public BaseResponse(Status status, T data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    @NonNull
    public static <T> BaseResponse<T> success(@NonNull T data) {
        return new BaseResponse<>(Status.SUCCESS, data, null);
    }

    @NonNull
    public static <T> BaseResponse<T> error() {
        return new BaseResponse<>(Status.ERROR, null, null);
    }

    @NonNull
    public static <T> BaseResponse<T> error(String msg, @Nullable T data) {
        return new BaseResponse<>(Status.ERROR, data, msg);
    }

    @NonNull
    public static <T> BaseResponse<T> errorConnection() {
        return new BaseResponse<>(Status.ERROR_CONNECT_EXCEPTION, null, null);
    }

    @NonNull
    public static <T> BaseResponse<T> errorServerError() {
        return new BaseResponse<>(Status.ERROR_SERVER_ERROR, null, null);
    }

    @NonNull
    public static <T> BaseResponse<T> errorHttpUnAuthorized() {
        return new BaseResponse<>(Status.ERROR_HTTP_UNAUTHORIZED, null, null);
    }

    @NotNull
    @Override
    public String toString() {
        return "Response {" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
