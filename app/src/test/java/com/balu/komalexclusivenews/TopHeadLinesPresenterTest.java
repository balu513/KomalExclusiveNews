package com.balu.komalexclusivenews;

import android.view.View;

import com.balu.komalexclusivenews.Const.Constants;
import com.balu.komalexclusivenews.model.NewsApiInterface;
import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;
import com.balu.komalexclusivenews.mvp.presenter.TopHeadLinesPresenter;
import com.balu.komalexclusivenews.mvp.view.IView;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TopHeadLinesPresenterTest {
    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }
    @Test
    public void topHeadLinesTest(){
        String COUNTRY_CODE = "ind";
        String API_KEY = "be0a5563a77c48d28f85e45ddf8a8a8e";

        NewsApiInterface  newsApiInterface = mock(NewsApiInterface.class);
        IView.ITopHeadLines topHeadLinesView= mock(IView.ITopHeadLines.class);

        TopHeadlines headlines = new TopHeadlines();

        when(newsApiInterface.getHeadLines(COUNTRY_CODE,API_KEY)).thenReturn(Single.just(headlines));
        TopHeadLinesPresenter topHeadLinesPresenter = new TopHeadLinesPresenter(null,topHeadLinesView,newsApiInterface);
        topHeadLinesPresenter.getTopHeadLines(COUNTRY_CODE);

        verify(topHeadLinesView).OnTopHeadLinesSuccess(headlines);
    }
}
