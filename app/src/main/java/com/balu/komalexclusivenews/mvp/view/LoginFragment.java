package com.balu.komalexclusivenews.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.balu.komalexclusivenews.R;
import com.balu.komalexclusivenews.application.KomalExclusiveNewsApplication;
import com.balu.komalexclusivenews.mvp.model.login.User;
import com.balu.komalexclusivenews.mvp.model.login.UserDao;
import com.balu.komalexclusivenews.mvp.presenter.LogInPresenter;
import com.balu.komalexclusivenews.util.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements IView.ILogin{

    private static final String ARG_PARAM1 = "param1";

    private boolean isLogin;

    @BindView(R.id.et_login_name)
    TextInputEditText name_et;
    @BindView(R.id.et_login_phNo)
    TextInputEditText phoneNum_et;
    @BindView(R.id.et_login_email)
    TextInputEditText email_et;
    @BindView(R.id.et_login_password)
    TextInputEditText password_et;

    @BindView(R.id.textInputLay_login_name)
    TextInputLayout name_textInputLay;
    @BindView(R.id.textInputLay_login_phNo)
    TextInputLayout phoneNum_textInputLay;
    @BindView(R.id.textInputLay_login_email)
    TextInputLayout email_textInputLay;
    @BindView(R.id.textInputLay_login_password)
    TextInputLayout password_textInputLay;

    @BindView(R.id.btn_login_submit)
    Button btnLoginSubmit;

    @Inject
    Context context;

    @Inject
    UserDao userDao;
    private LogInPresenter logInPresenter;

    public LoginFragment() { }

    public static LoginFragment newInstance(boolean param1) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isLogin = getArguments().getBoolean(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        KomalExclusiveNewsApplication.getKomalNewsComponent().inject(this);
        logInPresenter = new LogInPresenter(this, isLogin, userDao);
        initView();

        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                if(isLogin){
                    logInPresenter.isValidUser(name_et.getText().toString(), password_et.getText().toString());
                }
                else{
                    logInPresenter.createUser(name_et.getText().toString(), password_et.getText().toString(),email_et.getText().toString(),phoneNum_et.getText().toString());
                }
            }
        });

        return view;
    }

    private void initView(){
        name_et.addTextChangedListener(new ValidationTextWatcher(name_et));
        password_et.addTextChangedListener(new ValidationTextWatcher(password_et));
        if(isLogin){
            btnLoginSubmit.setText("Login");
            email_et.setVisibility(View.GONE);
            phoneNum_et.setVisibility(View.GONE);
        }
        else{
            btnLoginSubmit.setText("Register");
            email_et.addTextChangedListener(new ValidationTextWatcher(email_et));
            phoneNum_et.addTextChangedListener(new ValidationTextWatcher(phoneNum_et));
            email_et.setVisibility(View.VISIBLE);
            phoneNum_et.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLogInSuccess(User user) {
        Toast.makeText(getActivity(), "Successfully Login", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity.class));

    }

    @Override
    public void onLoginFailure(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpFailure(String errorMsg) {
        Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationSuccess(Utils.LogInEnum logInEnum) {
        switch (logInEnum){
            case NAME:
                name_textInputLay.setErrorEnabled(false);
                break;
            case EMAIL:
                email_textInputLay.setErrorEnabled(false);
                break;
            case PASSWORD:
                password_textInputLay.setErrorEnabled(false);
                break;
            case PHONENUMBER:
                phoneNum_textInputLay.setErrorEnabled(false);
                break;
        }
    }

    @Override
    public void onValidationFailure(Utils.LogInEnum logInEnum, String errorMsg) {
        switch (logInEnum){
            case NAME:
                name_textInputLay.setError(errorMsg);
                break;
            case EMAIL:
                email_textInputLay.setError(errorMsg);
                break;
            case PASSWORD:
                password_textInputLay.setError(errorMsg);
                break;
            case PHONENUMBER:
                phoneNum_textInputLay.setError(errorMsg);
                break;
        }
    }


    private class ValidationTextWatcher implements TextWatcher {

        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_login_password:
                    logInPresenter.validateFields(password_et.getText().toString(), Utils.LogInEnum.PASSWORD);
                    break;
                case R.id.et_login_email:
                    logInPresenter.validateFields(email_et.getText().toString(), Utils.LogInEnum.EMAIL);
                    break;
                case R.id.et_login_name:
                    logInPresenter.validateFields(name_et.getText().toString(), Utils.LogInEnum.NAME);
                    break;
                case R.id.et_login_phNo:
                    logInPresenter.validateFields(phoneNum_et.getText().toString(), Utils.LogInEnum.PHONENUMBER);
                    break;
            }
        }
    }

   // @OnClick(R.id.btn_login_submit)
    public void submit(View view){
        if(isLogin){
            logInPresenter.isValidUser(name_et.getText().toString(), password_et.getText().toString());
        }
        else{
            logInPresenter.createUser(name_et.getText().toString(), password_et.getText().toString(),email_et.getText().toString(),phoneNum_et.getText().toString());
        }



    }
}
