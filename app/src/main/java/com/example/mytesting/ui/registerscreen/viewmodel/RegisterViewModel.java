package com.example.mytesting.ui.registerscreen.viewmodel;

import android.annotation.SuppressLint;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.example.mytesting.base.BaseViewModel;
import com.example.mytesting.constants.RegisterStatus;
import com.example.mytesting.data.local.repository.RegisterUserRepository;
import com.example.mytesting.data.model.RegisterModel;
import com.example.mytesting.utils.rxscheduler.SchedulerListener;

import javax.inject.Inject;

public class RegisterViewModel extends BaseViewModel {
    private RegisterUserRepository mRegisterRepository;

    private SchedulerListener mSchedulerListener;

    public enum Action {
        REGISTER_ACTION,
        SUCCESS_ACTION
    }

    private ObservableField<Boolean> mEnableRegisterBtn = new ObservableField<>(false);
    private MutableLiveData<RegisterStatus> mRegisterStatus = new MutableLiveData<>();
    private MutableLiveData<Action> mAction = new MutableLiveData<>();

    @Inject
    public RegisterViewModel(RegisterUserRepository registerRepository, SchedulerListener schedulerListener) {
        this.mRegisterRepository = registerRepository;
        this.mSchedulerListener = schedulerListener;
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isValid() {
        return mEnableRegisterBtn.get();
    }

    public ObservableField<Boolean> getEnableRegisterBtn() {
        return mEnableRegisterBtn;
    }

    public void setEnableRegisterBtn(Boolean isEnabled) {
        this.mEnableRegisterBtn.set(isEnabled);
    }

    public MutableLiveData<RegisterStatus> getRegisterStatus() {
        return mRegisterStatus;
    }

    @SuppressLint("CheckResult")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void register(RegisterModel model) {
        showLoading();
        mRegisterRepository.insertRegisterUser(model)
        .subscribeOn(mSchedulerListener.io())
        .observeOn(mSchedulerListener.ui())
        .doOnSubscribe(this::addToDispose)
        .subscribe(() -> {
            mAction.setValue(Action.SUCCESS_ACTION);
        }, throwable -> {
            hideLoading();
            //Handle exception
        });
    }

    @SuppressLint("CheckResult")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void getRegisterUsers() {
        mRegisterRepository.getRegisterUsers()
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

    public MutableLiveData<Action> getAction() {
        return mAction;
    }

    public void onRegister() {
        mAction.setValue(Action.REGISTER_ACTION);
    }
}
