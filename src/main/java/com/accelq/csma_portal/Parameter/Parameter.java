package com.accelq.csma_portal.Parameter;

import jakarta.persistence.*;
import com.accelq.csma_portal.Category.Category;

@Entity
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String endpoint;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Parameter() {}

    public Parameter(Integer id, String name, String description, String endpoint, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.endpoint = endpoint;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
