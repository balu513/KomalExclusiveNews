package com.balu.komalexclusivenews.model;

import com.balu.komalexclusivenews.model.news.TopHeadlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {

    @GET("top-headlines")
    Call<TopHeadlines> getHeadLines(@Query("country") String countryCode, @Query("apikey") String apiKey);
}
