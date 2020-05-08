package com.balu.komalexclusivenews.component;


import com.balu.komalexclusivenews.apiClient.CovidApiClient;
import com.balu.komalexclusivenews.apiClient.CricketApiClient;
import com.balu.komalexclusivenews.apiClient.NewsApiClient;
import com.balu.komalexclusivenews.apiClient.WeatherApiClient;
import com.balu.komalexclusivenews.module.AppModule;
import com.balu.komalexclusivenews.module.DbModule;
import com.balu.komalexclusivenews.mvp.view.LoginFragment;
import com.balu.komalexclusivenews.mvp.view.TopHeadLinesFragment;
import com.balu.komalexclusivenews.viewmodel.covid.CovidWorldSummaryViewModel;
import com.balu.komalexclusivenews.viewmodel.cricket.MatchCalendarViewModel;
import com.balu.komalexclusivenews.viewmodel.cricket.NewMatchesViewModel;
import com.balu.komalexclusivenews.viewmodel.weather.WeatherViewModel;

import dagger.Component;

@Component(modules = {AppModule.class, NewsApiClient.class, CovidApiClient.class, WeatherApiClient.class, CricketApiClient.class, DbModule.class})
public interface KomalNewsComponent {
   void inject(TopHeadLinesFragment topHeadLinesFragment);

   void inject(CovidWorldSummaryViewModel covidWorldSummaryViewModel);

   void inject(WeatherViewModel weatherViewModel);

   void inject(MatchCalendarViewModel matchCalendarViewModel);

   void inject(NewMatchesViewModel newMatchesViewModel);

   void inject(LoginFragment loginFragment);
}
