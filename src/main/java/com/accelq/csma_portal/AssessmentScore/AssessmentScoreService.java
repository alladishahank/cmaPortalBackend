package com.accelq.csma_portal.AssessmentScore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentScoreService {

    private final AssessmentScoreRepository assessmentScoreRepository;

    @Autowired
    public AssessmentScoreService(AssessmentScoreRepository assessmentScoreRepository) {
        this.assessmentScoreRepository = assessmentScoreRepository;
    }

    public List<AssessmentScore> getAllAssessmentScores() {
        return assessmentScoreRepository.findAll();
    }

    public Optional<AssessmentScore> getAssessmentScoreById(Integer id) {
        return assessmentScoreRepository.findById(id);
    }

    public void addNewAssessmentScore(AssessmentScore assessmentScore) {
        assessmentScoreRepository.save(assessmentScore);
    }

    public void deleteAssessmentScore(Integer id) {
        boolean exists = assessmentScoreRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("AssessmentScore with id " + id + " does not exist.");
        }
        assessmentScoreRepository.deleteById(id);
    }

    public void updateAssessmentScore(Integer id, AssessmentScore updatedAssessmentScore) {
        AssessmentScore assessmentScore = assessmentScoreRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("AssessmentScore with id " + id + " does not exist."));

        assessmentScore.setAssessment(updatedAssessmentScore.getAssessment());
        assessmentScore.setParameter(updatedAssessmentScore.getParameter());
        assessmentScore.setScore(updatedAssessmentScore.getScore());

        assessmentScoreRepository.save(assessmentScore);
    }

    public List<AssessmentScore> getAssessmentScoresByAssessmentId(Integer assessmentId) {
        return assessmentScoreRepository.findByAssessmentId(assessmentId);
    }
}
