package com.balu.komalexclusivenews.viewmodel.weather;

import android.text.Editable;
import android.text.TextWatcher;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.apiClient.WeatherApiClient;
import com.balu.komalexclusivenews.model.weather.Weather;

import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    MutableLiveData<Weather>  successWeatherMutableLiveData = new MutableLiveData<Weather>();
    MutableLiveData<String> failureWeatherMutableLiveData = new MutableLiveData<String>();

    public ObservableField<Weather> getWeather() {
        return weather;
    }

    public ObservableField<Weather> weather = new ObservableField<Weather>();

    public MutableLiveData<Weather> getSuccessWeatherMutableLiveData() {
        return successWeatherMutableLiveData;
    }

    public MutableLiveData<String> getFailureWeatherMutableLiveData() {
        return failureWeatherMutableLiveData;
    }


    public TextWatcher getLocationTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //
                if(s != null) {
                    loadCurrentWeatherReport(s.toString());
                }
            }
        };
    }



    public  void loadCurrentWeatherReport(String location){
        WeatherApiClient.getWeatherApiClient().getWeatherByLocation(Constants.Whether.API_KEY_NEWS, location).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(response != null && response.body() != null){
                   // successWeatherMutableLiveData.postValue(response.body());
                    weather.set(response.body());
                }

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                if(t != null){
                    //failureWeatherMutableLiveData.postValue(t.getLocalizedMessage());
                }

            }
        });
    }
}
