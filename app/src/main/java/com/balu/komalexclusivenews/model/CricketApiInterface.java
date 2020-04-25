package com.balu.komalexclusivenews.model;

import com.balu.komalexclusivenews.model.cricket.MatchCalendar;
import com.balu.komalexclusivenews.model.cricket.NewMatches;
import com.balu.komalexclusivenews.model.cricket.PlayerFinder;
import com.balu.komalexclusivenews.model.cricket.PlayerStat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CricketApiInterface {

    @GET("matches")
    Call<NewMatches> getNewMatches(@Query("apikey") String apiKey);

    //https://cricapi.com/api/matchCalendar?apikey=xtHqJY7jB6PeJzpRycNnMmPqxfp1
    @GET("matchCalendar")
    Call<MatchCalendar> getMatchCalendar(@Query("apikey") String apiKey);

    //https://cricapi.com/api/playerStats?apikey=xtHqJY7jB6PeJzpRycNnMmPqxfp1&pid=35320
    @GET("playerStats")
    Call<PlayerStat> getPlayerStatics(@Query("apikey") String apiKey, @Query("pid") String pid);

    //https://cricapi.com/api/playerFinder?apikey=xtHqJY7jB6PeJzpRycNnMmPqxfp1&name=Tendulkar
    @GET("playerFinder")
    Call<PlayerFinder> getPlayerByName(@Query("apikey") String apiKey, @Query("name") String name);


    @GET("cricket")
    Call<Object> getOldMatches(@Query("apikey") String apiKey);

    @GET("cricketScore")
    Call<Object> getCricketScore(@Query("apikey") String apiKey, @Query("unique_id") String uniqueId);



}
