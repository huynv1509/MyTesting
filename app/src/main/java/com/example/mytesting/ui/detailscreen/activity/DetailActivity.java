package com.example.mytesting.ui.detailscreen.activity;

import android.animation.LayoutTransition;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.constants.Constants;
import com.example.mytesting.databinding.ActivityDetailBinding;
import com.example.mytesting.ui.detailscreen.adapter.FollowerAdapter;
import com.example.mytesting.ui.detailscreen.viewmodel.DetailViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class DetailActivity extends BaseActivity<ActivityDetailBinding, DetailViewModel> {
    @Inject
    DetailViewModel viewModel;

    private ActivityDetailBinding mViewBinding;

    private FollowerAdapter mFollowerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initViews() {
        mViewBinding = getViewDataBinding();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mFollowerAdapter = new FollowerAdapter();
        mViewBinding.followerList.setLayoutManager(layoutManager);
        mViewBinding.followerList.setAdapter(mFollowerAdapter);
        initListener();
        initData();
    }

    @Override
    public DetailViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    private void initData() {
        String username = getIntent().getStringExtra(Constants.INTENT_USERNAME_EXTRA);
        viewModel.fetchUserDetail(username);
    }

    private void initListener() {
        mViewBinding.userTitleTv.setOnClickListener(v -> {
            mViewBinding.userContentLn.setVisibility(mViewBinding.userContentLn.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            if (mViewBinding.userContentLn.getVisibility() == View.VISIBLE) {
                mViewBinding.userTitleTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            } else {
                mViewBinding.userTitleTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
            }
        });
        mViewBinding.followerTitleTv.setOnClickListener(v -> {
            mViewBinding.followerList.setVisibility(mViewBinding.followerList.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            if (mViewBinding.followerList.getVisibility() == View.VISIBLE) {
                mViewBinding.followerTitleTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);
            } else {
                mViewBinding.followerTitleTv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
            }
        });

        mViewBinding.userInfoCard.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mViewBinding.userConstraint.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mViewBinding.userConstraint.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);

        mViewBinding.followerCard.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mViewBinding.followerConstraint.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mViewBinding.followerConstraint.getLayoutTransition().setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);

        viewModel.getUser().observe(this, user -> {
            viewModel.hideLoading();
            if (user != null) {
                viewModel.fetchFollower(user.getUsername());
                if (StringUtils.isNotEmpty(user.getName())) {
                    mViewBinding.nameTitleTv.setVisibility(View.VISIBLE);
                    mViewBinding.nameTv.setVisibility(View.VISIBLE);
                    mViewBinding.nameTv.setText(user.getName());
                } else {
                    mViewBinding.nameTitleTv.setVisibility(View.GONE);
                    mViewBinding.nameTv.setVisibility(View.GONE);
                }

                if (StringUtils.isNotEmpty(user.getCompany())) {
                    mViewBinding.companyTitleTv.setVisibility(View.VISIBLE);
                    mViewBinding.companyTv.setVisibility(View.VISIBLE);
                    mViewBinding.companyTv.setText(user.getCompany());
                } else {
                    mViewBinding.companyTitleTv.setVisibility(View.GONE);
                    mViewBinding.companyTv.setVisibility(View.GONE);
                }

                if (StringUtils.isNotEmpty(user.getLocation())) {
                    mViewBinding.locationTitleTv.setVisibility(View.VISIBLE);
                    mViewBinding.locationTv.setVisibility(View.VISIBLE);
                    mViewBinding.locationTv.setText(user.getLocation());
                } else {
                    mViewBinding.locationTitleTv.setVisibility(View.GONE);
                    mViewBinding.locationTv.setVisibility(View.GONE);
                }

                if (StringUtils.isNotEmpty(user.getBlog())) {
                    mViewBinding.blogTitleTv.setVisibility(View.VISIBLE);
                    mViewBinding.blogTv.setVisibility(View.VISIBLE);
                    mViewBinding.blogTv.setText(user.getBlog());
                } else {
                    mViewBinding.blogTitleTv.setVisibility(View.GONE);
                    mViewBinding.blogTv.setVisibility(View.GONE);
                }
            }
        });

        viewModel.getFollowers().observe(this, followers -> {
            viewModel.hideLoading();
            if (followers != null && !followers.isEmpty()) {
                mFollowerAdapter.addItems(followers);
                mViewBinding.footerTv.setText(getString(R.string.total, followers.size()));
            } else {
                mViewBinding.footerTv.setText(getString(R.string.total, 0));
            }
        });

        viewModel.getNetworkViewAction().observe(this, viewAction -> {
            switch (viewAction) {
                case SHOW_ERROR_BAD_REQUEST:
                case SHOW_ERROR_SERVER_ERROR:
                    Snackbar.make(mViewBinding.getRoot(), getString(R.string.server_error), BaseTransientBottomBar.LENGTH_LONG).show();
                    break;
                case SHOW_ERROR_NETWORK_TIMEOUT:
                case SHOW_ERROR_CONNECT_EXCEPTION:
                    Snackbar.make(mViewBinding.getRoot(), getString(R.string.error_internet_connection_warning), BaseTransientBottomBar.LENGTH_LONG).show();
                    break;
            }
        });
    }
}
