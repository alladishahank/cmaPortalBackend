package com.accelq.csma_portal.Assessment;

import com.accelq.csma_portal.DTO.AssessmentDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping(path = "{assessmentId}")
    public Optional<Assessment> getAssessmentById(@PathVariable("assessmentId") Integer id) {
        return assessmentService.getAssessmentById(id);
    }

    @PostMapping
    public void addNewAssessment(@RequestBody Assessment assessment) {
        assessmentService.addNewAssessment(assessment);
    }

    @DeleteMapping(path = "{assessmentId}")
    public void deleteAssessment(@PathVariable("assessmentId") Integer id) {
        assessmentService.deleteAssessment(id);
    }

    @PutMapping(path = "{assessmentId}")
    public void updateAssessment(@PathVariable("assessmentId") Integer id, @RequestBody Assessment updatedAssessment) {
        assessmentService.updateAssessment(id, updatedAssessment);
    }

    @GetMapping(path = "/details")
    public List<AssessmentDetailsDTO> getAllAssessmentDetails() {
        return assessmentService.getAllAssessmentDetails();
    }
}
