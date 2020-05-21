package com.example.mytesting.ui.detailscreen.activity;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.databinding.ActivityDetailBinding;
import com.example.mytesting.ui.detailscreen.viewmodel.DetailViewModel;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> {
    @Inject
    DetailViewModel viewModel;
    private ActivityDetailBinding mDataBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initViews() {
        mDataBinding = getViewDataBinding();
    }

    @Override
    public DetailViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }
}
