package com.balu.komalexclusivenews.apiClient;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.NewsApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiClient {

    public static NewsApiInterface getNewsApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        NewsApiInterface newsApiClient = new Retrofit.Builder().baseUrl(Constants.News.URL_PREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(NewsApiInterface.class);
        return  newsApiClient;

    }
}
