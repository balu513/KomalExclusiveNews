package com.balu.komalexclusivenews.model;

import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiInterface {

    @GET("top-headlines")
    Single<TopHeadlines> getHeadLines(@Query("country") String countryCode, @Query("apikey") String apiKey);
}
