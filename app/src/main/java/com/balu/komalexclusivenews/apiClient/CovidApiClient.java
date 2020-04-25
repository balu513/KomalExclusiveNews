package com.balu.komalexclusivenews.apiClient;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.CovidApiInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApiClient {

    public static CovidApiInterface getCovidApiClientWorld(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        CovidApiInterface covidApiInterface = new Retrofit.Builder().baseUrl(Constants.Covid.URL_PREFIX_COVID_WORLD)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(CovidApiInterface.class);
        return covidApiInterface;

    }
    public static CovidApiInterface getCovidApiClientIndia(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        CovidApiInterface covidApiInterface = new Retrofit.Builder().baseUrl(Constants.Covid.URL_PREFIX_COVID_IND)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(CovidApiInterface.class);
        return covidApiInterface;

    }
}
