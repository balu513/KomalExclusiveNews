package com.balu.komalexclusivenews.viewmodel.cricket;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.application.KomalExclusiveNewsApplication;
import com.balu.komalexclusivenews.model.CricketApiInterface;
import com.balu.komalexclusivenews.model.cricket.MatchCalendar;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MatchCalendarViewModel extends ViewModel {
    @Inject
    CricketApiInterface cricketApiInterface;

    public MatchCalendarViewModel() {
        KomalExclusiveNewsApplication.getKomalNewsComponent().inject(this);
    }

    public MutableLiveData<MatchCalendar> getSuccessMatchCalendarMutableLiveData() {
        return successMatchCalendarMutableLiveData;
    }

    public MutableLiveData<String> getFailureMatchCalendarMutableLiveData() {
        return failureMatchCalendarMutableLiveData;
    }

    MutableLiveData<MatchCalendar> successMatchCalendarMutableLiveData = new MutableLiveData<MatchCalendar>();
    MutableLiveData<String> failureMatchCalendarMutableLiveData = new MutableLiveData<String>();

    public void loadMatchCalendar(){
        cricketApiInterface.getMatchCalendar(Constants.Cricket.API_KEY_CRICKET)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeWith(new DisposableSingleObserver<MatchCalendar>() {
                    @Override
                    public void onSuccess(@NonNull MatchCalendar matchCalendar) {
                        if(matchCalendar != null) {
                            successMatchCalendarMutableLiveData.postValue(matchCalendar);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                            failureMatchCalendarMutableLiveData.postValue(e.getLocalizedMessage());
                    }
                });

    }
}
