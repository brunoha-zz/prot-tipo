package com.github.di.module;

import android.app.Application;
import android.content.Context;

import com.github.service.APIClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    APIClient provideApiClient() {
        return new APIClient();
    }

}
