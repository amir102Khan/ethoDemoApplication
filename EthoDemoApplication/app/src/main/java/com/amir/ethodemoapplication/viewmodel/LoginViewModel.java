package com.amir.ethodemoapplication.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.model.LoginModel;
import com.amir.ethodemoapplication.util.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private AuthListener authListener;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setAuthListner(AuthListener authListener) {
        this.authListener = authListener;
    }

    /**
     * method called when on login button will be clicked
     */
    public void onLoginClicked() {
        LoginModel loginModel = new LoginModel(email.getValue(), password.getValue());
        if (!Common.isValidEmail(loginModel.getEmail())) {
            authListener.onValidationError("Email is not valid");
        } else if (!Common.validateEditText(loginModel.getEmail())) {
            authListener.onValidationError("Email is empty");
        } else if (!Common.validateEditText(loginModel.getPassword())) {
            authListener.onValidationError("Password is empty");
        } else if (!loginModel.isPasswordLengthGreaterThan5()) {
            authListener.onValidationError("Password is too short");
        } else {
            if (!Common.checkInternetConnection(context)) {
                authListener.onValidationError("check your internet connectivity");
            } else {
                authListener.onStarting();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(loginModel.getEmail(), loginModel.getPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    authListener.onSuccess();
                                } else {
                                    authListener.onError("Login failed");
                                }

                            }
                        });
            }

        }
    }
}
