package com.example.mytesting.ui.registerscreen.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.data.model.RegisterModel;
import com.example.mytesting.databinding.ActivityRegisterBinding;
import com.example.mytesting.ui.mainscreen.activity.MainActivity;
import com.example.mytesting.ui.registerscreen.viewmodel.RegisterViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel>
    implements TextWatcher {

    @Inject
    RegisterViewModel viewModel;

    private ActivityRegisterBinding mViewBinding;

    private RegisterModel mRegisterModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        mViewBinding = getViewDataBinding();
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
        mViewBinding.edtName.addTextChangedListener(this);
        mViewBinding.edtEmail.addTextChangedListener(this);
        mViewBinding.edtRetypeEmail.addTextChangedListener(this);
        mViewBinding.edtPassword.addTextChangedListener(this);
        mViewBinding.edtRePassword.addTextChangedListener(this);

        viewModel.getRegisterStatus().observe(this, registerStatus -> {
            switch (registerStatus) {
                case NAME_ERROR:
                case EMAIL_ERROR:
                case CONFIRM_EMAIL_ERROR:
                case PASSWORD_ERROR:
                case CONFIRM_PASSWORD_ERROR:
                    viewModel.setEnableRegisterBtn(false);
                    break;
                case SUCCESS:
                    viewModel.setEnableRegisterBtn(true);
                    break;
            }
        });

        viewModel.getAction().observe(this, action -> {
            viewModel.hideLoading();
            switch (action) {
                case SUCCESS_ACTION:
                    Snackbar.make(mViewBinding.getRoot(), "Register success", BaseTransientBottomBar.LENGTH_LONG).show();
//                    viewModel.getRegisterUsers();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case REGISTER_ACTION:
                    if (viewModel.isValid()) {
                        viewModel.register(mRegisterModel);
                    }
                    break;
            }
        });
    }

    private void dataValidation() {
        String name = String.valueOf(mViewBinding.edtName.getText());
        String email = String.valueOf(mViewBinding.edtEmail.getText());
        String confirmEmail = String.valueOf(mViewBinding.edtRetypeEmail.getText());
        String password = String.valueOf(mViewBinding.edtPassword.getText());
        String confirmPassword = String.valueOf(mViewBinding.edtRePassword.getText());
        mRegisterModel = new RegisterModel(name, email, confirmEmail, password, confirmPassword);
        viewModel.getRegisterStatus().setValue(mRegisterModel.registerStatus());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        dataValidation();
    }
}
