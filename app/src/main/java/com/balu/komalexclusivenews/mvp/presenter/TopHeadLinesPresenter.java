package com.balu.komalexclusivenews.mvp.presenter;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.NewsApiInterface;
import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;
import com.balu.komalexclusivenews.mvp.view.IView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopHeadLinesPresenter implements LifecycleObserver {
    private Lifecycle mLifecycle;
    private IView.ITopHeadLines topHeadLinesView;
    private NewsApiInterface newsApiInterface;
    DisposableSingleObserver<TopHeadlines> topHeadlinesDisposableSingleObserver;

    public TopHeadLinesPresenter(Lifecycle lifecycle,IView.ITopHeadLines topHeadLinesView, NewsApiInterface newsApiInterface) {
        mLifecycle = lifecycle;
        this.topHeadLinesView = topHeadLinesView;
        this.newsApiInterface = newsApiInterface;
        if (this.mLifecycle != null) {
            mLifecycle.addObserver(this);
        }
    }

    public void getTopHeadLines(String countryCode){
        topHeadlinesDisposableSingleObserver = newsApiInterface.getHeadLines(countryCode, Constants.News.API_KEY).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<TopHeadlines>() {
                    @Override
                    public void onSuccess(TopHeadlines topHeadlines) {
                        if (topHeadlines != null) {
                            topHeadLinesView.OnTopHeadLinesSuccess(topHeadlines);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        topHeadLinesView.OnTopHeadLinesFailure("Top headlines news loading failed");
                    }
                });
        /*Call<TopHeadlines>topHeadLines = newsApiInterface.getHeadLines(countryCode, Constants.News.API_KEY);
        topHeadLines.enqueue(new Callback<TopHeadlines>() {
            @Override
            public void onResponse(Call<TopHeadlines> call, Response<TopHeadlines> response) {
                if (response.body() != null){
                    topHeadLinesView.OnTopHeadLinesSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<TopHeadlines> call, Throwable t) {
                topHeadLinesView.OnTopHeadLinesFailure("Top headlines news loading failed");
            }
        });*/
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy(){
        topHeadlinesDisposableSingleObserver.dispose();
        if(mLifecycle != null)
            mLifecycle.removeObserver(this);

    }

}
