package com.balu.komalexclusivenews.mvp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginFragment extends Fragment implements IView.ILogin{

    private static final String ARG_PARAM1 = "param1";
    private static final int PERMISSION_REQUEST_CODE = 200;

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

    private void handleRunTimePermissions(){
        if (checkPermission()){
            Toast.makeText(getContext(),"Permission already granted.",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(),"Please Request permission.",Toast.LENGTH_SHORT).show();
            requestPermission();
        }

    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(),new String[]{READ_CONTACTS,WRITE_EXTERNAL_STORAGE,CAMERA}, PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermission(){
        int resultcontact = ContextCompat.checkSelfPermission(getContext(),READ_CONTACTS);
        int resultStorage = ContextCompat.checkSelfPermission(getContext(),WRITE_EXTERNAL_STORAGE);
        int resultCamera = ContextCompat.checkSelfPermission(getContext(), CAMERA);

        return resultCamera == PackageManager.PERMISSION_GRANTED && resultStorage == PackageManager.PERMISSION_GRANTED && resultcontact == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0){
                boolean contactAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean cameraAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                if (contactAccepted && storageAccepted && cameraAccepted)
                    Toast.makeText(getContext(), "Permission Granted, Now you can access contact storage and camera.", Toast.LENGTH_LONG).show();
                else {

                    Toast.makeText(getContext(), "Permission Denied, You cannot access contact storage and camera.", Toast.LENGTH_LONG).show();

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                        if(shouldShowRequestPermissionRationale(CAMERA)){
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{READ_CONTACTS,WRITE_EXTERNAL_STORAGE,CAMERA},
                                                        PERMISSION_REQUEST_CODE);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
            }
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        handleRunTimePermissions();
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
        view.findViewById(R.id.google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin();
            }
        });
        view.findViewById(R.id.facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
            }
        });
        view.findViewById(R.id.twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twitterLogin();
            }
        });

        return view;
    }

    private void twitterLogin() {
        Toast.makeText(getActivity().getApplicationContext(),"Twitter sign in",Toast.LENGTH_SHORT).show();
    }

    private void facebookLogin() {
        Toast.makeText(getActivity().getApplicationContext(),"Facebook sign in",Toast.LENGTH_SHORT).show();
    }

    private void googleLogin() {
        Toast.makeText(getActivity().getApplicationContext(),"Google sign in",Toast.LENGTH_SHORT).show();

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
