package com.balu.komalexclusivenews.view.weather;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.databinding.FragmentWeatherBinding;
import com.balu.komalexclusivenews.model.weather.Weather;
import com.balu.komalexclusivenews.viewmodel.weather.WeatherViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherFragment} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {
    private WeatherViewModel weatherViewModel;
    FragmentWeatherBinding binding;



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
        // Inflate the layout for this fragment
       // View view = inflater.inflate(R.layout.fragment_weather, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        binding.setWeatherViewmodel(weatherViewModel);


        return binding.getRoot();


    }

    private void observeWeatherSuccessReport(){
        weatherViewModel.getSuccessWeatherMutableLiveData().observe(getActivity(), new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {

            }
        });
    }
}
