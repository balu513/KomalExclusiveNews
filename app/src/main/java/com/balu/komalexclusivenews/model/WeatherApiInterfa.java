package com.balu.komalexclusivenews.model;

import com.balu.komalexclusivenews.model.covid.Country;
import com.balu.komalexclusivenews.model.weather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiInterfa {
    @GET("current")
    Call<Weather> getWeatherByLocation(@Query("access_key") String access_key, @Query("query") String location);
}
