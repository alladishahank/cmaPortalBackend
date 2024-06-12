package com.accelq.csma_portal.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteById(Integer id) {
        customerRepository.deleteById(id);
    }

    public List<String> getAllCustomerNames() {
        List<Customer> customers = customerRepository.findAll();
        List<String> customerNames = new ArrayList<>();

        for (Customer customer : customers) {
            String name = customer.getName();
            customerNames.add(name);
        }
        return customerNames;
    }
}
