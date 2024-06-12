package com.accelq.csma_portal.AssessmentScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Integer> {
    List<AssessmentScore> findByAssessmentId(Integer assessmentId);
}
