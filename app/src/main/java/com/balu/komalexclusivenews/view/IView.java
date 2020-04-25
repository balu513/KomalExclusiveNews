package com.balu.komalexclusivenews.view;

import com.balu.komalexclusivenews.model.news.TopHeadlines;

public interface IView {

    interface ITopHeadLines{
        void OnTopHeadLinesSuccess(TopHeadlines topHeadLines);
        void OnTopHeadLinesFailure(String errorMsg);
    }


}
