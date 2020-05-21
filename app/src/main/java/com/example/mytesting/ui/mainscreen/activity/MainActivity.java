package com.example.mytesting.ui.mainscreen.activity;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.databinding.ActivityMainBinding;
import com.example.mytesting.ui.mainscreen.viewmodel.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Inject
    MainViewModel viewModel;
    private ActivityMainBinding mDataBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mDataBinding = getViewDataBinding();
    }

    @Override
    public MainViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }
}
