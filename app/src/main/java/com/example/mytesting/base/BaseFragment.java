package com.example.mytesting.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel, R extends BaseActivity> extends Fragment {
    private R mActivity;
    private T mViewDataBinding;
    private V mViewModel;
    private View mRootView;

    public abstract boolean isPerformDependencyInjection();

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    protected abstract void initToolbar();

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (isPerformDependencyInjection()) {
            performDependencyInjection();
        }
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getViewModel();
        if (mViewModel != null) {
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        }
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
        initToolbar();
        initView(savedInstanceState);
        initData(savedInstanceState);
        if (mViewModel != null) {
            mViewModel.onViewCreated();
        }
        initObservation();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mActivity = (R) context;
        mActivity.onFragmentAttached();
    }

    @Override
    public void onDetach() {
        mActivity = null;
        mViewModel.clearDispose();
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public R getAttachedActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewModel.clearDispose();
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    private void initObservation() {}

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
