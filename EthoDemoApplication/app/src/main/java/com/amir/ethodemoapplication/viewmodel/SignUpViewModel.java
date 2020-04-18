package com.amir.ethodemoapplication.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.model.LoginModel;
import com.amir.ethodemoapplication.model.SignupModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpViewModel extends ViewModel {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();

    private AuthListener authListener;
    private MutableLiveData<SignupModel> userMutableLiveData;


    public void setAuthListner(AuthListener authListener){
        this.authListener = authListener;
    }

    public void onSignUpClicked(){
        authListener.onStarting();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getValue(),password.getValue())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = FirebaseAuth.
                                    getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name.getValue()).build();

                            firebaseUser.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            authListener.onSuccess();
                                        }
                                    });
                        }
                        else {
                            authListener.onError("Sign Up failed");
                        }
                    }
                });
    }

}
