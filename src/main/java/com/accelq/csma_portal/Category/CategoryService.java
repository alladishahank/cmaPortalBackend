package com.accelq.csma_portal.Category;

import com.accelq.csma_portal.DTO.CategoryDTO;
import com.accelq.csma_portal.DTO.ParameterDTO;
import com.accelq.csma_portal.Parameter.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        System.out.println("in the service...");
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> getCategoriesWithParameters() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();

        for (Category category : categories) {
            List<ParameterDTO> parameterDTOs = new ArrayList<>();
            for (Parameter parameter : category.getParameters()) {
                ParameterDTO parameterDTO = new ParameterDTO();
                parameterDTO.setName(parameter.getName());
                parameterDTO.setDescription(parameter.getDescription());
                parameterDTO.setEndpoint(parameter.getEndpoint());
                parameterDTOs.add(parameterDTO);
            }

            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(category.getName());
            categoryDTO.setWeight(category.getWeight());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setIndustryAvg(category.getIndustryAvg());
            categoryDTO.setParameters(parameterDTOs);

            categoryDTOs.add(categoryDTO);
        }

        return categoryDTOs;
    }
}
