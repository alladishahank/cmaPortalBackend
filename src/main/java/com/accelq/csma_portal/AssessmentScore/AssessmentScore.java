package com.accelq.csma_portal.AssessmentScore;

import jakarta.persistence.*;
import com.accelq.csma_portal.Assessment.Assessment;
import com.accelq.csma_portal.Parameter.Parameter;

@Entity
@Table(name = "assessment_score")
public class AssessmentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private Parameter parameter;

    @Column(nullable = false)
    private Double score;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
