package com.example.mytesting.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mytesting.data.model.BaseResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;

public class BaseRepository {
    public <T> LiveData<BaseResponse<T>> enqueueRequestType(@NotNull Call<T> call) {
        final MutableLiveData<BaseResponse<T>> data = new MutableLiveData<>();
        call.enqueue(new CallbackImpl<>(data));
        return data;
    }
}
