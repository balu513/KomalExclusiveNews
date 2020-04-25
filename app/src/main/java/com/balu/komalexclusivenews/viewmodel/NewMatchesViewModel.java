package com.balu.komalexclusivenews.viewmodel;
;
import android.util.Log;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.cricket.NewMatches;
import com.balu.komalexclusivenews.apiClient.CricketApiClient;

import java.util.Random;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewMatchesViewModel extends ViewModel {

    MutableLiveData<NewMatches> successNewMatchesResponseLiveDate = new MutableLiveData<NewMatches>();
    MutableLiveData<String> failureNewMatchesResponseLiveDate = new MutableLiveData<String>();

    public final ObservableField<String> randomValue = new ObservableField<>();

    public NewMatchesViewModel(){
        setRandomValue();
    }

    public void loadNewMatches(){
        Log.d("NewMatchesViewModel", "loadNewMatches");
        Call<NewMatches> matches = CricketApiClient.getCricketApiClient().getNewMatches(Constants.Cricket.API_KEY_CRICKET);
        matches.enqueue(new Callback<NewMatches>() {
            @Override
            public void onResponse(Call<NewMatches> call, Response<NewMatches> response) {
                Log.d("Success",response.body().toString());
                if(response!=null && response.body()!=null) {
                    successNewMatchesResponseLiveDate.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewMatches> call, Throwable t) {
                Log.d("Failure",t.getLocalizedMessage());
                if(t != null){
                    failureNewMatchesResponseLiveDate.postValue(t.getLocalizedMessage());
                }
            }
        });
    }

    public MutableLiveData<NewMatches> getSuccessNewMatchesResponseLiveDate(){
        return successNewMatchesResponseLiveDate;
    }
    public MutableLiveData<String> getFailureNewMatchesResponseLiveDate(){
        return failureNewMatchesResponseLiveDate;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        successNewMatchesResponseLiveDate.postValue(null);
        failureNewMatchesResponseLiveDate.postValue(null);
    }


    // Two way Binding
    public void setRandomValue() {
        this.randomValue.set(new Random().nextInt(1000)+"");
    }
    public void tvBindiClicked(){
        setRandomValue();
    }
}
