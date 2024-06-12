package com.accelq.csma_portal.DTO;

import java.util.List;

public class CategoryDTO {
    private String name;
    private Integer weight;
    private String description;
    private Integer industry_avg;
    private List<ParameterDTO> parameters;

    public CategoryDTO() {}

    public CategoryDTO(String name, Integer weight, String description, Integer industry_avg, List<ParameterDTO> parameters) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.industry_avg = industry_avg;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIndustryAvg() {
        return industry_avg;
    }

    public void setIndustryAvg(Integer industry_avg) {
        this.industry_avg = industry_avg;
    }

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }
}
