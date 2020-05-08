package com.balu.komalexclusivenews.viewmodel.weather;

import android.util.Log;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.apiClient.WeatherApiClient;
import com.balu.komalexclusivenews.application.KomalExclusiveNewsApplication;
import com.balu.komalexclusivenews.model.WeatherApiInterfa;
import com.balu.komalexclusivenews.model.weather.Weather;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    @Inject
    WeatherApiInterfa weatherApiInterfa;

    public WeatherViewModel() {
        KomalExclusiveNewsApplication.getKomalNewsComponent().inject(this);
    }

    MutableLiveData<Weather>  successWeatherMutableLiveData = new MutableLiveData<Weather>();
    MutableLiveData<String> failureWeatherMutableLiveData = new MutableLiveData<String>();

    public MutableLiveData<Weather> getSuccessWeatherMutableLiveData() {
        return successWeatherMutableLiveData;
    }

    public MutableLiveData<String> getFailureWeatherMutableLiveData() {
        return failureWeatherMutableLiveData;
    }

    public  void loadCurrentWeatherReport(String location){
        weatherApiInterfa.getWeatherByLocation(Constants.Whether.API_KEY_NEWS, location).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(response != null && response.body() != null){
                   successWeatherMutableLiveData.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                if(t != null){
                    Log.d("WeatherViewModel" , " Failure "+t.getLocalizedMessage());
                    failureWeatherMutableLiveData.postValue(t.getLocalizedMessage());
                }

            }
        });
    }
}
