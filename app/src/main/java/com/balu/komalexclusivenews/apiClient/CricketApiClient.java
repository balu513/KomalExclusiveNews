package com.balu.komalexclusivenews.apiClient;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.CricketApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CricketApiClient {

    public static CricketApiInterface getCricketApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        CricketApiInterface newsApiClient = new Retrofit.Builder().baseUrl(Constants.Cricket.URL_PREFIX_CRICKET)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(CricketApiInterface.class);
        return  newsApiClient;

    }
}
