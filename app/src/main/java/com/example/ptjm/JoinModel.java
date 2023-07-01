package com.example.ptjm;

public class JoinModel {
    private String userId;
    private String jobId;

    public JoinModel(String userId, String jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
