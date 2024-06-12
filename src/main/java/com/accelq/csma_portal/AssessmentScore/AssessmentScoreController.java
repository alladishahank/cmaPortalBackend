package com.accelq.csma_portal.AssessmentScore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/assessment-scores")
public class AssessmentScoreController {

    private final AssessmentScoreService assessmentScoreService;

    @Autowired
    public AssessmentScoreController(AssessmentScoreService assessmentScoreService) {
        this.assessmentScoreService = assessmentScoreService;
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

    @PutMapping(path = "{assessmentScoreId}")
    public void updateAssessmentScore(@PathVariable("assessmentScoreId") Integer id, @RequestBody AssessmentScore updatedAssessmentScore) {
        assessmentScoreService.updateAssessmentScore(id, updatedAssessmentScore);
    }

    @GetMapping(path = "/by-assessment/{assessmentId}")
    public List<AssessmentScore> getAssessmentScoresByAssessmentId(@PathVariable("assessmentId") Integer assessmentId) {
        return assessmentScoreService.getAssessmentScoresByAssessmentId(assessmentId);
    }
}
