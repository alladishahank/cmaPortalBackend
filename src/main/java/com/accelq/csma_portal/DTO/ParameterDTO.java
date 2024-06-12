package com.accelq.csma_portal.DTO;public class ParameterDTO {
    private String name;
    private String description;
    private String endpoint;

    public ParameterDTO() {}

    public ParameterDTO(String name, String description, String endpoint) {
        this.name = name;
        this.description = description;
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
