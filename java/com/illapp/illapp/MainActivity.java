package com.illapp.illapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.illapp.illapp.loginzone.LoginPage;

public class MainActivity extends AppCompatActivity {


    Animation animation;
    TextView textView,logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.x);
        logo = findViewById(R.id.logo);

        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        textView.startAnimation(animation);
        logo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
                finishAffinity();
            }
        }, 6500);
    }
}