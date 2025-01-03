package com.nandini.customerordermanagement.Controller;

import com.nandini.customerordermanagement.Entity.Customer;
import com.nandini.customerordermanagement.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    Repository repo;
    //getting all Customers
    @GetMapping("/getCustomers")
    public List<Customer> getAllCustomers(){
        List<Customer> customerList = repo.findAll();
        return customerList;
    }

    @GetMapping("/getCustomers/{id}")
    public Customer getCustomerById(@PathVariable int id){
        Customer customer = repo.findById(id).get();
        return customer;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer Cs){
        repo.save(Cs);
    }

    @PutMapping("/updateCustomer/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer Cs){
        Customer customer = repo.findById(id).get();
        customer.setCustomerName(Cs.getCustomerName());
        customer.setContactNo(Cs.getContactNo());
        customer.setEmailId(Cs.getEmailId());
        customer.setPaymentMethod(Cs.getPaymentMethod());
        repo.save(customer);
        return customer;
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable int id){
        Customer customer = repo.findById(id).get();
        repo.delete(customer);
    }

}

