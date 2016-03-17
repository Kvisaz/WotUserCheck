package ru.kvisaz.wotolenemer.rest;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RestAPI restApi;


    @NonNull
    public static RestAPI getApiService(){
        if(restApi==null){
            restApi = getRetrofit().create(RestAPI.class);
        }
        return restApi;
    }

    @NonNull
    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestAPI.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
