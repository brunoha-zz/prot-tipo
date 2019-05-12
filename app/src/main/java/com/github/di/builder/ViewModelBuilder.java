package com.github.di.builder;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.github.di.ViewModelKey;
import com.github.di.ViewModelProviderFactory;
import com.github.viewmodel.AuthorViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by rafaelneiva on 08/02/18.
 */

@Module
public abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(AuthorViewModel.class)
    abstract ViewModel bindAuthorViewModel(AuthorViewModel authorViewModel);

    // ViewModel Factory
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);
}
