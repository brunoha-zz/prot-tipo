package com.github.di.builder;

import com.github.di.Activity;
import com.github.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @Activity
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
