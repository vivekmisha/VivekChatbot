package com.example.vivekchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.vivekchatbot.utility.Constants;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPrefernces sharedPrefernces = new SharedPrefernces(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPrefernces.getValueBool(Constants.IS_LOGGED_IN)){
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                    finish();
                }
            }
        },1000);
    }
}