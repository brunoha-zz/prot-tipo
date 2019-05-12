package com.github;

import android.app.Activity;
import android.app.Application;

import com.github.di.component.DaggerAppComponent;
import com.github.di.module.AppModule;
import com.github.service.APIClient;
import com.github.utils.WebConstants;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Inject
    APIClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .appModule(new AppModule())
                .build().inject(this);

        apiClient.configure(WebConstants.BASE_URL);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
