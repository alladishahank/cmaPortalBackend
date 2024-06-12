package com.accelq.csma_portal.Assessment;

import jakarta.persistence.*;
import com.accelq.csma_portal.Customer.Customer;
import com.accelq.csma_portal.User.User;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private String projectId;

    @ManyToOne
    @JoinColumn(name = "assessor_id", nullable = false)
    private User assessor;

    private LocalDate assessmentDate;

    @Column(name = "ama_score")
    private Double amaScore;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public User getAssessor() {
        return assessor;
    }

    public void setAssessor(User assessor) {
        this.assessor = assessor;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assessment that = (Assessment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
