package com.balu.komalexclusivenews.model;

import com.balu.komalexclusivenews.model.covid.CovidIndiaSummary;
import com.balu.komalexclusivenews.model.covid.CovidWorldSummary;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApiInterface {

    @GET("summary")
    Call<CovidWorldSummary> getCovidSummary();

    @GET("data.json")
    Call<CovidIndiaSummary> getCovidSummaryForINIDA();


    //https://www.mohfw.gov.in/dashboard/data/data.json
    //https://api.covid19api.com/summary

}
