package com.example.ptjm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt_register_poster, bt_register_seeker,bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClicked();


    }
    private void initView() {
        bt_register_poster = findViewById(R.id.bt_register_poster);
        bt_register_seeker = findViewById(R.id.bt_register_seeker);
        bt_login = findViewById(R.id.bt_login);
    }
    private void onClicked() {
        bt_register_poster.setOnClickListener(v -> {
            startActivity(new Intent(this,RegisterActivity.class).putExtra("posterType",1));


        });
        bt_register_seeker.setOnClickListener(v -> {
            startActivity(new Intent(this,RegisterActivity.class).putExtra("posterType",0));

        });
        bt_login.setOnClickListener((v -> {
            startActivity(new Intent(this,LoginActivity.class));

        }));
    }

}