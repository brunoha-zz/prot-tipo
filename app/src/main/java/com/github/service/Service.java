package com.github.service;

import com.github.model.BaseModel;
import com.github.utils.WebConstants;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Service {

    @GET(WebConstants.INIT_DATA)
    Observable<BaseModel> getInitData();


}
