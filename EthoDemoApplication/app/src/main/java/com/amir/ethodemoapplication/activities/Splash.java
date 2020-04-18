package com.amir.ethodemoapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.amir.ethodemoapplication.R;
import com.amir.ethodemoapplication.core.BaseActivity;

public class Splash extends BaseActivity {


    private Handler handler;
    private static final int TIMER = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        handler = new Handler();

        /**
         * if user is logged in that go to dashboard else go to login
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getBoolean(ISLOGIN)){
                    startActivity(new Intent(mContext, Dashboard.class));
                }
                else {
                    startActivity(new Intent(mContext, Login.class));
                }
                finish();
            }
        },TIMER);
    }
}
