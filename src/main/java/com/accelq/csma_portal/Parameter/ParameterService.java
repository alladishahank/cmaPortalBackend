package com.accelq.csma_portal.Parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    public List<Parameter> findAll() {
        return parameterRepository.findAll();
    }

    public Optional<Parameter> findById(Integer id) {
        return parameterRepository.findById(id);
    }

    public Parameter save(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public void deleteById(Integer id) {
        parameterRepository.deleteById(id);
    }
}
