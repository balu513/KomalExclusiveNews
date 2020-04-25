package com.balu.komalexclusivenews.viewmodel.covid;

import android.util.Log;

import com.balu.komalexclusivenews.apiClient.CovidApiClient;
import com.balu.komalexclusivenews.model.covid.CovidWorldSummary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidWorldSummaryViewModel extends ViewModel {

    MutableLiveData<CovidWorldSummary> successCovidWorldSummaryMutableLiveData = new MutableLiveData<CovidWorldSummary>();
    MutableLiveData<String> failureCovidWorldSummaryMutableLiveData = new MutableLiveData<String>();

    public  void loadCovidWorldSummary(){
        Call<CovidWorldSummary> covidSummary = CovidApiClient.getCovidApiClientWorld().getCovidSummary();
        covidSummary.enqueue(new Callback<CovidWorldSummary>() {
            @Override
            public void onResponse(Call<CovidWorldSummary> call, Response<CovidWorldSummary> response) {
                Log.d("Success",response.body()+"");
                if(response!=null && response.body()!=null) {
                    successCovidWorldSummaryMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CovidWorldSummary> call, Throwable t) {
                Log.d("Failure",t.getLocalizedMessage());
                if(t != null){
                    failureCovidWorldSummaryMutableLiveData.postValue(t.getLocalizedMessage());
                }
            }
        });
    }

    public MutableLiveData<CovidWorldSummary> getSuccessCovidWorldSummaryMutableLiveData(){
        return successCovidWorldSummaryMutableLiveData;
    }
    public MutableLiveData<String> getFailureCovidWorldSummaryMutableLiveData(){
        return failureCovidWorldSummaryMutableLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        successCovidWorldSummaryMutableLiveData.postValue(null);
        failureCovidWorldSummaryMutableLiveData.postValue(null);
    }
}
