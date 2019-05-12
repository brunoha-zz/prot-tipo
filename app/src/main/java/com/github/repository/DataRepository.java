package com.github.repository;

import com.github.model.AuthorModel;
import com.github.model.BaseModel;
import com.github.service.APIClient;
import com.github.service.Service;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class DataRepository {

    private APIClient client;

    @Inject
    public DataRepository(APIClient client) {
        this.client = client;
    }

    public Observable<List<AuthorModel>> getAuthors() {
        return client.getRetrofit().create(Service.class)
                .getInitData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(BaseModel::getAuthors);
    }

    public Single<List<AuthorModel>> searchAuthors(List<AuthorModel> authors, String name) {
        return Observable.just(authors)
                .flatMap(Observable::fromIterable)
                .filter(result -> result.getAuthor().contains(name))
                .toList();
    }
}
