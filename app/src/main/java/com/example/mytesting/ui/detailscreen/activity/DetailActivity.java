package com.example.mytesting.ui.detailscreen.activity;

import android.animation.LayoutTransition;
import android.view.View;

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
        initListener();
    }

    @Override
    public DetailViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    private void initListener() {
        mDataBinding.userTitleTv.setOnClickListener(v -> mDataBinding.userContentLn.setVisibility(mDataBinding.userContentLn.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
        mDataBinding.followerTitleTv.setOnClickListener(v -> mDataBinding.followerList.setVisibility(mDataBinding.followerList.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));

        mDataBinding.userInfoCard.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mDataBinding.userConstraint.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mDataBinding.userConstraint.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);

        mDataBinding.followerCard.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mDataBinding.followerConstraint.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mDataBinding.followerConstraint.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
    }
}
