package com.nandini.customerordermanagement.Controller;
import com.nandini.customerordermanagement.Model.Customer;
import com.nandini.customerordermanagement.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Customers")
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        Optional<Customer> customer = repo.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer Cs) {
        Customer savedCustomer = repo.save(Cs);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer Cs) {
        Optional<Customer> existingCustomer = repo.findById(id);
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            customer.setCustomerName(Cs.getCustomerName());
            customer.setContactNo(Cs.getContactNo());
            customer.setEmailId(Cs.getEmailId());
            customer.setPaymentMethod(Cs.getPaymentMethod());
            repo.save(customer);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        Optional<Customer> customer = repo.findById(id);
        if (customer.isPresent()) {
            repo.delete(customer.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 Not Found
        }
    }
}