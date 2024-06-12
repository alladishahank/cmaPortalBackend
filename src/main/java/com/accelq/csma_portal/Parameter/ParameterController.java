package com.accelq.csma_portal.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public List<Parameter> getAllParameters() {
        return parameterService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parameter> getParameterById(@PathVariable Integer id) {
        return parameterService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Parameter createParameter(@RequestBody Parameter parameter) {
        return parameterService.save(parameter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parameter> updateParameter(@PathVariable Integer id, @RequestBody Parameter parameterDetails) {
        return parameterService.findById(id)
                .map(parameter -> {
                    parameter.setName(parameterDetails.getName());
                    parameter.setDescription(parameterDetails.getDescription());
                    parameter.setEndpoint(parameterDetails.getEndpoint());
                    parameter.setCategory(parameterDetails.getCategory());
                    Parameter updatedParameter = parameterService.save(parameter);
                    return ResponseEntity.ok(updatedParameter);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParameter(@PathVariable Integer id) {
        return parameterService.findById(id)
                .map(parameter -> {
                    parameterService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
