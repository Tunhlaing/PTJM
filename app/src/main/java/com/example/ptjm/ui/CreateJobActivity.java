package com.example.ptjm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ptjm.databinding.ActivityCreateJobBinding;
import com.example.ptjm.model.CreateJobModel;
import com.example.ptjm.model.JoinModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateJobActivity extends AppCompatActivity {

    private ActivityCreateJobBinding createJobBinding;

    String jobID;
    int levelID;
    String difficultyLevel = "";
    String requireLevel = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createJobBinding = ActivityCreateJobBinding.inflate(getLayoutInflater());
        setContentView(createJobBinding.getRoot());
        onClick();
        createJobBinding.rgDifficulty.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton radioButton = findViewById(checkedId);
            difficultyLevel = radioButton.getText().toString();

        });
        createJobBinding.rgRequire.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            requireLevel = radioButton.getText().toString();
        });

    }

    private void onClick() {
        createJobBinding.btPost.setOnClickListener(v -> {

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
            String jobCategory = createJobBinding.etJobCategory.getText().toString();
            String jobDescription = createJobBinding.etJobDescription.getText().toString();
            String jobLocation = createJobBinding.etJobLocation.getText().toString();
            String jobDuration = createJobBinding.etJobDuration.getText().toString();
            String contactNumber = createJobBinding.etContactNumber.getText().toString();
            int requireWorker = Integer.parseInt(createJobBinding.etRequireWorker.getText().toString());
            int offerPrice = Integer.parseInt(createJobBinding.etOfferPrice.getText().toString());
            int jobStatus = 0;
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String jobCreatedDateTime = dateFormat.format(currentDate);



            CreateJobModel jobListModel = new CreateJobModel(jobID,jobCategory,jobDescription,jobLocation,jobDuration,contactNumber,
                                                        requireWorker,offerPrice,jobStatus,difficultyLevel,requireLevel,jobCreatedDateTime);


           myRef.child(jobID).setValue(jobListModel).addOnSuccessListener(aVoid -> {
                       Toast.makeText(this, "Job created successfully", Toast.LENGTH_SHORT).show();
                   })
                   .addOnFailureListener(e -> {
                       Toast.makeText(this, "Job creation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                   });

        return jobID;

        }



}