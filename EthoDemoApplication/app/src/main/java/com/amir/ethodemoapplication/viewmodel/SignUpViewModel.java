package com.amir.ethodemoapplication.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.interfaces.Constants;
import com.amir.ethodemoapplication.model.LoginModel;
import com.amir.ethodemoapplication.model.SignupModel;
import com.amir.ethodemoapplication.model.UserModel;
import com.amir.ethodemoapplication.util.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpViewModel extends ViewModel implements Constants {
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();

    private AuthListener authListener;
    private Context context;

    public void setContext(Context context){
        this.context = context;
    }


    public void setAuthListner(AuthListener authListener){
        this.authListener = authListener;
    }

    public void onSignUpClicked(){
        final SignupModel signupModel = new SignupModel(name.getValue(),email.getValue(),password.getValue());

        if (!Common.validateEditText(signupModel.getmName())){
            authListener.onValidationError("Name is empty");
        }
        else if (!Common.isValidEmail(signupModel.getmEmail())){
            authListener.onValidationError("Email is not valid");
        }
        else if (!Common.validateEditText(signupModel.getmEmail())){
            authListener.onValidationError("Email is empty");
        }
        else if (!Common.validateEditText(signupModel.getmPassword())){
            authListener.onValidationError("Password is empty");
        }
        else if (!signupModel.isPasswordLengthGreaterThan5()){
            authListener.onValidationError("Password is too short");
        }
        else {
            if (!Common.checkInternetConnection(context)){
                authListener.onValidationError("check your internet connectivity");
            }
            else {
                authListener.onStarting();
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(signupModel.getmEmail(),signupModel.getmPassword())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser firebaseUser = FirebaseAuth.
                                            getInstance().getCurrentUser();
/*
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(signupModel.getmName()).build();

                                    firebaseUser.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    authListener.onSuccess();
                                                }
                                            });*/

                                    DatabaseReference databaseReference = FirebaseDatabase
                                            .getInstance().getReference(USER);

                                    UserModel userModel = new UserModel(signupModel.getmName(),
                                            signupModel.getmEmail(),firebaseUser.getUid(),"","");


                                    databaseReference.child(firebaseUser.getUid()).setValue(userModel);
                                    authListener.onSuccess();

                                }
                                else {
                                    authListener.onError("Sign Up failed");
                                }
                            }
                        });
            }
        }

    }

}
