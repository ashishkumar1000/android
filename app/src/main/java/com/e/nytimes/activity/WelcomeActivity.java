package com.e.nytimes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.e.nytimes.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Runnable r = () -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        };

        Handler h = new Handler();
        h.postDelayed(r, 2000);
    }
}