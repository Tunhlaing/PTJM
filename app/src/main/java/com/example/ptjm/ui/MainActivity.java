package com.example.ptjm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ptjm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        onClicked();


    }
        private void onClicked() {
        mainBinding.btRegisterPoster.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class).putExtra("registerType",1));


        });
        mainBinding.btRegisterSeeker.setOnClickListener(v -> {
            startActivity(new Intent(this,RegisterActivity.class).putExtra("registerType",0));

        });
        mainBinding.btLogin.setOnClickListener((v -> {
            startActivity(new Intent(this, LoginActivity.class));

        }));
    }


}