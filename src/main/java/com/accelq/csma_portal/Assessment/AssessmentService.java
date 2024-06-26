package com.accelq.csma_portal.Assessment;

import com.accelq.csma_portal.AssessmentScore.AssessmentScore;
import com.accelq.csma_portal.DTO.AssessmentDetailsDTO;
import com.accelq.csma_portal.DTO.DetailedAssessmentScoreDTO;
import com.accelq.csma_portal.DTO.ViewFullAssessmentDetailsDTO;
import com.accelq.csma_portal.Parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.accelq.csma_portal.AssessmentScore.AssessmentScoreRepository;

import java.util.*;
import java.util.logging.Logger;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final AssessmentScoreRepository assessmentScoreRepository;
    private static final Logger LOGGER = Logger.getLogger(AssessmentService.class.getName());

    @Autowired
    public AssessmentService(AssessmentRepository assessmentRepository, AssessmentScoreRepository assessmentScoreRepository) {
        this.assessmentRepository = assessmentRepository;
        this.assessmentScoreRepository = assessmentScoreRepository;
    }

    public List<AssessmentDetailsDTO> getAllAssessmentDetails() {
        List<Assessment> assessments = assessmentRepository.findAll();
        List<AssessmentDetailsDTO> assessmentDetailsDTOs = new ArrayList<>();

        for (Assessment assessment : assessments) {
            AssessmentDetailsDTO detailsDTO = convertToAssessmentDetailsDTO(assessment);
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

    public List<ViewFullAssessmentDetailsDTO> getAllAssessmentFullDetails() {
        List<Assessment> assessments = assessmentRepository.findAll();
        List<ViewFullAssessmentDetailsDTO> assessmentFullDetailsDTOs = new ArrayList<>();

        for (Assessment assessment : assessments) {
            ViewFullAssessmentDetailsDTO detailsDTO = convertToViewFullAssessmentDetailsDTO(assessment);
            assessmentFullDetailsDTOs.add(detailsDTO);
        }

        return assessmentFullDetailsDTOs;
    }

    private AssessmentDetailsDTO convertToAssessmentDetailsDTO(Assessment assessment) {
        AssessmentDetailsDTO detailsDTO = new AssessmentDetailsDTO();
        detailsDTO.setId(assessment.getId());
        detailsDTO.setAssessmentName(assessment.getName());
        detailsDTO.setCustomerName(assessment.getCustomer().getName());
        detailsDTO.setProjectId(assessment.getProjectId());
        detailsDTO.setAssessorName(assessment.getAssessor().getFirstName() + " " + assessment.getAssessor().getLastName());
        detailsDTO.setAssessmentDate(assessment.getAssessmentDate());
        detailsDTO.setAmaScore(assessment.getAmaScore());
        return detailsDTO;
    }

    private ViewFullAssessmentDetailsDTO convertToViewFullAssessmentDetailsDTO(Assessment assessment) {
        ViewFullAssessmentDetailsDTO detailsDTO = new ViewFullAssessmentDetailsDTO();
        detailsDTO.setId(assessment.getId());  // Ensure this is correctly setting the ID
        detailsDTO.setAssessmentName(assessment.getName());
        detailsDTO.setCustomerName(assessment.getCustomer().getName());
        detailsDTO.setProjectId(assessment.getProjectId());
        detailsDTO.setAssessorName(assessment.getAssessor().getFirstName() + " " + assessment.getAssessor().getLastName());
        detailsDTO.setAssessmentDate(assessment.getAssessmentDate());
        detailsDTO.setAmaScore(assessment.getAmaScore());

        List<DetailedAssessmentScoreDTO> scoreDetails = new ArrayList<>();
        Integer assessmentId = assessment.getId();
        LOGGER.info("Fetching scores for assessment ID: " + assessmentId);
        assessmentScoreRepository.findByAssessmentIdOrderByParameterId(assessmentId).forEach(score -> {
            DetailedAssessmentScoreDTO scoreDetailDTO = new DetailedAssessmentScoreDTO();
            scoreDetailDTO.setParameterId(score.getParameter().getId());
            scoreDetailDTO.setParameterName(score.getParameter().getName());
            scoreDetailDTO.setCategoryName(score.getParameter().getCategory().getName());
            scoreDetailDTO.setScore(score.getScore());
            scoreDetails.add(scoreDetailDTO);
        });

        detailsDTO.setAssessmentScores(scoreDetails);
        return detailsDTO;
    }

    public void updateAMAScore(Integer assessmentId) {
        List<AssessmentScore> scores = assessmentScoreRepository.findByAssessmentIdOrderByParameterId(assessmentId);
        double totalWeightedScore = 0.0;
        double totalWeight = 0.0;

        LOGGER.info("Starting AMA score calculation for assessment ID: " + assessmentId);

        // Collect scores and weights for each parameter
        for (AssessmentScore score : scores) {
            Parameter parameter = score.getParameter();
            double parameterScore = score.getScore();
            double parameterWeight = parameter.getCategory().getWeight(); // Use the weight of the category

            LOGGER.info("Parameter ID: " + parameter.getId() + ", Parameter Score: " + parameterScore + ", Category Weight: " + parameterWeight);

            totalWeightedScore += parameterScore * parameterWeight;
            totalWeight += parameterWeight;
        }

        LOGGER.info("Total Weighted Score: " + totalWeightedScore);
        LOGGER.info("Total Weight: " + totalWeight);

        // Calculate AMA score
        double amaScore = totalWeightedScore / totalWeight;
        amaScore = Math.round(amaScore * 10.0) / 10.0;

        LOGGER.info("Calculated AMA Score: " + amaScore);

        // Update the assessment with the new AMA score
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new IllegalStateException("Assessment with id " + assessmentId + " does not exist."));
        assessment.setAmaScore(amaScore);

        LOGGER.info("Saving updated AMA score for assessment ID: " + assessmentId + ", AMA Score: " + amaScore);
        assessmentRepository.save(assessment);
    }

}
