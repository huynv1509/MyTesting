package com.example.mytesting.ui.mainscreen.activity;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytesting.BR;
import com.example.mytesting.R;
import com.example.mytesting.base.BaseActivity;
import com.example.mytesting.constants.Constants;
import com.example.mytesting.databinding.ActivityMainBinding;
import com.example.mytesting.ui.detailscreen.activity.DetailActivity;
import com.example.mytesting.ui.mainscreen.adapter.UserAdapter;
import com.example.mytesting.ui.mainscreen.viewmodel.MainViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Inject
    MainViewModel viewModel;

    private ActivityMainBinding mViewBinding;

    private UserAdapter mUserAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mViewBinding = getViewDataBinding();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mUserAdapter = new UserAdapter((position, username) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(Constants.INTENT_USERNAME_EXTRA, username);
            startActivity(intent);
        });
        mViewBinding.userList.setLayoutManager(layoutManager);
        mViewBinding.userList.setAdapter(mUserAdapter);
        initListener();

        viewModel.onFetchUsers();
    }

    @Override
    public MainViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected int getBindingVariable() {
        return BR.viewModel;
    }

    private void initListener() {
        mViewBinding.btnReload.setOnClickListener(v -> {
            mViewBinding.btnReload.setVisibility(View.GONE);
            viewModel.onFetchUsers();
        });

        viewModel.getUserList().observe(this, userList -> {
            viewModel.hideLoading();
            if (userList != null && !userList.isEmpty()) {
                mViewBinding.userList.setVisibility(View.VISIBLE);
                mUserAdapter.addItems(userList);
            }
        });

        viewModel.getNetworkViewAction().observe(this, viewAction -> {
            switch (viewAction) {
                case SHOW_ERROR_BAD_REQUEST:
                case SHOW_ERROR_SERVER_ERROR:
                    mViewBinding.userList.setVisibility(View.GONE);
                    mViewBinding.btnReload.setVisibility(View.VISIBLE);
                    Snackbar.make(mViewBinding.getRoot(), getString(R.string.server_error), BaseTransientBottomBar.LENGTH_LONG).show();
                    break;
                case SHOW_ERROR_NETWORK_TIMEOUT:
                case SHOW_ERROR_CONNECT_EXCEPTION:
                    mViewBinding.userList.setVisibility(View.GONE);
                    mViewBinding.btnReload.setVisibility(View.VISIBLE);
                    Snackbar.make(mViewBinding.getRoot(), getString(R.string.error_internet_connection_warning), BaseTransientBottomBar.LENGTH_LONG).show();
                    break;
            }
        });
    }
}
