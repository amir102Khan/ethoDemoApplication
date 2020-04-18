package com.amir.ethodemoapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;
import com.amir.ethodemoapplication.databinding.ActivityLoginBinding;

public class Login extends BaseActivity implements View.OnClickListener {

    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        implementListener();
    }

    @Override
    public void onClick(View v) {
        if (v == binding.tvCreateAccount){
            startActivity(new Intent(mContext,SignUp.class));
        }
    }


    private void implementListener(){
        binding.tvCreateAccount.setOnClickListener(this);
    }
}
