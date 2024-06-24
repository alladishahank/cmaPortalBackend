package com.accelq.csma_portal.AssessmentScore;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Integer> {

    @EntityGraph(attributePaths = {"parameter", "parameter.category"})
    @Query("SELECT s FROM AssessmentScore s WHERE s.assessment.id = :assessmentId ORDER BY s.parameter.id ASC")
    List<AssessmentScore> findByAssessmentIdOrderByParameterId(@Param("assessmentId") Integer assessmentId);
}
