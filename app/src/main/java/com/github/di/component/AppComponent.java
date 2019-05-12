package com.github.di.component;

import android.app.Application;

import com.github.App;

import com.github.di.builder.ActivityBuilder;
import com.github.di.builder.FragmentBuilder;
import com.github.di.builder.ViewModelBuilder;
import com.github.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilder.class,
        FragmentBuilder.class,
        ViewModelBuilder.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent {
    void inject(App application);

    @Component.Builder
    interface Builder {
        Builder appModule(AppModule appModule);

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
