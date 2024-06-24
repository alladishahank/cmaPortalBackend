package com.accelq.csma_portal.AssessmentScore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accelq.csma_portal.Assessment.AssessmentRepository;
import com.accelq.csma_portal.Assessment.AssessmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AssessmentScoreService {

    private final AssessmentScoreRepository assessmentScoreRepository;
    private final AssessmentService assessmentService;
    private static final Logger LOGGER = Logger.getLogger(AssessmentScoreService.class.getName());

    @Autowired
    public AssessmentScoreService(AssessmentScoreRepository assessmentScoreRepository, AssessmentRepository assessmentRepository, AssessmentService assessmentService) {
        this.assessmentScoreRepository = assessmentScoreRepository;
        this.assessmentService = assessmentService;
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

    public void updateAssessmentScores(Integer assessmentId, List<Map<String, Object>> newScores) {
        List<AssessmentScore> existingScores = assessmentScoreRepository.findByAssessmentIdOrderByParameterId(assessmentId);

        for (Map<String, Object> newScore : newScores) {
            Integer parameterId = (Integer) newScore.get("parameterId");
            Double score = ((Number) newScore.get("score")).doubleValue();

            boolean found = false;
            for (AssessmentScore existingScore : existingScores) {
                if (existingScore.getParameter().getId().equals(parameterId)) {
                    existingScore.setScore(score);
                    found = true;
                    break;
                }
            }

            if (!found) {
                LOGGER.warning("Parameter ID: " + parameterId + " not found in existing scores for assessment ID: " + assessmentId);
            }
        }

        LOGGER.info("Saving updated scores for assessment ID: " + assessmentId);
        assessmentScoreRepository.saveAll(existingScores);
        assessmentService.updateAMAScore(assessmentId);
    }



    public List<AssessmentScore> getAssessmentScoresByAssessmentId(Integer assessmentId) {
        return assessmentScoreRepository.findByAssessmentIdOrderByParameterId(assessmentId);
    }
}
