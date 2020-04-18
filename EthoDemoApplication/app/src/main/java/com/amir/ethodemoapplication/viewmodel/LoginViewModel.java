package com.amir.ethodemoapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ethodemoapplication.activities.Login;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.model.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    private AuthListener authListener;
    private MutableLiveData<LoginModel> userMutableLiveData;

    public MutableLiveData<LoginModel> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public void setAuthListner(AuthListener authListener){
        this.authListener = authListener;
    }
    LiveData<LoginModel> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }

    public void onLoginClicked() {
        authListener.onStarting();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getValue(),password.getValue())
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            authListener.onSuccess();
                        }
                        else {
                            authListener.onError("Login failed");
                        }

                    }
                });


    }
}
