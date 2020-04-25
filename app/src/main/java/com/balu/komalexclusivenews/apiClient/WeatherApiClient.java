package com.balu.komalexclusivenews.apiClient;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.WeatherApiInterfa;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {
    public static WeatherApiInterfa getWeatherApiClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        WeatherApiInterfa weatherApiInterface = new Retrofit.Builder().baseUrl(Constants.Whether.URL_PREFIX_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build().create(WeatherApiInterfa.class);

        return weatherApiInterface;
    }
}
