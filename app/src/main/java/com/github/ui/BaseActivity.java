package com.github.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.github.viewmodel.BaseViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<DB extends ViewDataBinding,V extends BaseViewModel> extends AppCompatActivity
        implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    private DB mViewDataBinding;

    private V mViewModel;

    protected abstract int getContentLayoutId();

    protected abstract Class<V> getViewModelClass();

    public abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
        if (getViewModelClass() != null)
            mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModelClass());

        init();

    }

    public DB getViewBinding() {
        return mViewDataBinding;
    }

    public V getViewModel() {
        return mViewModel;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
