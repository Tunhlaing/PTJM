package com.example.ptjm.model;

public class CreateJobModel {

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
    private String jobCreatedDateTime;

    //    public JobListModel(){
//
//    }
    public CreateJobModel(String jobId, String jobCategory, String jobDescription, String jobLocation, String jobDuration, String contactNumber, int requireWorker, int offerPrice, int jobStatus, String difficultyLevel, String requireLevel, String jobCreatedDateTime) {
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
        this.jobCreatedDateTime = jobCreatedDateTime;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobDuration() {
        return jobDuration;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public int getRequireWorker() {
        return requireWorker;
    }

    public int getOfferPrice() {
        return offerPrice;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getRequireLevel() {
        return requireLevel;
    }

    public String getJobCreatedDateTime() {
        return jobCreatedDateTime;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setRequireWorker(int requireWorker) {
        this.requireWorker = requireWorker;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setRequireLevel(String requireLevel) {
        this.requireLevel = requireLevel;
    }

    public void setJobCreatedDateTime(String jobCreatedDateTime) {
        this.jobCreatedDateTime = jobCreatedDateTime;
    }
}


