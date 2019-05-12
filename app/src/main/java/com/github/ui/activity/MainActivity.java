package com.github.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;

import com.github.R;
import com.github.databinding.ActivityMainBinding;
import com.github.model.AuthorModel;
import com.github.ui.BaseActivity;
import com.github.ui.adapter.AuthorAdapter;
import com.github.viewmodel.AuthorViewModel;

import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, AuthorViewModel> {

    AuthorAdapter authorAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<AuthorViewModel> getViewModelClass() {
        return AuthorViewModel.class;
    }

    @Override
    public void init() {
        getViewBinding().authorRecycler.setLayoutManager(new LinearLayoutManager(this));
        getViewModel().authors.observe(this, result ->
                result.apply(this::setupAdapter, error -> {

                }));

        getViewModel().getAuthors();

        setupSearch();
    }

    private void setupSearch() {
        getViewBinding().mainSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                getViewModel().searchAuthors(s.toString());
            }
        });
    }

    private void setupAdapter(List<AuthorModel> result) {
        authorAdapter = new AuthorAdapter(result);
        getViewBinding().authorRecycler.setAdapter(authorAdapter);
    }

}
