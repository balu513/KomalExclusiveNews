package com.balu.komalexclusivenews.presenter;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.NewsApiInterface;
import com.balu.komalexclusivenews.model.news.TopHeadlines;
import com.balu.komalexclusivenews.view.IView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopHeadLinesPresenter {
    private IView.ITopHeadLines topHeadLinesView;
    private NewsApiInterface newsApiInterface;

    public TopHeadLinesPresenter(IView.ITopHeadLines topHeadLinesView, NewsApiInterface newsApiInterface) {
        this.topHeadLinesView = topHeadLinesView;
        this.newsApiInterface = newsApiInterface;
    }

    public void getTopHeadLines(String countryCode){
        Call<TopHeadlines>topHeadLines = newsApiInterface.getHeadLines(countryCode, Constants.News.API_KEY);
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
        });
    }

}
