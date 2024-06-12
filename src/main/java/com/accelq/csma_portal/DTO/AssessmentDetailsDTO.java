package com.accelq.csma_portal.DTO;

import java.time.LocalDate;

public class AssessmentDetailsDTO {
    private String assessmentName;
    private String customerName;
    private String projectId;
    private String assessorName;
    private LocalDate assessmentDate;
    private Double amaScore;

    // Getters and Setters

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public Double getAmaScore() {
        return amaScore;
    }

    public void setAmaScore(Double amaScore) {
        this.amaScore = amaScore;
    }
}
