package com.example.ptjm;

public class JobListModel {

    private String jobId;
    private String jobCategory;
    private String jobDescription;
    private String jobLocation;
    private String jobDuration;
    private String contactNumber;
    private int requireWorker;
    private int offerPrice;
    private int jobStatus;
    private String difficultyLevel;
    private String requireLevel;

    public JobListModel(String jobId, String jobCategory, String jobDescription, String jobLocation, String jobDuration, String contactNumber, int requireWorker, int offerPrice, int jobStatus, String difficultyLevel, String requireLevel) {
        this.jobId = jobId;
        this.jobCategory = jobCategory;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobDuration = jobDuration;
        this.contactNumber = contactNumber;
        this.requireWorker = requireWorker;
        this.offerPrice = offerPrice;
        this.jobStatus = jobStatus;
        this.difficultyLevel = difficultyLevel;
        this.requireLevel = requireLevel;
    }
}
