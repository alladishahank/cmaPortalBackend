package com.accelq.csma_portal.DTO;

import java.time.LocalDate;
import java.util.List;

public class ViewFullAssessmentDetailsDTO {
    private Integer id;
    private String assessmentName;
    private String customerName;
    private String projectId;
    private String assessorName;
    private LocalDate assessmentDate;
    private Double amaScore;
    private List<DetailedAssessmentScoreDTO> assessmentScores;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public List<DetailedAssessmentScoreDTO> getAssessmentScores() {
        return assessmentScores;
    }

    public void setAssessmentScores(List<DetailedAssessmentScoreDTO> assessmentScores) {
        this.assessmentScores = assessmentScores;
    }
}
