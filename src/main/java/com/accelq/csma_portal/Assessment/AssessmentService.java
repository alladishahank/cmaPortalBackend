package com.accelq.csma_portal.Assessment;

import com.accelq.csma_portal.DTO.AssessmentDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
    }

    public List<AssessmentDetailsDTO> getAllAssessmentDetails() {
        List<Assessment> assessments = assessmentRepository.findAll();
        List<AssessmentDetailsDTO> assessmentDetailsDTOs = new ArrayList<>();

        for (Assessment assessment : assessments) {
            AssessmentDetailsDTO detailsDTO = new AssessmentDetailsDTO();
            detailsDTO.setAssessmentName(assessment.getName());
            detailsDTO.setCustomerName(assessment.getCustomer().getName());
            detailsDTO.setProjectId(assessment.getProjectId());
            detailsDTO.setAssessorName(assessment.getAssessor().getFirstName() + " " + assessment.getAssessor().getLastName());
            detailsDTO.setAssessmentDate(assessment.getAssessmentDate());
            detailsDTO.setAmaScore(assessment.getAmaScore());

            assessmentDetailsDTOs.add(detailsDTO);
        }

        return assessmentDetailsDTOs;
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }

    public Optional<Assessment> getAssessmentById(Integer id) {
        return assessmentRepository.findById(id);
    }

    public void addNewAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }

    public void deleteAssessment(Integer id) {
        boolean exists = assessmentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Assessment with id " + id + " does not exist.");
        }
        assessmentRepository.deleteById(id);
    }

    public void updateAssessment(Integer id, Assessment updatedAssessment) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Assessment with id " + id + " does not exist."));

        assessment.setName(updatedAssessment.getName());
        assessment.setCustomer(updatedAssessment.getCustomer());
        assessment.setProjectId(updatedAssessment.getProjectId());
        assessment.setAssessor(updatedAssessment.getAssessor());
        assessment.setAssessmentDate(updatedAssessment.getAssessmentDate());
        assessment.setAmaScore(updatedAssessment.getAmaScore());

        assessmentRepository.save(assessment);
    }
}
