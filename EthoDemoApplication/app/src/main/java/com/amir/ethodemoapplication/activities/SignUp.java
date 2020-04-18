package com.amir.ethodemoapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.databinding.ActivitySignUpBinding;
import com.amir.ethodemoapplication.interfaces.AuthListener;
import com.amir.ethodemoapplication.viewmodel.SignUpViewModel;

public class SignUp extends BaseActivity implements View.OnClickListener, AuthListener {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        loader = (ConstraintLayout) binding.loader.getRoot();
        setViewModel();
        implementListener();
    }

    /**
     *  method to set the view model
     */
    private void setViewModel() {
        SignUpViewModel signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding.setSignUpViewModel(signUpViewModel);
        binding.setLifecycleOwner(this);
        signUpViewModel.setAuthListner(this);
        signUpViewModel.setContext(mContext);
    }

    @Override
    public void onClick(View v) {
        if (v == binding.tvLogin) {
            onBackPressed();
        }
    }

    /**
     * method to implement on click listener
     */
    private void implementListener() {
        binding.tvLogin.setOnClickListener(this);
    }

    @Override
    public void onStarting() {
        showLoader();
        hideKeyboard();
    }

    @Override
    public void onSuccess() {
        hideLoader();
        sp.setBoolean(ISLOGIN, true);
        startActivity(new Intent(mContext, Dashboard.class));
        finish();
    }

    @Override
    public void onError(String message) {
        hideLoader();
        showToast(message);
    }

    @Override
    public void onValidationError(String message) {
        showToast(message);
    }
}
