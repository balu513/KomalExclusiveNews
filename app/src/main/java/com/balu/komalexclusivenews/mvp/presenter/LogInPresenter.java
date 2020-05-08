package com.balu.komalexclusivenews.mvp.presenter;

import android.content.Context;

import com.balu.komalexclusivenews.mvp.model.login.User;
import com.balu.komalexclusivenews.mvp.model.login.UserDao;
import com.balu.komalexclusivenews.mvp.view.IView;
import com.balu.komalexclusivenews.util.Utils;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LogInPresenter {
    private final IView.ILogin loginView;
    private boolean isLogIn;
    private final UserDao userDao;
    private DisposableSingleObserver<User> disposableSingleObserver;

    public LogInPresenter(IView.ILogin loginView, boolean isLogIn, UserDao userDao) {
        this.loginView = loginView;
        this.isLogIn = isLogIn;
        this.userDao = userDao;
    }
    public void validateFields(String text, Utils.LogInEnum logInEnum){
        switch (logInEnum){
            case NAME:
                validateName(text);
                break;
            case EMAIL:
                validateEmail(text);
                break;
            case PASSWORD:
                validatePassword(text);
                break;
            case PHONENUMBER:
                validatePhneNumber(text);
                break;
        }
    }
    private boolean validatePassword(String password) {
        if (password.trim().isEmpty()) {
            loginView.onValidationFailure(Utils.LogInEnum.PASSWORD,"Password is required");
            return false;
        }else if(password.length() < 6){
            loginView.onValidationFailure(Utils.LogInEnum.PASSWORD,"Password can't be less than 6 digit");
            return false;
        }
        else {
            loginView.onValidationSuccess(Utils.LogInEnum.PASSWORD);
        }
        return true;
    }
    private boolean validateName(String name) {
        if (name.trim().isEmpty()) {
            loginView.onValidationFailure(Utils.LogInEnum.NAME,"Name is required");
            return false;
        }else if(name.length() < 4){
            loginView.onValidationFailure(Utils.LogInEnum.NAME,"Name can't be less than 4 char");
            return false;
        }
        else {
            loginView.onValidationSuccess(Utils.LogInEnum.NAME);
        }
        return true;
    }

    private boolean validatePhneNumber(String phoneNum) {
        if (phoneNum.trim().isEmpty()) {
            loginView.onValidationFailure(Utils.LogInEnum.PHONENUMBER,"Phone Number is required");
            return false;
        }else if(phoneNum.length() < 10){
            loginView.onValidationFailure(Utils.LogInEnum.PHONENUMBER,"phone number can't be less than 10 digits");
            return false;
        }
        else {
            loginView.onValidationSuccess(Utils.LogInEnum.PHONENUMBER);
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.trim().isEmpty()) {
            loginView.onValidationSuccess(Utils.LogInEnum.EMAIL);
        } else {
            Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            if (!isValid) {
                loginView.onValidationFailure(Utils.LogInEnum.EMAIL,"Invalid Email address, ex: abc@example.com");
                return false;
            } else {
                loginView.onValidationSuccess(Utils.LogInEnum.EMAIL);
            }
        }
        return true;
    }

    public void isValidUser(String name, String password)
    {
        if(validateName(name) && validatePassword(password)) {
            disposableSingleObserver = userDao.getUser(name, password)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableSingleObserver<User>() {
                        @Override
                        public void onSuccess(User user) {
                            if(user != null){
                                loginView.onLogInSuccess(user);
                            }
                            else{
                                loginView.onLoginFailure("Enterd name or password is incorrect");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            loginView.onLoginFailure(e.getLocalizedMessage());
                        }
                    });
        }
    }

    public void createUser(final String name, final String password, final String email, final String phoneNum){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                userDao.createUser(new User(name, email, password, phoneNum));
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribeWith(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                loginView.onSignUpSuccess("Successfully created ");

            }

            @Override
            public void onError(Throwable e) {
                loginView.onSignUpFailure("Error while creating");

            }

        });

    }
}
