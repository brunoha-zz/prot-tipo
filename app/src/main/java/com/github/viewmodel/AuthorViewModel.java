package com.github.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;

import com.github.model.AuthorModel;
import com.github.repository.DataRepository;
import com.github.utils.Either;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class AuthorViewModel extends BaseViewModel {

    private DataRepository dataRepository;

    private List<AuthorModel> allAuthors;

    public MutableLiveData<Either<List<AuthorModel>,Exception>> authors = new MutableLiveData<>();

    @Inject
    public AuthorViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @SuppressLint("CheckResult")
    public void getAuthors() {
        dataRepository.getAuthors().subscribe( result -> {
                    allAuthors = result;
                    authors.postValue(Either.left(result));
                },
                error -> authors.postValue(Either.right()));
    }

    @SuppressLint("CheckResult")
    public void searchAuthors(String name) {
        dataRepository.searchAuthors(allAuthors, name)
                .subscribe(result -> authors.postValue(Either.left(result)), error ->
                        authors.postValue(Either.right()));
    }

}
