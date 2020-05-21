package com.example.mytesting.ui.registerscreen.viewmodel;

import android.content.Context;

import androidx.databinding.ObservableField;

import com.example.mytesting.base.BaseViewModel;
import com.example.mytesting.data.local.repository.UserDaoRepository;

import javax.inject.Inject;

public class RegisterViewModel extends BaseViewModel {
    private UserDaoRepository mUserDaoRepository;
    private Context mContext;

    private ObservableField<Boolean> mEnableRegisterBtn = new ObservableField<>(false);

    @Inject
    public RegisterViewModel(Context context, UserDaoRepository userDaoRepository) {
        this.mContext = context;
        this.mUserDaoRepository = userDaoRepository;
    }

    public ObservableField<Boolean> getEnableRegisterBtn() {
        return mEnableRegisterBtn;
    }

    public void setEnableRegisterBtn(Boolean isEnabled) {
        this.mEnableRegisterBtn.set(isEnabled);
    }
}
