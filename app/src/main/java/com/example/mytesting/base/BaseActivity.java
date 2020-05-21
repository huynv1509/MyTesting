package com.example.mytesting.base;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.mytesting.services.NetworkStatusReceiver;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback, NetworkStatusReceiver.ConnectivityReceiverListener {
    private T mViewDataBinding;
    private V mViewModel;
    private NetworkStatusReceiver mNetworkReceiver = new NetworkStatusReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        registerNetworkCheckReceiver();
        performDataBinding();
        initViews();
        if (mViewModel != null) {
            mViewModel.onViewCreated();
        }
        initObservation();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        if (mViewModel != null) {
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        }
        mViewDataBinding.executePendingBindings();
    }

    private void registerNetworkCheckReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(mNetworkReceiver, intentFilter);
    }

    /**
     * @return layout resource id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews();

    public abstract V getViewModel();

    protected abstract int getBindingVariable();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    private void initObservation() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.clearDispose();
        unregisterReceiver(mNetworkReceiver);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isDataNetworkAvailable) {
        if (!isDataNetworkAvailable) {
            //Todo show inform network lost
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
