package com.example.mytesting.ui.registerscreen.activity;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.databinding.ActivityRegisterBinding;
import com.example.mytesting.ui.registerscreen.viewmodel.RegisterViewModel;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {
    @Inject
    RegisterViewModel viewModel;
    private ActivityRegisterBinding mDataBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        mDataBinding = getViewDataBinding();
        initListener();
    }

    @Override
    public RegisterViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    private void initListener() {
        mDataBinding.btnRegister.setOnClickListener(v -> {
        });
    }
}
