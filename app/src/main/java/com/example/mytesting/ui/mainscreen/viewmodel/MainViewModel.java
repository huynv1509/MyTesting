package com.example.mytesting.ui.mainscreen.viewmodel;

import com.example.mytesting.base.BaseViewModel;
import com.example.mytesting.data.remote.repository.UserRepository;
import com.example.mytesting.utils.rxscheduler.SchedulerListener;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {
    private UserRepository mUserRepository;
    private SchedulerListener mSchedulerListener;

    @Inject
    public MainViewModel(UserRepository userRepository, SchedulerListener schedulerListener) {
        this.mUserRepository = userRepository;
        this.mSchedulerListener = schedulerListener;
    }

    public void onFetchUsers() {
        showLoading();
//        mUserRepository.getUsers()
        mUserRepository.getFollowers("mojombo")
        .subscribeOn(mSchedulerListener.io())
        .observeOn(mSchedulerListener.ui())
        .doOnSubscribe(this::addToDispose)
        .subscribe(response -> {
            if (response != null) {

            }
        }, throwable -> {
            hideLoading();
            handleNetworkException(throwable, false);
        });
    }
}
