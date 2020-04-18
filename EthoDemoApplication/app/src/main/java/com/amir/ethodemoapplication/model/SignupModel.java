package com.amir.ethodemoapplication.model;

import android.util.Patterns;

import com.amir.ethodemoapplication.activities.SignUp;

public class SignupModel {
    private String mEmail;
    private String mPassword;
    private String mName;

    public SignupModel(String mName,String mEmail, String mPassword){
        this.mEmail = mEmail;
        this.mName = mName;
        this.mPassword = mPassword;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getmPassword().length() > 5;
    }
}
