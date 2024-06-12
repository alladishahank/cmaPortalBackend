package com.accelq.csma_portal.Category;

import com.accelq.csma_portal.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetails) {
        return categoryService.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    category.setWeight(categoryDetails.getWeight());
                    category.setDescription(categoryDetails.getDescription());
                    category.setIndustryAvg(categoryDetails.getIndustryAvg());
                    Category updatedCategory = categoryService.save(category);
                    return ResponseEntity.ok(updatedCategory);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        return categoryService.findById(id)
                .map(category -> {
                    categoryService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/withParameters")
    public List<CategoryDTO> getCategoriesWithParameters() {
        return categoryService.getCategoriesWithParameters();
    }
}
