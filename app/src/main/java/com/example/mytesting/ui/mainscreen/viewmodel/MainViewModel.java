package com.example.mytesting.ui.mainscreen.viewmodel;

import android.annotation.SuppressLint;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.mytesting.base.BaseViewModel;
import com.example.mytesting.data.model.User;
import com.example.mytesting.data.remote.repository.UserRepository;
import com.example.mytesting.utils.rxscheduler.SchedulerListener;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {
    private UserRepository mUserRepository;
    private SchedulerListener mSchedulerListener;
    private MutableLiveData<List<User>> mUserList = new MutableLiveData<>();

    @Inject
    public MainViewModel(UserRepository userRepository, SchedulerListener schedulerListener) {
        this.mUserRepository = userRepository;
        this.mSchedulerListener = schedulerListener;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void onFetchUsers() {
        showLoading();
        mUserRepository.getUsers()
        .subscribeOn(mSchedulerListener.io())
        .observeOn(mSchedulerListener.ui())
        .doOnSubscribe(this::addToDispose)
        .subscribe(response -> {
            if (response != null) {
                mUserList.setValue(response);
            }
        }, throwable -> {
            hideLoading();
            handleNetworkException(throwable, false);
        });
    }

    public MutableLiveData<List<User>> getUserList() {
        return mUserList;
    }
}
