package com.example.ptjm.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ptjm.JobListModel;
import com.example.ptjm.JoinModel;
import com.example.ptjm.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateJobActivity extends AppCompatActivity {
    TextInputLayout ti_jobCategory,ti_jobDescription,ti_jobLocation,ti_jobDuration,ti_ContactNumber,ti_requireWorker,ti_OfferPrice;
    TextInputEditText et_jobCategory,et_jobDescription,et_jobLocation,et_jobDuration,et_ContactNumber,et_requireWorker,et_OfferPrice;
    RadioGroup rgDifficulty,rgRequire;
    Button bt_post;

    String jobID;
    int levelID;
    String difficultyLevel = "";
    String requireLevel = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        initView();
        onClick();
        rgDifficulty.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton radioButton = findViewById(checkedId);
            difficultyLevel = radioButton.getText().toString();
            //difficultyLevel = difficultySelectedValue;

        });
        rgRequire.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            requireLevel = radioButton.getText().toString();
           // requireLevel = RequireLevelSelectedValue;
        });

    }

    private void initView() {
        ti_jobCategory = findViewById(R.id.ti_jobCategory);
        ti_jobDescription = findViewById(R.id.ti_jobDescription);
        ti_jobLocation = findViewById(R.id.ti_jobLocation);
        ti_jobDuration = findViewById(R.id.ti_jobDuration);
        ti_ContactNumber = findViewById(R.id.ti_ContactNumber);
        ti_requireWorker = findViewById(R.id.ti_requireWorker);
        ti_OfferPrice = findViewById(R.id.ti_OfferPrice);

        et_jobCategory = findViewById(R.id.et_jobCategory);
        et_jobDescription = findViewById(R.id.et_jobDescription);
        et_jobLocation = findViewById(R.id.et_jobLocation);
        et_jobDuration = findViewById(R.id.et_jobDuration);
        et_ContactNumber = findViewById(R.id.et_ContactNumber);
        et_requireWorker = findViewById(R.id.et_requireWorker);
        et_OfferPrice = findViewById(R.id.et_OfferPrice);

        rgDifficulty = findViewById(R.id.rgDifficulty);
        rgRequire = findViewById(R.id.rgRequire);


        bt_post = findViewById(R.id.bt_post);

    }
    private void onClick() {
        bt_post.setOnClickListener(v -> {

            createJob();
            joinUserAndJob();
            finish();

        });
    }
    private void joinUserAndJob(){


        DatabaseReference joinRef = FirebaseDatabase.getInstance().getReference().child("user_job_relationship");
        String sentUserId = getIntent().getStringExtra("sentUserIDFromJobList");
        String joinId = joinRef.push().getKey();
        String jobID47 = createJob();
        JoinModel joinModel = new JoinModel(sentUserId,jobID47);
        joinRef.child(joinId).setValue(joinModel).addOnSuccessListener(aVoid ->{
            Toast.makeText(this, "Job ID and User ID joined successfully", Toast.LENGTH_SHORT).show();

        }).addOnFailureListener(e->{
            Toast.makeText(this, "Joining failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        });

    }
    private String createJob(){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("jobs_table");
            if (jobID == null) {
                jobID = myRef.push().getKey();
            }
            String jobCategory = et_jobCategory.getText().toString();
            String jobDescription = et_jobDescription.getText().toString();
            String jobLocation = et_jobLocation.getText().toString();
            String jobDuration = et_jobDuration.getText().toString();
            String contactNumber = et_ContactNumber.getText().toString();
            int requireWorker = Integer.parseInt(et_requireWorker.getText().toString());
            int offerPrice = Integer.parseInt(et_OfferPrice.getText().toString());
            int jobStatus = 0;



            JobListModel jobListModel = new JobListModel(jobID,jobCategory,jobDescription,jobLocation,jobDuration,contactNumber,
                                                        requireWorker,offerPrice,jobStatus,difficultyLevel,requireLevel);


           myRef.child(jobID).setValue(jobListModel).addOnSuccessListener(aVoid -> {
                       Toast.makeText(this, "Job created successfully", Toast.LENGTH_SHORT).show();
                   })
                   .addOnFailureListener(e -> {
                       Toast.makeText(this, "Job creation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                   });

        return jobID;

        }



}