package com.example.ptjm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.ptjm.ui.CreateJobActivity;

public class JobListActivity extends AppCompatActivity {

    ImageButton bt_add;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        bt_add = findViewById(R.id.bt_add);
        onClick();

    }

    private void onClick() {
        bt_add.setOnClickListener(v -> {
            String sentUserId = getIntent().getStringExtra("sentUserID");

            startActivity(new Intent(JobListActivity.this, CreateJobActivity.class).putExtra("sentUserIDFromJobList",sentUserId));

        });
    }
}