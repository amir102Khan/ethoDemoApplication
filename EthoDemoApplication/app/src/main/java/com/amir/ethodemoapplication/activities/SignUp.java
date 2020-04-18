package com.amir.ethodemoapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.databinding.ActivitySignUpBinding;

import java.util.BitSet;

public class SignUp extends BaseActivity implements View.OnClickListener {

    private ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        implementListener();
    }

    @Override
    public void onClick(View v) {
        if (v == binding.tvLogin){
            onBackPressed();
        }
    }

    private void implementListener(){
        binding.tvLogin.setOnClickListener(this);
    }
}
