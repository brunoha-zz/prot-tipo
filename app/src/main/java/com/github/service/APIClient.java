package com.github.service;


import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final int TIMEOUT = 360;
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String CONTENT_TYPE = "Content-Type";
    private static APIClient mInstance;

    private Retrofit mRetrofit;

    public APIClient() {
        mInstance = this;
    }

    public static synchronized APIClient getInstance() {
        return mInstance;
    }

    private OkHttpClient getClient() {

        OkHttpClient.Builder client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(getLoggingCapableHttpClient())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS);

        return client.build();
    }

    public Retrofit getRetrofit() {

        return mRetrofit;
    }

    public void configure(String baseUrl) {
        OkHttpClient mClient = getClient();

        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(mClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private HttpLoggingInterceptor getLoggingCapableHttpClient() {
        HttpLoggingInterceptor mLogging = new HttpLoggingInterceptor();
        mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return mLogging;
    }

}

