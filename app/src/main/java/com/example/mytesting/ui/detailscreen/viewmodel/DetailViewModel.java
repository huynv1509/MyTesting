package com.example.mytesting.ui.detailscreen.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.mytesting.base.BaseViewModel;
import com.example.mytesting.data.model.User;
import com.example.mytesting.data.remote.repository.UserRepository;
import com.example.mytesting.utils.rxscheduler.SchedulerListener;

import java.util.List;

import javax.inject.Inject;

public class DetailViewModel extends BaseViewModel {
    private UserRepository mUserRepository;
    private SchedulerListener mSchedulerListener;
    private MutableLiveData<User> mUser = new MutableLiveData<>();
    private MutableLiveData<List<User>> mFollowers = new MutableLiveData<>();

    @Inject
    public DetailViewModel(UserRepository userRepository, SchedulerListener schedulerListener) {
        this.mUserRepository = userRepository;
        this.mSchedulerListener = schedulerListener;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void fetchUserDetail(String username) {
        showLoading();
        mUserRepository.getUserDetail(username)
        .subscribeOn(mSchedulerListener.io())
        .observeOn(mSchedulerListener.ui())
        .doOnSubscribe(this::addToDispose)
        .subscribe(response -> {
            if (response != null) {
                mUser.setValue(response);
            }
        }, throwable -> {
            hideLoading();
            handleNetworkException(throwable, false);
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    public void fetchFollower(String username) {
        showLoading();
        mUserRepository.getFollowers(username)
        .subscribeOn(mSchedulerListener.io())
        .observeOn(mSchedulerListener.ui())
        .doOnSubscribe(this::addToDispose)
        .subscribe(response -> {
            if (response != null) {
                mFollowers.setValue(response);
            }
        }, throwable -> {
            hideLoading();
            handleNetworkException(throwable, false);
        });
    }

    public MutableLiveData<User> getUser() {
        return mUser;
    }

    public MutableLiveData<List<User>> getFollowers() {
        return mFollowers;
    }
}
