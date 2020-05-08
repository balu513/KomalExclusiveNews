package com.balu.komalexclusivenews.mvp.view;

import com.balu.komalexclusivenews.mvp.model.login.User;
import com.balu.komalexclusivenews.mvp.model.news.TopHeadlines;
import com.balu.komalexclusivenews.util.Utils;

public interface IView {

    interface ITopHeadLines{
        void OnTopHeadLinesSuccess(TopHeadlines topHeadLines);
        void OnTopHeadLinesFailure(String errorMsg);
    }
    interface ILogin{
        void onLogInSuccess(User user);
        void onLoginFailure(String errorMsg);
        void onSignUpSuccess(String msg);
        void onSignUpFailure(String errorMsg);
        void onValidationSuccess(Utils.LogInEnum logInEnum);
        void onValidationFailure(Utils.LogInEnum logInEnum, String errorMsg);
       // void onSetErrorEnabledView(Utils.LogInEnum logInEnum, boolean isEnable);

    }


}
