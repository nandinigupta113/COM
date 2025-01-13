package com.nandini.customerordermanagement.service;

import com.nandini.customerordermanagement.Model.Customer;
import com.nandini.customerordermanagement.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repo;

    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return repo.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        return repo.save(customer);
    }

    public Optional<Customer> updateCustomer(int id, Customer customer) {
        Optional<Customer> existingCustomer = repo.findById(id);
        if (existingCustomer.isPresent()) {
            Customer existing = existingCustomer.get();
            existing.setCustomerName(customer.getCustomerName());
            existing.setContactNo(customer.getContactNo());
            existing.setEmailId(customer.getEmailId());
            existing.setPaymentMethod(customer.getPaymentMethod());
            repo.save(existing);
            return Optional.of(existing);
        }
        return Optional.empty();
    }

    public boolean deleteCustomer(int id) {
        Optional<Customer> customer = repo.findById(id);
        if (customer.isPresent()) {
            repo.delete(customer.get());
            return true;
        }
        return false;
    }
}
