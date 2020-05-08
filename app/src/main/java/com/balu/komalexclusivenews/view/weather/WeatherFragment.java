package com.balu.komalexclusivenews.view.weather;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.application.KomalExclusiveNewsApplication;
import com.balu.komalexclusivenews.databinding.FragmentWeatherBinding;
import com.balu.komalexclusivenews.model.WeatherApiInterfa;
import com.balu.komalexclusivenews.model.weather.Weather;
import com.balu.komalexclusivenews.viewmodel.weather.WeatherViewModel;
import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {
    private WeatherViewModel weatherViewModel;
    FragmentWeatherBinding binding;

    private String resultSearchString = null;

    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        binding.setWeatherViewmodel(weatherViewModel);
        observeWeatherSuccessReport();
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = binding.etInputPlace.getText().toString();
                weatherViewModel.loadCurrentWeatherReport(searchString);
            }
        });
        return binding.getRoot();


    }

    private void observeWeatherSuccessReport(){
        binding.getWeatherViewmodel().getSuccessWeatherMutableLiveData().observe(getActivity(), new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                if (weather != null){
                    Glide.with(getActivity()).load(weather.getCurrent().getWeatherIcons().get(0)).into(binding.ivIconWeather);
                    binding.tvLocationWeather.setText(weather.getLocation().getName());
                    binding.tvRegionWeather.setText(weather.getLocation().getRegion());
                    binding.tvCountryWeather.setText(weather.getLocation().getCountry());
                    binding.tvTimeWeather.setText(weather.getCurrent().getObservationTime());
                    binding.tvTempWeather.setText(weather.getCurrent().getTemperature()+"");
                    binding.tvHumidityWeather.setText(weather.getCurrent().getHumidity()+"");
                    binding.tvDescriptionWeather.setText(weather.getCurrent().getWeatherDescriptions().get(0));
                }

            }
        });
    }
}
