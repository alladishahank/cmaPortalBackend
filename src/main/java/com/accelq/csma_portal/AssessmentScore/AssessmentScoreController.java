package com.accelq.csma_portal.AssessmentScore;

import com.accelq.csma_portal.Assessment.Assessment;
import com.accelq.csma_portal.Assessment.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/assessment-scores")
public class AssessmentScoreController {

    private final AssessmentScoreService assessmentScoreService;
    private final AssessmentRepository assessmentRepository;
    private static final Logger LOGGER = Logger.getLogger(AssessmentScoreController.class.getName());

    @Autowired
    public AssessmentScoreController(AssessmentScoreService assessmentScoreService, AssessmentRepository assessmentRepository) {
        this.assessmentScoreService = assessmentScoreService;
        this.assessmentRepository = assessmentRepository;
    }

    @GetMapping
    public List<AssessmentScore> getAllAssessmentScores() {
        return assessmentScoreService.getAllAssessmentScores();
    }

    @GetMapping(path = "{assessmentScoreId}")
    public Optional<AssessmentScore> getAssessmentScoreById(@PathVariable("assessmentScoreId") Integer id) {
        return assessmentScoreService.getAssessmentScoreById(id);
    }

    @PostMapping
    public void addNewAssessmentScore(@RequestBody AssessmentScore assessmentScore) {
        assessmentScoreService.addNewAssessmentScore(assessmentScore);
    }

    @DeleteMapping(path = "{assessmentScoreId}")
    public void deleteAssessmentScore(@PathVariable("assessmentScoreId") Integer id) {
        assessmentScoreService.deleteAssessmentScore(id);
    }

    @PostMapping(path = "/updateScores")
    public ResponseEntity<Map<String, Object>> updateAssessmentScores(@RequestBody Map<String, Object> payload) {
        try {
            Integer assessmentId = (Integer) payload.get("assessmentId");
            List<Map<String, Object>> scores = (List<Map<String, Object>>) payload.get("scores");

            LOGGER.info("Received request to update scores for assessment ID: " + assessmentId);
            assessmentScoreService.updateAssessmentScores(assessmentId, scores);

            Assessment updatedAssessment = assessmentRepository.findById(assessmentId)
                    .orElseThrow(() -> new IllegalStateException("Assessment with id " + assessmentId + " does not exist."));

            LOGGER.info("Scores updated successfully for assessment ID: " + assessmentId);
            return ResponseEntity.ok(Map.of("message", "Scores updated successfully", "amaScore", updatedAssessment.getAmaScore()));
        } catch (Exception e) {
            LOGGER.severe("Error updating scores: " + e.getMessage());
            return ResponseEntity.status(500).body(Map.of("message", "Error updating scores", "error", e.getMessage()));
        }
    }

    @GetMapping(path = "/by-assessment/{assessmentId}")
    public List<AssessmentScore> getAssessmentScoresByAssessmentId(@PathVariable("assessmentId") Integer assessmentId) {
        return assessmentScoreService.getAssessmentScoresByAssessmentId(assessmentId);
    }
}
